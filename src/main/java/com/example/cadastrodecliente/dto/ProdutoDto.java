package com.example.cadastrodecliente.dto;

import com.example.cadastrodecliente.model.Cliente;
import com.example.cadastrodecliente.model.ImagensProduto;
import com.example.cadastrodecliente.model.Produto;

import java.util.List;
import java.util.Set;

public class ProdutoDto {

    private Cliente cliente;

    private List<ImagensProduto> imagensProdutos;

    private String nome;

    private String valor;

    private int quantidade;

    public ProdutoDto(Cliente cliente, List<ImagensProduto> imagensProdutos, String nome, String valor, int quantidade, int vendidos) {
        this.cliente = cliente;
        this.imagensProdutos = imagensProdutos;
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.vendidos = vendidos;
    }

    private int vendidos;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ImagensProduto> getImagensProdutos() {
        return imagensProdutos;
    }

    public void setImagensProdutos(List<ImagensProduto> imagensProdutos) {
        this.imagensProdutos = imagensProdutos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getVendidos() {
        return vendidos;
    }

    public void setVendidos(int vendidos) {
        this.vendidos = vendidos;
    }
}
