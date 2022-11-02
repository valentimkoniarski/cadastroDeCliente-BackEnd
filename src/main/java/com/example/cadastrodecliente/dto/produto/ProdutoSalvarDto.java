package com.example.cadastrodecliente.dto.produto;

import com.example.cadastrodecliente.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoSalvarDto {

    private Long id;
    private Cliente cliente;
    private String nome;
    private String valor;
    private int quantidade;
    private int vendidos;

    public ProdutoSalvarDto(Long id, Cliente cliente, String nome, String valor, int quantidade, int vendidos) {
        this.id = id;
        this.cliente = cliente;
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.vendidos = vendidos;
    }
}
