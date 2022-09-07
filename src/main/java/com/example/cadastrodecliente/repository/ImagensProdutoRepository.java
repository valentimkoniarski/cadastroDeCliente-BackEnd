package com.example.cadastrodecliente.repository;

import com.example.cadastrodecliente.model.Cliente;
import com.example.cadastrodecliente.model.Endereco;
import com.example.cadastrodecliente.model.ImagensProduto;
import com.example.cadastrodecliente.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImagensProdutoRepository extends JpaRepository<ImagensProduto, Long> {

    List<ImagensProduto> findImagensByProduto(Produto produto);

    void deleteImagensByProduto(Produto produto);

}
