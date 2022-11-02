package com.example.cadastrodecliente.services;

import com.example.cadastrodecliente.dto.endereco.EnderecoDto;
import com.example.cadastrodecliente.dto.endereco.EnderecoSalvarDto;
import com.example.cadastrodecliente.model.Cliente;
import com.example.cadastrodecliente.model.Endereco;
import com.example.cadastrodecliente.model.Usuario;
import com.example.cadastrodecliente.repository.ClienteRepository;
import com.example.cadastrodecliente.repository.EnderecoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EnderecoService {

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ModelMapper modelMapper;

    public List<Endereco> buscarTodos(Long clienteId, Usuario usuario) {
        Cliente cliente = clienteRepository.findClienteByIdAndUsuario(clienteId, usuario);
        return enderecoRepository.findEnderecoByCliente(cliente);
    }

    public Endereco salvar(Long clienteId, Usuario usuario, EnderecoDto enderecoDto) {
        Cliente cliente = clienteRepository.findClienteByIdAndUsuario(clienteId, usuario);
        EnderecoSalvarDto enderecoSalvarDto =
                new EnderecoSalvarDto(
                        null,
                        enderecoDto.getRua(),
                        enderecoDto.getNumero(),
                        enderecoDto.getBairro(),
                        enderecoDto.getCidade(),
                        enderecoDto.getPrincipal(),
                        cliente
                );
        Endereco endereco = modelMapper.map(enderecoSalvarDto, Endereco.class);
        enderecoRepository.save(endereco);
        return endereco;
    }

    public Endereco atualizar(Long clienteId, Usuario usuario, EnderecoDto enderecoDto) {
        Cliente cliente = clienteRepository.findClienteByIdAndUsuario(clienteId, usuario);
        EnderecoSalvarDto enderecoSalvarDto =
                new EnderecoSalvarDto(
                        enderecoDto.getId(),
                        enderecoDto.getRua(),
                        enderecoDto.getNumero(),
                        enderecoDto.getBairro(),
                        enderecoDto.getCidade(),
                        enderecoDto.getPrincipal(),
                        cliente
                );
        Endereco endereco = modelMapper.map(enderecoSalvarDto, Endereco.class);
        enderecoRepository.save(endereco);
        return endereco;
    }

    public Endereco apagar(Long clienteId, Usuario usuario, EnderecoDto enderecoDto) {
        Cliente cliente = clienteRepository.findClienteByIdAndUsuario(clienteId, usuario);
        Endereco endereco = enderecoRepository.findEnderecoByIdAndCliente(enderecoDto.getId(), cliente);
        enderecoRepository.delete(endereco);
        return endereco;
    }


}
