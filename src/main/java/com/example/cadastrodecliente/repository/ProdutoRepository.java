package com.example.cadastrodecliente.repository;

import com.example.cadastrodecliente.model.Cliente;
import com.example.cadastrodecliente.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long>  {

    List<Produto> findProdutosByCliente(Cliente cliente);
    Produto findProdutoByIdAndClienteId(Long id, Long clienteId);

}
