package com.example.cadastrodecliente.dto.produto;

import com.example.cadastrodecliente.dto.ImagensProdutoDto;
import com.example.cadastrodecliente.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class ProdutoDto {

    private Long id;
    private Cliente cliente;
    private List<ImagensProdutoDto> imagensProdutos;
    private String nome;
    private String valor;
    private int quantidade;
    private int vendidos;

}
