package com.example.cadastrodecliente.controller;

import java.util.List;

import javax.transaction.Transactional;

import com.example.cadastrodecliente.dto.EnderecoDto;
import com.example.cadastrodecliente.model.Cliente;
import com.example.cadastrodecliente.model.Endereco;
import com.example.cadastrodecliente.repository.ClienteRepository;
import com.example.cadastrodecliente.repository.EnderecoRepository;

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
@RequestMapping(value = "/enderecos")
public class EnderecoController {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @GetMapping("/{id}")
    public ResponseEntity<List<Endereco>> buscarEnderecos(@PathVariable Long id) {

        Cliente cliente = clienteRepository.findById(id).get();

        List<Endereco> enderecos = enderecoRepository.findEnderecoByCliente(cliente);

        return ResponseEntity.ok(enderecos);

    }

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity<Endereco> cadastrarEndereco(@PathVariable Long id, @RequestBody EnderecoDto enderecoDto) {

        Cliente cliente = clienteRepository.findById(id).get();

        Endereco enderecoCadastrado = new Endereco(enderecoDto.getRua(), enderecoDto.getNumero(),
                enderecoDto.getBairro(),
                enderecoDto.getCidade(), enderecoDto.getPrincipal(), cliente);

        enderecoRepository.save(enderecoCadastrado);

        return ResponseEntity.ok(enderecoCadastrado);

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Endereco> deletarEndereco(@PathVariable Long id) {

        Endereco endereco = enderecoRepository.findById(id).get();

        enderecoRepository.delete(endereco);

        return ResponseEntity.ok(endereco);

    }

}
