package br.com.albinomoreira.shoppingapi.converter;

import br.com.albinomoreira.shoppingapi.dto.ItemDTO;
import br.com.albinomoreira.shoppingapi.dto.ShopDTO;
import br.com.albinomoreira.shoppingapi.model.Item;
import br.com.albinomoreira.shoppingapi.model.Shop;

import java.util.stream.Collectors;

public class DTOConverter {

    public static ItemDTO convert(Item item){
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setProductIdentifier(item.getProductIdentifier());
        itemDTO.setPrice(item.getPrice());
        return itemDTO;
    }

    public static ShopDTO convert(Shop shop){
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setUserIdentifier(shop.getUserIdentifier());
        shopDTO.setTotal(shop.getTotal());
        shopDTO.setDate(shop.getDate());
        shopDTO.setItems(shop.getItens().stream().map(DTOConverter::convert).collect(Collectors.toList()));
        return shopDTO;
    }
}
