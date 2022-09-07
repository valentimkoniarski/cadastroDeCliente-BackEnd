package com.example.cadastrodecliente.repository;

import com.example.cadastrodecliente.model.Cliente;
import com.example.cadastrodecliente.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long>  {

    List<Produto> findProdutoByCliente(Cliente cliente);

    void deleteProdutoByCliente(Cliente cliente);
}
