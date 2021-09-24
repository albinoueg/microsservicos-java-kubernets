package br.com.albinomoreira.shoppingapi.service;

import br.com.albinomoreira.shoppingclient.dto.UserDTO;
import br.com.albinomoreira.shoppingclient.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    public UserDTO getUserByCpf(String cpf){
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8080/user/cpf/" + cpf;

            ResponseEntity<UserDTO> response = restTemplate.getForEntity(url, UserDTO.class);
            return response.getBody();
        }catch (HttpClientErrorException.NotFound e){
            throw new UserNotFoundException();
        }
    }
}
