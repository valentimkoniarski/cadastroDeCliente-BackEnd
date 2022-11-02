package com.example.cadastrodecliente.controller;

import com.example.cadastrodecliente.dto.cliente.ClienteDto;
import com.example.cadastrodecliente.model.Cliente;
import com.example.cadastrodecliente.model.Usuario;
import com.example.cadastrodecliente.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import javax.transaction.Transactional;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> buscarTodos(@AuthenticationPrincipal Usuario usuario) {
        return ResponseEntity.ok(clienteService.buscarTodos(usuario));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Cliente> salvar(@AuthenticationPrincipal Usuario usuario, @RequestBody ClienteDto clienteDto) {
        return ResponseEntity.ok(clienteService.salvar(usuario, clienteDto));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Cliente> atualizar(@AuthenticationPrincipal Usuario usuario, @RequestBody ClienteDto clienteDto) {
        return ResponseEntity.ok(clienteService.atualizar(usuario, clienteDto));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<Cliente> apagar(@AuthenticationPrincipal Usuario usuario, @RequestBody ClienteDto clienteDto) {
        return ResponseEntity.ok(clienteService.apagar(usuario, clienteDto));
    }

}
