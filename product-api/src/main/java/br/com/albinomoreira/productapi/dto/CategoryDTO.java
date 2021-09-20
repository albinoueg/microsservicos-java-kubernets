package br.com.albinomoreira.productapi.dto;

import br.com.albinomoreira.productapi.model.Category;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CategoryDTO {

    @NotNull
    private long id;
    private String nome;

    public static CategoryDTO convert(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setNome(category.getNome());
        return categoryDTO;
    }
}
