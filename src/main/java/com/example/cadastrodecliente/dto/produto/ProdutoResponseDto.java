package com.example.cadastrodecliente.dto.produto;

import com.example.cadastrodecliente.dto.ImagensProdutoDto;
import com.example.cadastrodecliente.model.ImagensProduto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProdutoResponseDto {

    private Long id;
    private List<ImagensProduto> imagensProdutos;
    private String nome;
    private String valor;
    private int quantidade;
    private int vendidos;

}
