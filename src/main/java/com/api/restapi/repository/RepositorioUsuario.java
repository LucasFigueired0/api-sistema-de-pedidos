package com.api.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.restapi.entity.Usuario;


public interface RepositorioUsuario extends JpaRepository<Usuario,Long>{
    Usuario findByEmailAndSenha(String email, String senha);
    Usuario findUsuarioById(Long id);

}