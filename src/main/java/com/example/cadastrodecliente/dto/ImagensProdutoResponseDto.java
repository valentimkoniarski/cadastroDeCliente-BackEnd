package com.example.cadastrodecliente.dto;

import com.example.cadastrodecliente.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ImagensProdutoResponseDto {

    private Long id;

    private Produto produto;

    private String nome;

    private String url;

    private Boolean destaque;

}
