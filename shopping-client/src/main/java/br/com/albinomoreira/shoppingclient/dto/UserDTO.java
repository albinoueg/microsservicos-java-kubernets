package br.com.albinomoreira.shoppingclient.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {
    private String nome;
    private String cpf;
    private String endereco;
    private String key;
    private String email;
    private String telefone;
    private Date dataCadastro;
}
