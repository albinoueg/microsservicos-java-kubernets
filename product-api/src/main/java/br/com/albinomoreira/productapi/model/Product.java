package br.com.albinomoreira.productapi.model;

import br.com.albinomoreira.shoppingclient.dto.ProductDTO;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private Float preco;
    private String descricao;
    private String productIdentifier;

    @ManyToOne
    @JoinColumn(name = "category_ID")
    private Category category;

    public static Product convert(ProductDTO productDTO){
        Product product = new Product();
        product.setNome(productDTO.getNome());
        product.setPreco(productDTO.getPreco());
        product.setDescricao(productDTO.getDescricao());
        product.setProductIdentifier(productDTO.getProductIdentifier());
        if(productDTO.getCategory() != null){
            product.setCategory(Category.convert(productDTO.getCategory()));
        }
        return product;
    }
}
