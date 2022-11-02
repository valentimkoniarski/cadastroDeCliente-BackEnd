package com.example.cadastrodecliente.repository;

import java.util.List;

import com.example.cadastrodecliente.model.Cliente;

import com.example.cadastrodecliente.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query(value = "SELECT * FROM cliente WHERE usuario_id = :userId", nativeQuery = true)
    List<Cliente> findClienteByUsuario(@Param("userId") Long userId);

    Cliente findClienteByIdAndUsuario(Long clienteId, Usuario usuario);

}
