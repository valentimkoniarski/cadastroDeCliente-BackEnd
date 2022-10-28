package com.example.cadastrodecliente.controller;

import com.example.cadastrodecliente.dto.ProdutoDto;
import com.example.cadastrodecliente.model.Produto;
import com.example.cadastrodecliente.model.Usuario;
import com.example.cadastrodecliente.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public ResponseEntity<List<Produto>> buscarProdutos(@AuthenticationPrincipal Usuario usuario) {
        return ResponseEntity.ok(produtoService.buscarTodos(usuario));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Produto> apagarProduto(@AuthenticationPrincipal Usuario usuario, @PathVariable Long id) {
        return ResponseEntity.ok(produtoService.apagarProduto(usuario, id));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Produto> cadastrarProduto(@AuthenticationPrincipal Usuario usuario, @RequestBody ProdutoDto produtoDto) {
        return ResponseEntity.ok(produtoService.salvarProduto(usuario, produtoDto));
    }
}
