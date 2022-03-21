package com.example.cadastrodecliente.controller;

import java.util.List;

import javax.transaction.Transactional;

import com.example.cadastrodecliente.dto.TelefoneDto;
import com.example.cadastrodecliente.model.Cliente;
import com.example.cadastrodecliente.model.Telefone;
import com.example.cadastrodecliente.repository.ClienteRepository;
import com.example.cadastrodecliente.repository.TelefoneRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/telefones")
public class TelefoneController {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    TelefoneRepository telefoneRepository;

    @GetMapping("/{cliente_id}")
    public ResponseEntity<List<Telefone>> buscarTelefones(@PathVariable Long cliente_id) {

        Cliente cliente = clienteRepository.findById(cliente_id).get();

        List<Telefone> telefones = telefoneRepository.findTelefoneByCliente(cliente);

        return ResponseEntity.ok(telefones);

    }

    @PostMapping("/{cliente_id}")
    @Transactional
    public ResponseEntity<Telefone> cadastrarTelefone(@PathVariable Long cliente_id,
            @RequestBody TelefoneDto telefoneDto) {

        Cliente cliente = clienteRepository.findById(cliente_id).get();

        Telefone telefone = new Telefone(telefoneDto.getNumero(), cliente);

        telefoneRepository.save(telefone);

        return ResponseEntity.ok(telefone);

    }

    @DeleteMapping("/{telefone_id}")
    @Transactional
    public ResponseEntity<Telefone> deletarTelefone(@PathVariable Long telefone_id) {

        Telefone telefone = telefoneRepository.findById(telefone_id).get();

        telefoneRepository.delete(telefone);

        return ResponseEntity.ok(telefone);

    }

}
