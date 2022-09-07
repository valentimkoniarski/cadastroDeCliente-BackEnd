package com.example.cadastrodecliente.controller;

import com.example.cadastrodecliente.dto.ProdutoDto;
import com.example.cadastrodecliente.model.Cliente;
import com.example.cadastrodecliente.model.ImagensProduto;
import com.example.cadastrodecliente.model.Produto;
import com.example.cadastrodecliente.model.Usuario;
import com.example.cadastrodecliente.repository.ClienteRepository;
import com.example.cadastrodecliente.repository.ImagensProdutoRepository;
import com.example.cadastrodecliente.repository.ProdutoRepository;
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
    ClienteRepository clienteRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    ImagensProdutoRepository imagensProdutoRepository;

    @GetMapping
    public ResponseEntity<List<Produto>> buscarProdutos(@AuthenticationPrincipal Usuario usuario) {

        Cliente cliente = clienteRepository.findById(usuario.getId()).get();

        List<Produto> produtos = produtoRepository.findProdutoByCliente(cliente);

        return ResponseEntity.ok(produtos);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Produto> cadastrarProduto(@RequestBody ProdutoDto produtoDto, @AuthenticationPrincipal Usuario usuario) {

        Cliente cliente = clienteRepository.findById(usuario.getId()).get();

        Produto produto = new Produto(cliente, produtoDto.getNome(), produtoDto.getValor(), produtoDto.getQuantidade(), produtoDto.getVendidos());

        produtoRepository.save(produto);

        for (ImagensProduto img : produtoDto.getImagensProdutos()) {
            ImagensProduto imagensProduto = new ImagensProduto(produto, img.getNome(), img.getUrl(), img.getDestaque());
            imagensProdutoRepository.save(imagensProduto);
        }

        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("/{produto_id}")
    @Transactional
    public ResponseEntity<Produto> apagarProduto(@PathVariable Long produto_id) {

        Produto produto = produtoRepository.findById(produto_id).get();

        produtoRepository.delete(produto);

        return ResponseEntity.ok(produto);
    }
}
