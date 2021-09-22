package br.com.albinomoreira.shoppingapi.service;

import br.com.albinomoreira.shoppingapi.converter.DTOConverter;
import br.com.albinomoreira.shoppingapi.dto.ShopDTO;
import br.com.albinomoreira.shoppingapi.dto.ShopReportDTO;
import br.com.albinomoreira.shoppingapi.model.Shop;
import br.com.albinomoreira.shoppingapi.repository.ShopRepository;
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

    public List<ShopDTO> getAll(){
        List<Shop> shops = shopRepository.findAll();
        return shops.stream().map(ShopDTO::convert).collect(Collectors.toList());
    }

    public List<ShopDTO> getByUser(String userIdentifier){
        List<Shop> shops = shopRepository.findAllByUserIdentifier(userIdentifier);
        return shops.stream().map(ShopDTO::convert).collect(Collectors.toList());
    }

    public List<ShopDTO> getByDate(ShopDTO shopDTO){
        List<Shop> shops = shopRepository.findAllByDateGreaterThan(shopDTO.getDate());
        return shops.stream().map(DTOConverter::convert).collect(Collectors.toList());
    }

    public ShopDTO findById(long productId){
        Optional<Shop> shop = shopRepository.findById(productId);
        if(shop.isPresent()){
            return ShopDTO.convert(shop.get());
        }
        return null;
    }

    public ShopDTO save(ShopDTO shopDTO){
        shopDTO.setTotal(shopDTO.getItems().stream().map(x -> x.getPrice()).reduce((float) 0, Float::sum));

        Shop shop = Shop.convert(shopDTO);
        shop.setDate(new Date());

        shop = shopRepository.save(shop);
        return ShopDTO.convert(shop);
    }

    public List<ShopDTO> getShopByFilter(Date dataInicio, Date dataFim, Float valorMinimo){

        List<Shop> shops = shopRepository.getShopByFilters(dataInicio, dataFim, valorMinimo);
        return shops.stream().map(DTOConverter::convert).collect(Collectors.toList());
    }

    public ShopReportDTO getReportByDate(Date dataInicio, Date dataFim){
        return shopRepository.getReportByDate(dataInicio, dataFim);
    }
}
