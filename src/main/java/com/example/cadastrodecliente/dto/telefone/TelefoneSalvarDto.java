package com.example.cadastrodecliente.dto.telefone;

import com.example.cadastrodecliente.model.Cliente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TelefoneSalvarDto {

    private Long id;
    private String numero;
    private Cliente cliente;

    public TelefoneSalvarDto(Long id, String numero, Cliente cliente) {
        this.id = id;
        this.numero = numero;
        this.cliente = cliente;
    }
}
