package com.example.cadastrodecliente.services;

import com.example.cadastrodecliente.dto.telefone.TelefoneDto;
import com.example.cadastrodecliente.dto.telefone.TelefoneSalvarDto;
import com.example.cadastrodecliente.model.Cliente;
import com.example.cadastrodecliente.model.Telefone;
import com.example.cadastrodecliente.model.Usuario;
import com.example.cadastrodecliente.repository.ClienteRepository;
import com.example.cadastrodecliente.repository.TelefoneRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TelefoneService {

    @Autowired
    TelefoneRepository telefoneRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ModelMapper modelMapper;

    public List<Telefone> buscarTodos(Long clienteId, Usuario usuario) {
        Cliente cliente = clienteRepository.findClienteByIdAndUsuario(clienteId, usuario);
        return telefoneRepository.findTelefoneByCliente(cliente);
    }

    public Telefone salvar(Long clienteId, Usuario usuario, TelefoneDto telefoneDto) {
        Cliente cliente = clienteRepository.findClienteByIdAndUsuario(clienteId, usuario);

        TelefoneSalvarDto telefoneSalvarDto =
                new TelefoneSalvarDto(
                        null,
                        telefoneDto.getNumero(),
                        cliente
                );
        Telefone telefone = modelMapper.map(telefoneSalvarDto, Telefone.class);
        telefoneRepository.save(telefone);
        return telefone;
    }

    public Telefone atualizar(Long clienteId, Usuario usuario, TelefoneDto telefoneDto) {
        Cliente cliente = clienteRepository.findClienteByIdAndUsuario(clienteId, usuario);

        TelefoneSalvarDto telefoneSalvarDto =
                new TelefoneSalvarDto(
                        telefoneDto.getId(),
                        telefoneDto.getNumero(),
                        cliente
                );
        Telefone telefone = modelMapper.map(telefoneSalvarDto, Telefone.class);
        telefoneRepository.save(telefone);
        return telefone;
    }

    public Telefone apagar(Long clienteId, Usuario usuario, TelefoneDto telefoneDto) {
        Cliente cliente = clienteRepository.findClienteByIdAndUsuario(clienteId, usuario);
        Telefone telefone = telefoneRepository.findTelefoneByIdAndCliente(telefoneDto.getId(), cliente);
        telefoneRepository.delete(telefone);
        return telefone;
    }

}
