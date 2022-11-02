package com.example.cadastrodecliente.dto.endereco;

import com.example.cadastrodecliente.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EnderecoSalvarDto {

    private Long id;
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private Boolean principal;
    private Cliente cliente;
}
