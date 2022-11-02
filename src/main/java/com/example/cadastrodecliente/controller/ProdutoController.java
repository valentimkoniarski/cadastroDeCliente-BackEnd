package com.example.cadastrodecliente.controller;

import com.example.cadastrodecliente.dto.produto.ProdutoDto;
import com.example.cadastrodecliente.model.Produto;
import com.example.cadastrodecliente.model.Usuario;
import com.example.cadastrodecliente.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @GetMapping("/{clienteId}")
    public ResponseEntity<List<Produto>> buscarTodos(@PathVariable Long clienteId, @AuthenticationPrincipal Usuario usuario) {
        return ResponseEntity.ok(produtoService.buscarTodos(clienteId, usuario));
    }

    @DeleteMapping("/{clienteId}")
    @Transactional
    public ResponseEntity<Produto> apagar(@PathVariable Long clienteId, @AuthenticationPrincipal Usuario usuario, @RequestBody ProdutoDto produtoDto) {
        return ResponseEntity.ok(produtoService.apagar(clienteId, usuario, produtoDto));
    }

    @PostMapping("/{clienteId}")
    @Transactional
    public ResponseEntity<Produto> salvar(@PathVariable Long clienteId, @AuthenticationPrincipal Usuario usuario, @RequestBody ProdutoDto produtoDto) throws Exception {
        return ResponseEntity.ok(produtoService.salvar(clienteId, usuario, produtoDto));
    }

    @PutMapping("/{clienteId}")
    @Transactional
    public ResponseEntity<Produto> atualizar(@PathVariable Long clienteId, @AuthenticationPrincipal Usuario usuario, @RequestBody ProdutoDto produtoDto) throws Exception {
        return ResponseEntity.ok(produtoService.atualizar(clienteId, usuario, produtoDto));
    }

}
