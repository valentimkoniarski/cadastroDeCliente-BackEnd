package com.example.cadastrodecliente.dto.cliente;

import com.example.cadastrodecliente.model.TipoPessoaCliente;
import com.example.cadastrodecliente.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteSalvarDto {

    private Long id;
    private Usuario usuario;
    private String nome;
    private String numDocumento;
    private TipoPessoaCliente tipoPessoa;

}
