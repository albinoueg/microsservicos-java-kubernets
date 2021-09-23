package br.com.albinomoreira.userapi.converter;

import br.com.albinomoreira.shoppingclient.dto.UserDTO;
import br.com.albinomoreira.userapi.model.User;

public class DTOConverter {
    public static UserDTO convert(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setNome(user.getNome());
        userDTO.setEndereco(user.getEndereco());
        userDTO.setCpf(user.getCpf());
        userDTO.setEmail(user.getEmail());
        userDTO.setTelefone(user.getTelefone());
        userDTO.setDataCadastro(user.getDataCadastro());
        return userDTO;
    }
}
