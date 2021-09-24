package br.com.albinomoreira.shoppingapi.service;

import br.com.albinomoreira.shoppingapi.converter.DTOConverter;
import br.com.albinomoreira.shoppingapi.model.Shop;
import br.com.albinomoreira.shoppingapi.repository.ShopRepository;
import br.com.albinomoreira.shoppingclient.dto.ItemDTO;
import br.com.albinomoreira.shoppingclient.dto.ProductDTO;
import br.com.albinomoreira.shoppingclient.dto.ShopDTO;
import br.com.albinomoreira.shoppingclient.dto.ShopReportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    public List<ShopDTO> getAll(){
        List<Shop> shops = shopRepository.findAll();
        return shops.stream().map(DTOConverter::convert).collect(Collectors.toList());
    }

    public List<ShopDTO> getByUser(String userIdentifier){
        List<Shop> shops = shopRepository.findAllByUserIdentifier(userIdentifier);
        return shops.stream().map(DTOConverter::convert).collect(Collectors.toList());
    }

    public List<ShopDTO> getByDate(ShopDTO shopDTO){
        List<Shop> shops = shopRepository.findAllByDateGreaterThan(shopDTO.getDate());
        return shops.stream().map(DTOConverter::convert).collect(Collectors.toList());
    }

    public ShopDTO findById(long productId){
        Optional<Shop> shop = shopRepository.findById(productId);
        if(shop.isPresent()){
            return DTOConverter.convert(shop.get());
        }
        return null;
    }

    public ShopDTO save(ShopDTO shopDTO, String key){
        if(userService.getUserByCpf(shopDTO.getUserIdentifier(), key) == null) {
            return null;
        }

        if(!validateProducts(shopDTO.getItems())){
            return null;
        }

        shopDTO.setTotal(shopDTO.getItems().stream().map(x -> x.getPrice()).reduce((float) 0, Float::sum));
        shopDTO.setDate(new Date());

        Shop shop = Shop.convert(shopDTO);
        shop = shopRepository.save(shop);

        return DTOConverter.convert(shop);
    }

    private boolean validateProducts(List<ItemDTO> items) {
        for(ItemDTO item : items){
            ProductDTO productDTO = productService.getProductByIdentifier(item.getProductIdentifier());
            if(productDTO == null){
                return false;
            }
            item.setPrice(productDTO.getPreco());
        }
        return true;
    }

    public List<ShopDTO> getShopByFilter(Date dataInicio, Date dataFim, Float valorMinimo){

        List<Shop> shops = shopRepository.getShopByFilters(dataInicio, dataFim, valorMinimo);
        return shops.stream().map(DTOConverter::convert).collect(Collectors.toList());
    }

    public ShopReportDTO getReportByDate(Date dataInicio, Date dataFim){
        return shopRepository.getReportByDate(dataInicio, dataFim);
    }
}
