package com.example.cadastrodecliente.repository;

import java.util.List;

import com.example.cadastrodecliente.model.Cliente;
import com.example.cadastrodecliente.model.Telefone;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {

    List<Telefone> findTelefoneByCliente(Cliente cliente);

    void deleteTelefoneByCliente(Cliente cliente);

}
