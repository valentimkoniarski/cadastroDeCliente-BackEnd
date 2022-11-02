package com.example.cadastrodecliente.controller;

import java.util.List;
import javax.transaction.Transactional;
import com.example.cadastrodecliente.dto.telefone.TelefoneDto;
import com.example.cadastrodecliente.model.Telefone;
import com.example.cadastrodecliente.model.Usuario;
import com.example.cadastrodecliente.repository.ClienteRepository;
import com.example.cadastrodecliente.repository.TelefoneRepository;
import com.example.cadastrodecliente.services.TelefoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/telefones")
public class TelefoneController {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    TelefoneRepository telefoneRepository;

    @Autowired
    TelefoneService telefoneService;

    @GetMapping("/{clienteId}")
    public ResponseEntity<List<Telefone>> buscarTelefones(@PathVariable Long clienteId, @AuthenticationPrincipal Usuario usuario) {
        return ResponseEntity.ok(telefoneService.buscarTodos(clienteId, usuario));
    }

    @PostMapping("/{clienteId}")
    @Transactional
    public ResponseEntity<Telefone> salvar(@PathVariable Long clienteId, @AuthenticationPrincipal Usuario usuario, @RequestBody TelefoneDto telefoneDto) {
        return ResponseEntity.ok(telefoneService.salvar(clienteId, usuario, telefoneDto));
    }

    @PutMapping("/{clienteId}")
    @Transactional
    public ResponseEntity<Telefone> atualizar(@PathVariable Long clienteId, @AuthenticationPrincipal Usuario usuario, @RequestBody TelefoneDto telefoneDto) {
        return ResponseEntity.ok(telefoneService.atualizar(clienteId, usuario, telefoneDto));
    }

    @DeleteMapping("/{clienteId}")
    @Transactional
    public ResponseEntity<Telefone> apagar(@PathVariable Long clienteId, @AuthenticationPrincipal Usuario usuario, @RequestBody TelefoneDto telefoneDto) {
        return ResponseEntity.ok(telefoneService.apagar(clienteId, usuario, telefoneDto));
    }

}
