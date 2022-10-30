package com.example.cadastrodecliente.dto.produto;

import com.example.cadastrodecliente.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProdutoSalvarDto {

    private Long id;
    private Cliente cliente;
    private String nome;
    private String valor;
    private int quantidade;
    private int vendidos;

}
