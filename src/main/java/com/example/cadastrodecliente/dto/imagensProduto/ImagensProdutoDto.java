package com.example.cadastrodecliente.dto.imagensProduto;

import com.example.cadastrodecliente.model.Produto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImagensProdutoDto {

    private Long id;
    @JsonBackReference
    private Produto produto;
    private String nome;
    private String url;
    private Boolean destaque;
}
