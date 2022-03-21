package com.example.cadastrodecliente.controller;

import com.example.cadastrodecliente.dto.ClienteDto;
import com.example.cadastrodecliente.model.Cliente;
import com.example.cadastrodecliente.model.Endereco;
import com.example.cadastrodecliente.model.Telefone;
import com.example.cadastrodecliente.model.Usuario;
import com.example.cadastrodecliente.repository.ClienteRepository;
import com.example.cadastrodecliente.repository.EnderecoRepository;
import com.example.cadastrodecliente.repository.TelefoneRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private TelefoneRepository telefoneRepository;

    @GetMapping
    public ResponseEntity<List<Cliente>> listaDeClientes() {

        Usuario principal = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Cliente> clienteQuery = clienteRepository.findClienteByUsuario(principal.getId());

        return ResponseEntity.ok(clienteQuery);

    }

    @PostMapping
    @Transactional
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody ClienteDto clienteDto) {

        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Cliente cliente = new Cliente(usuario, clienteDto.getNome(), clienteDto.getNumDocumento(),
                clienteDto.getTipoPessoa());

        clienteRepository.saveAndFlush(cliente);

        List<Endereco> enderecos = new ArrayList<>();

        for (Endereco endereco : clienteDto.getEndereco()) {
            endereco.setCliente(cliente);
            enderecoRepository.saveAndFlush(endereco);
            enderecos.add(endereco);
        }

        List<Telefone> telefones = new ArrayList<>();

        for (Telefone telefone : clienteDto.getTelefone()) {
            telefone.setCliente(cliente);
            telefoneRepository.saveAndFlush(telefone);
            telefones.add(telefone);
        }

        return ResponseEntity.ok(cliente);

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Cliente> atualizarCliente(@RequestBody ClienteDto clienteDto, @PathVariable Long id) {

        Cliente cliente = clienteRepository.findById(id).get();

        cliente.setNome(clienteDto.getNome());
        cliente.setNumDocumento(clienteDto.getNumDocumento());
        cliente.setTipoPessoa(clienteDto.getTipoPessoa());

        clienteRepository.saveAndFlush(cliente);

        if (clienteDto.getEndereco() != null) {

            List<Endereco> enderecos = new ArrayList<>();

            enderecoRepository.deleteEnderecoByCliente(cliente);

            for (Endereco endereco : clienteDto.getEndereco()) {
                endereco.setCliente(cliente);
                enderecoRepository.saveAndFlush(endereco);
                enderecos.add(endereco);
            }
        }

        if (clienteDto.getTelefone() != null) {

            List<Telefone> telefones = new ArrayList<>();

            telefoneRepository.deleteTelefoneByCliente(cliente);

            for (Telefone telefone : clienteDto.getTelefone()) {
                telefone.setCliente(cliente);
                telefoneRepository.saveAndFlush(telefone);
                telefones.add(telefone);
            }
        }

        return ResponseEntity.ok(cliente);

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Cliente> deletarCliente(@PathVariable Long id) {

        Cliente cliente = clienteRepository.findById(id).get();

        clienteRepository.delete(cliente);

        return ResponseEntity.ok(cliente);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarCliente(@PathVariable Long id) {

        Cliente cliente = clienteRepository.findById(id).get();

        return ResponseEntity.ok(cliente);

    }

    

}
