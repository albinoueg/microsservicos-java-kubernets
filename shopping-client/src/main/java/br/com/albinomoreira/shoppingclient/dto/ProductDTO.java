package br.com.albinomoreira.shoppingclient.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProductDTO {
    @NotBlank
    private String productIdentifier;
    @NotBlank
    private String nome;
    @NotNull
    private Float preco;
    @NotBlank
    private String descricao;
    @NotNull
    private CategoryDTO category;
}
