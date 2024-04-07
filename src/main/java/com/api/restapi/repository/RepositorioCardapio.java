package com.api.restapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.api.restapi.entity.Cardapio;

import jakarta.transaction.Transactional;

public interface RepositorioCardapio extends JpaRepository<Cardapio, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Cardapio p SET p.nomeItem = :novoNome WHERE p.id = :itemId AND p.usuario_id = :usuarioId")
    void atualizarNomeDoItem(Long itemId, Long usuarioId, String novoNome);

    @Modifying
    @Transactional
    @Query("UPDATE Cardapio p SET p.preco = :novoPreco WHERE p.id = :itemId AND p.usuario_id = :usuarioId")
    void atualizarPrecoDoItem(Long itemId, Long usuarioId, Double novoPreco);

    @Modifying
    @Transactional
    @Query("DELETE FROM Cardapio WHERE id = :itemId AND usuario_id = :usuarioId")
    void deletarItem(Long itemId, Long usuarioId);

    @Query("SELECT c FROM Cardapio c WHERE c.usuario_id = :usuarioId")
    List<Cardapio> listarItensDoCardapio(Long usuarioId);
}
