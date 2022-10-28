package com.example.cadastrodecliente.dto;

import com.example.cadastrodecliente.model.ImagensProduto;
import lombok.Data;

import java.util.List;

@Data
public class ProdutoResponseDto {

    private Long id;

    private List<ImagensProduto> imagensProdutos;

    private String nome;

    private String valor;

    private int quantidade;

    private int vendidos;

    public ProdutoResponseDto(Long id, List<ImagensProduto> imagensProdutos, String nome, String valor, int quantidade, int vendidos) {
        this.id = id;
        this.imagensProdutos = imagensProdutos;
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.vendidos = vendidos;
    }

}
