package com.example.cadastrodecliente.repository;

import java.util.List;

import com.example.cadastrodecliente.model.Cliente;
import com.example.cadastrodecliente.model.Endereco;

import org.springframework.data.jpa.repository.JpaRepository;


public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    List<Endereco> findEnderecoByCliente(Cliente cliente);

    Endereco findEnderecoByIdAndCliente(Long enderecoId, Cliente cliente);



}
