package br.com.albinomoreira.shoppingapi.dto;

import br.com.albinomoreira.shoppingapi.converter.DTOConverter;
import br.com.albinomoreira.shoppingapi.model.Item;
import br.com.albinomoreira.shoppingapi.model.Shop;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ShopDTO {

    @NotBlank
    private String userIdentifier;
    private float total;
    private Date date;
    @NotNull
    private List<ItemDTO> items;

    public static ShopDTO convert(Shop shop){
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setUserIdentifier(shop.getUserIdentifier());
        shopDTO.setTotal(shop.getTotal());
        shopDTO.setDate(shop.getDate());
        shopDTO.setItems(shop.getItens().stream().map(ItemDTO::convert).collect(Collectors.toList()));
        return shopDTO;
    }
}
