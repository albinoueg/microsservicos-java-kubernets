package br.com.albinomoreira.userapi.controller;

import br.com.albinomoreira.shoppingclient.dto.UserDTO;
import br.com.albinomoreira.userapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public List<UserDTO> getUsers(){
        List<UserDTO> usuarios = userService.getAll();
        return usuarios;
    }

    @GetMapping("/user/{id}")
    UserDTO findById(@PathVariable Long id){
        return userService.findById(id);
    }

    @GetMapping("/user/cpf/{cpf}")
    public UserDTO findByCpf(@PathVariable String cpf){
        return userService.findByCpf(cpf);
    }

    @PostMapping("/user")
    public UserDTO newUser(@RequestBody UserDTO userDTO){
        return userService.save(userDTO);
    }

    @DeleteMapping("/user/{id}")
    public UserDTO delete(@PathVariable Long id){
        return userService.delete(id);
    }

    @GetMapping("/user/search")
    public List<UserDTO> queryByName(@RequestParam(name = "nome", required = true) String nome){
        return userService.queryByNome(nome);
    }
}
