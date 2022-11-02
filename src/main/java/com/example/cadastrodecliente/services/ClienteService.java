package com.example.cadastrodecliente.services;

import com.example.cadastrodecliente.dto.cliente.ClienteDto;
import com.example.cadastrodecliente.dto.cliente.ClienteSalvarDto;
import com.example.cadastrodecliente.model.Cliente;
import com.example.cadastrodecliente.model.Usuario;
import com.example.cadastrodecliente.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ModelMapper modelMapper;

    public List<Cliente> buscarTodos(Usuario usuario) {
        return clienteRepository.findClienteByUsuario(usuario.getId());
    }

    public Cliente salvar(Usuario usuario, ClienteDto clienteDto) {
        ClienteSalvarDto clienteSalvarDto =
                new ClienteSalvarDto(
                        null,
                        usuario,
                        clienteDto.getNome(),
                        clienteDto.getNumDocumento(),
                        clienteDto.getTipoPessoa()
                );
        Cliente cliente = modelMapper.map(clienteSalvarDto, Cliente.class);
        clienteRepository.save(cliente);
        return cliente;
    }

    public Cliente atualizar(Usuario usuario, ClienteDto clienteDto) {

        ClienteSalvarDto clienteSalvarDto =
                new ClienteSalvarDto(
                        clienteDto.getId(),
                        usuario,
                        clienteDto.getNome(),
                        clienteDto.getNumDocumento(),
                        clienteDto.getTipoPessoa()
                );
        Cliente cliente = modelMapper.map(clienteSalvarDto, Cliente.class);
        clienteRepository.save(cliente);
        return cliente;
    }

    public Cliente apagar(Usuario usuario, ClienteDto clienteDto) {
        Cliente cliente = clienteRepository.findClienteByIdAndUsuario(clienteDto.getId(), usuario);
        clienteRepository.delete(cliente);
        return cliente;
    }


}
