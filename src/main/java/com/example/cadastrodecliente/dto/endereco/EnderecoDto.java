package com.example.cadastrodecliente.dto.endereco;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDto {

    private Long id;
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private Boolean principal;

}
