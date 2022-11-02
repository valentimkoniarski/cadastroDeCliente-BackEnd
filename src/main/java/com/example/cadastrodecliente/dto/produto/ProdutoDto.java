package com.example.cadastrodecliente.dto.produto;

import com.example.cadastrodecliente.dto.cliente.ClienteDto;
import com.example.cadastrodecliente.dto.imagensProduto.ImagensProdutoDto;
import lombok.*;
import java.util.List;

@Getter
@Setter
public class ProdutoDto {

    private Long id;
    private ClienteDto clienteDto;
    private List<ImagensProdutoDto> imagens;
    private String nome;
    private String valor;
    private int quantidade;
    private int vendidos;

    public ProdutoDto(Long id, ClienteDto clienteDto, List<ImagensProdutoDto> imagens, String nome, String valor, int quantidade, int vendidos) {
        this.id = id;
        this.clienteDto = clienteDto;
        this.imagens = imagens;
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.vendidos = vendidos;
    }
}
