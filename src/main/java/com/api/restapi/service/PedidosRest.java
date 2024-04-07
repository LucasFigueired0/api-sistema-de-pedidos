package com.api.restapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.restapi.entity.Pedidos;
import com.api.restapi.repository.RepositorioPedidos;

@RestController
@RequestMapping("/pedidos")
public class PedidosRest {
    @Autowired
    private RepositorioPedidos repositorioPedidos;

    public PedidosRest(RepositorioPedidos repositorioPedidos) {
        this.repositorioPedidos = repositorioPedidos;
    }

    // Cadastrar pedido
    @PostMapping("/criar-pedido")
    public ResponseEntity<?> salvar(@RequestBody Pedidos pedidos) {
        try {
            if (pedidos != null) {
                repositorioPedidos.save(pedidos);
                return ResponseEntity.ok("Pedido salvo com sucesso!");
            } else {
                return ResponseEntity.badRequest().body("O objeto pedido está vazio.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao salvar o pedido: " + e.getMessage());
        }
    }

    // atualizar descrição do pedido
    @PutMapping("/atualizar-descricao")
    public ResponseEntity<String> atualizarDescricao(@RequestBody Map<String, Object> requestBody) {
        try {
            Long pedidoId = Long.parseLong(requestBody.get("pedidoId").toString());
            Long usuarioId = Long.parseLong(requestBody.get("usuarioId").toString());
            String novaDescricao = (String) requestBody.get("novaDescricao");

            repositorioPedidos.atualizarDescricaoDoPedido(pedidoId, usuarioId, novaDescricao);
            return ResponseEntity.ok("Descrição do pedido atualizada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao atualizar a descrição do pedido: " + e.getMessage());
        }
    }

    //Atualizar status do pedido
    @PutMapping("/atualizar-status")
    public ResponseEntity<String> atualizarStatus(@RequestBody Map<String, Object> requestBody) {
        try {
            Long pedidoId = Long.parseLong(requestBody.get("pedidoId").toString());
            Long usuarioId = Long.parseLong(requestBody.get("usuarioId").toString());
            String novoStatus = (String) requestBody.get("novoStatus");

            repositorioPedidos.atualizarStatusDoPedido(pedidoId, usuarioId, novoStatus);
            return ResponseEntity.ok("Status do pedido atualizado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao atualizar o status do pedido: " + e.getMessage());
        }
    }

    //Atualizar valor do pedido
    @PutMapping("/atualizar-valor")
    public ResponseEntity<String> atualizarValor(@RequestBody Map<String, Object> requestBody) {
        try {
            Long pedidoId = Long.parseLong(requestBody.get("pedidoId").toString());
            Long usuarioId = Long.parseLong(requestBody.get("usuarioId").toString());
            double novoValor = Double.parseDouble(requestBody.get("novoValor").toString());

            repositorioPedidos.atualizarValorDoPedido(pedidoId, usuarioId, novoValor);

            return ResponseEntity.ok("Valor do pedido atualizado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao atualizar o Valor do pedido: " + e.getMessage());
        }
    }

    @PostMapping("/listar-pedidos")
    public ResponseEntity<List<Pedidos>> listarPedidos(@RequestBody Map<String, Object> requestBody) {
        try {
            Long usuarioId = Long.parseLong(requestBody.get("usuarioId").toString());
            List<Pedidos> pedidos = repositorioPedidos.listarPedidosPorUsuario(usuarioId);
            return ResponseEntity.ok(pedidos);
        } catch (Exception e) {
            e.printStackTrace(); // Isso é opcional, mas pode ajudar a depurar o erro
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
        }
    }

     @DeleteMapping("/deletar-pedido")
    public ResponseEntity <String> deletarPedido(@RequestBody Map<String, Object> requestBody){
        try{
            Long pedidoId = Long.parseLong(requestBody.get("pedidoId").toString());
            Long usuarioId = Long.parseLong(requestBody.get("usuarioId").toString());
          
            repositorioPedidos.deletarPedido(pedidoId, usuarioId);
            return ResponseEntity.ok("Pedido deletado com sucesso");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar pedido"+e.getMessage());
        }
    } 

}
