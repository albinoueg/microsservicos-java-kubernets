package br.com.albinomoreira.userapi.service;

import br.com.albinomoreira.shoppingclient.dto.UserDTO;
import br.com.albinomoreira.shoppingclient.exception.UserNotFoundException;
import br.com.albinomoreira.userapi.converter.DTOConverter;
import br.com.albinomoreira.userapi.model.User;
import br.com.albinomoreira.userapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> getAll(){
        List<User> usuarios = userRepository.findAll();
        return usuarios.stream().map(DTOConverter::convert).collect(Collectors.toList());
    }

    public UserDTO findById(long userId){
        Optional<User> usuario = userRepository.findById(userId);
        if(usuario.isPresent()){
            return DTOConverter.convert(usuario.get());
        }
        throw new UserNotFoundException();
    }

    public UserDTO save(UserDTO userDTO){
        userDTO.setKey(UUID.randomUUID().toString());
        userDTO.setDataCadastro(new Date());
        User user = userRepository.save(User.convert(userDTO));
        return DTOConverter.convert(user);
    }

    public UserDTO delete(long userId){
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            userRepository.delete(user.get());
        }
        throw new UserNotFoundException();
    }

    public List<UserDTO> queryByNome(String nome){
        List<User> usuarios = userRepository.queryByNomeLike(nome);
        return usuarios.stream().map(DTOConverter::convert).collect(Collectors.toList());
    }

    public UserDTO findByCpfAndKey(String cpf, String key) {
        Optional<User> usuario = userRepository.findByCpfAndKey(cpf,key);
        if(usuario.isPresent()){
            return DTOConverter.convert(usuario.get());
        }
        throw new UserNotFoundException();
    }
}
