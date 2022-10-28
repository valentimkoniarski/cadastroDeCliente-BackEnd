package com.example.cadastrodecliente.dto;

import com.example.cadastrodecliente.model.Cliente;
import com.example.cadastrodecliente.model.ImagensProduto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class ProdutoDto {

    private Cliente cliente;

    private List<ImagensProdutoDto> imagensProdutos;

    private String nome;

    private String valor;

    private int quantidade;

    private int vendidos;

    public ProdutoDto(Cliente cliente, List<ImagensProdutoDto> imagensProdutos, String nome, String valor, int quantidade, int vendidos) {
        this.cliente = cliente;
        this.imagensProdutos = imagensProdutos;
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.vendidos = vendidos;
    }
}