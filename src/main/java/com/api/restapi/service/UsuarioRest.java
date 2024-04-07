package com.api.restapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.restapi.entity.Usuario;
import com.api.restapi.repository.RepositorioUsuario;

@RestController
@RequestMapping("/usuario")
public class UsuarioRest {
    @Autowired
    private RepositorioUsuario repositorio;

    @GetMapping
    public List<Usuario>listar(){
        return repositorio.findAll();
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Usuario usuario){
       try{
        if(usuario != null) {
            repositorio.save(usuario);
            return ResponseEntity.ok("ok");
        } else {
            return ResponseEntity.badRequest().body("O objeto usuário está vazio.");
        }
       }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar o usuário: " + e.getMessage());
       }
    }

    @PutMapping
    public void alterar(@RequestBody Usuario usuario){
        if(usuario.getId()>0){
            repositorio.save(usuario);
        }
    }

    @DeleteMapping
    public void excluir(@RequestBody Usuario usuario){
        if(usuario!=null){
            repositorio.delete(usuario);
        }
    }
    
}
