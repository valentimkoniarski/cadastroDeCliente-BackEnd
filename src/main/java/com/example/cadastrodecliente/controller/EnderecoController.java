package com.example.cadastrodecliente.controller;

import java.util.List;
import javax.transaction.Transactional;
import com.example.cadastrodecliente.dto.endereco.EnderecoDto;
import com.example.cadastrodecliente.model.Endereco;
import com.example.cadastrodecliente.model.Usuario;
import com.example.cadastrodecliente.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoController {

    @Autowired
    EnderecoService enderecoService;

    @GetMapping("/{clienteId}")
    public ResponseEntity<List<Endereco>> buscarTodos(@PathVariable Long clienteId, @AuthenticationPrincipal Usuario usuario) {
        return ResponseEntity.ok(enderecoService.buscarTodos(clienteId, usuario));
    }

    @PostMapping("/{clienteId}")
    @Transactional
    public ResponseEntity<Endereco> salvar(@PathVariable Long clienteId, @AuthenticationPrincipal Usuario usuario, @RequestBody EnderecoDto enderecoDto) {
        return ResponseEntity.ok(enderecoService.salvar(clienteId, usuario, enderecoDto));
    }

    @PutMapping("/{clienteId}")
    @Transactional
    public ResponseEntity<Endereco> atualizar(@PathVariable Long clienteId, @AuthenticationPrincipal Usuario usuario, @RequestBody EnderecoDto enderecoDto) {
        return ResponseEntity.ok(enderecoService.atualizar(clienteId, usuario, enderecoDto));
    }

    @DeleteMapping("/{clienteId}")
    @Transactional
    public ResponseEntity<Endereco> deletarEndereco(@PathVariable Long clienteId, @AuthenticationPrincipal Usuario usuario, @RequestBody EnderecoDto enderecoDto) {
        return ResponseEntity.ok(enderecoService.apagar(clienteId, usuario, enderecoDto));
    }

}
