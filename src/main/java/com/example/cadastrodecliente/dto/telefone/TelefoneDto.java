package com.example.cadastrodecliente.dto.telefone;

import com.example.cadastrodecliente.dto.cliente.ClienteDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TelefoneDto {

    private Long id;
    private String numero;

    public TelefoneDto(Long id, String numero) {
        this.id = id;
        this.numero = numero;
    }
}
