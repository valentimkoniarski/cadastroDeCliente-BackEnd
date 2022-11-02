package com.example.cadastrodecliente.dto.cliente;

import com.example.cadastrodecliente.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto {

    private Long id;
    private String nome;
    private String numDocumento;
    private TipoPessoaCliente tipoPessoa;
}
