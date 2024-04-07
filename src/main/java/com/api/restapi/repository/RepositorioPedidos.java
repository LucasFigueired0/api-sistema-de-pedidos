package com.api.restapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.restapi.entity.Pedidos;

import jakarta.transaction.Transactional;

public interface RepositorioPedidos extends JpaRepository<Pedidos, Long> {

    // atualização de descrição
    @Modifying
    @Transactional
    @Query("UPDATE Pedidos p SET p.descricao = :novaDescricao WHERE p.id = :pedidoId AND p.usuarioId = :usuarioId")
    void atualizarDescricaoDoPedido(Long pedidoId, Long usuarioId, String novaDescricao);

    // atualização do status
    @Modifying
    @Transactional
    @Query("UPDATE Pedidos p SET p.status = :novoStatus WHERE p.id = :pedidoId AND p.usuarioId = :usuarioId")
    void atualizarStatusDoPedido(Long pedidoId, Long usuarioId, String novoStatus);

    // Atualizar preço do pedido
    @Modifying
    @Transactional
    @Query("UPDATE Pedidos p SET p.preco = :novoValor WHERE p.id = :pedidoId AND p.usuarioId = :usuarioId")
    void atualizarValorDoPedido(Long pedidoId, Long usuarioId, double novoValor);

    // Listar pedidos por id do usuário
    @Query("SELECT p FROM Pedidos p WHERE p.usuarioId = :usuarioId")
    List<Pedidos> listarPedidosPorUsuario(@Param("usuarioId") Long usuarioId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Pedidos WHERE id = :pedidoId AND usuarioId = :usuarioId") 
    void deletarPedido(Long pedidoId, Long usuarioId);
}
