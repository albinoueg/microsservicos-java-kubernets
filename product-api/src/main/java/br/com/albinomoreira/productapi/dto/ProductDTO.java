package br.com.albinomoreira.productapi.dto;
import br.com.albinomoreira.productapi.model.Product;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProductDTO {

    @NotBlank
    private String nome;
    @NotNull
    private Float preco;
    @NotBlank
    private String descricao;
    @NotBlank
    private String productIdentifier;
    @NotNull
    private CategoryDTO categoryDTO;

    public static ProductDTO convert(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setNome(product.getNome());
        productDTO.setPreco(productDTO.getPreco());
        productDTO.setProductIdentifier(productDTO.getProductIdentifier());
        productDTO.setDescricao(productDTO.getDescricao());
        if(product.getCategory() != null){
            productDTO.setCategoryDTO(CategoryDTO.convert(product.getCategory()));
        }
        return productDTO;
    }
}
