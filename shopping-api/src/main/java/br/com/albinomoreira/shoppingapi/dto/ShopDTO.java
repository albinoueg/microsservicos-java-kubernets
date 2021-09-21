package br.com.albinomoreira.shoppingapi.dto;

import br.com.albinomoreira.shoppingapi.model.Shop;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
public class ShopDTO {

    @NotBlank
    private String userIdentifier;
    @NotNull
    private float total;
    @NotNull
    private Date date;
    @NotNull
    private List<ItemDTO> items;

    public static ShopDTO convert(Shop shop){
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setUserIdentifier(shop.getUserIdentifier());
        shop.setTotal(shop.getTotal());
        return shopDTO;
    }
}
