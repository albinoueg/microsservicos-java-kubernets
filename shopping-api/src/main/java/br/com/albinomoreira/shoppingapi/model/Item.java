package br.com.albinomoreira.shoppingapi.model;

import br.com.albinomoreira.shoppingclient.dto.ItemDTO;
import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Item {

    private String productIdentifier;
    private Float price;

    public static Item convert(ItemDTO itemDTO){
        Item item = new Item();
        item.setProductIdentifier(itemDTO.getProductIdentifier());
        item.setPrice(itemDTO.getPrice());
        return item;
    }
}
