package com.api.restapi.service;

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

import com.api.restapi.entity.Cardapio;
import com.api.restapi.repository.RepositorioCardapio;

@RestController
@RequestMapping("/cardapio")
public class CardapioRest {
    @Autowired
    private RepositorioCardapio repositorioCardapio;

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Cardapio cardapio) {
        try {
            if (cardapio != null) {
                repositorioCardapio.save(cardapio);
                return ResponseEntity.ok("Item salvo com sucesso");
            } else {
                return ResponseEntity.badRequest().body("Objeto cardapio está vazio");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Falha ao salvar item do cardápio");
        }
    }

    @PutMapping("/atualizar-item")
    public ResponseEntity<String> atualizarItem(@RequestBody Map<String, Object> requestBody) {
        try {
            Long itemId = Long.parseLong(requestBody.get("itemId").toString());
            Long usuarioId = Long.parseLong(requestBody.get("usuarioId").toString());
            String novoNome = (String) requestBody.get("novoNome");

            repositorioCardapio.atualizarNomeDoItem(itemId, usuarioId, novoNome);
            return ResponseEntity.ok("Nome do item atualizado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao atualizar a nome: " + e.getMessage());
        }
    }

    @PutMapping("/atualizar-preco")
    public ResponseEntity<String> atualizarPreco(@RequestBody Map<String, Object> requestBody) {
        try {
            Long itemId = Long.parseLong(requestBody.get("itemId").toString());
            Long usuarioId = Long.parseLong(requestBody.get("usuarioId").toString());
            double novoPreco = Double.parseDouble(requestBody.get("novoPreco").toString());

            repositorioCardapio.atualizarPrecoDoItem(itemId, usuarioId, novoPreco);

            return ResponseEntity.ok("Preço do item atualizado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao atualizar o preço do item: " + e.getMessage());
        }
    }

    @DeleteMapping("/deletar-item")
    public ResponseEntity<String> deletarItem(@RequestBody Map<String, Object> requestBody) {
        try {
            Long itemId = Long.parseLong(requestBody.get("itemId").toString());
            Long usuarioId = Long.parseLong(requestBody.get("usuarioId").toString());

            repositorioCardapio.deletarItem(itemId, usuarioId);
            return ResponseEntity.ok("Item deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao deletar item" + e.getMessage());
        }
    }

    @PostMapping("/listar-itens")
    public ResponseEntity<List<Cardapio>> listarItensDoCardapio(@RequestBody Map<String, Long> requestBody) {
        try {
           
            Long usuarioId = requestBody.get("usuarioId");
           
            if (usuarioId != null) {
                // Chamar o método listarItensDoCardapio do RepositorioCardapio
                List<Cardapio> itensCardapio = repositorioCardapio.listarItensDoCardapio(usuarioId);
                // Retorna os itens do cardápio
                return ResponseEntity.ok(itensCardapio);
            } else {
                // Retorna uma resposta de erro se o usuárioId for nulo
                return ResponseEntity.badRequest().body(null);
            }
        } catch (Exception e) {
            // Retorna uma resposta de erro em caso de falha
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
}
