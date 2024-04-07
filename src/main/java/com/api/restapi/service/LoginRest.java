package com.api.restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.restapi.entity.Usuario;
import com.api.restapi.models.LoginModel;
import com.api.restapi.repository.RepositorioUsuario;

@RestController
@RequestMapping("/login")
public class LoginRest {
 
    @Autowired
    private final RepositorioUsuario repositorioUsuario;

    public LoginRest(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        try{    
            String email = usuario.getEmail();
            String senha = usuario.getSenha();

            Usuario foundUser = repositorioUsuario.findByEmailAndSenha(email, senha);
    
            if (foundUser != null) {
                LoginModel userData = new LoginModel(
                    foundUser.getId(),
                    foundUser.getNome_funcionario(),
                    foundUser.getEmail(),
                    foundUser.getNome_empresa(),
                    foundUser.getTelefone());
                return ResponseEntity.ok(userData);
            } else {
                return ResponseEntity.badRequest().body("Usuario ou senha incorretos.");
            }
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao efetuar login: " + e.getMessage());
        }
      
    }

    @PostMapping("user-by-id")
    public  ResponseEntity<?> userById(@RequestBody Usuario usuario){
        try{    
            Long id = usuario.getId();

            Usuario foundUser = repositorioUsuario.findUsuarioById(id);
    
            if (foundUser != null) {
                LoginModel userData = new LoginModel(
                    foundUser.getId(),
                    foundUser.getNome_funcionario(),
                    foundUser.getEmail(),
                    foundUser.getNome_empresa(),
                    foundUser.getTelefone());
                return ResponseEntity.ok(userData);
            } else {
                return ResponseEntity.badRequest().body("Usuario ou senha incorretos.");
            }
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao efetuar login: " + e.getMessage());
        }
    }
}
