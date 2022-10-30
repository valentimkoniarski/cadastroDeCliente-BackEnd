package com.example.cadastrodecliente.services;

import com.example.cadastrodecliente.dto.ImagensProdutoDto;
import com.example.cadastrodecliente.dto.ProdutoDto;
import com.example.cadastrodecliente.dto.ProdutoResponseDto;
import com.example.cadastrodecliente.model.Cliente;
import com.example.cadastrodecliente.model.ImagensProduto;
import com.example.cadastrodecliente.model.Produto;
import com.example.cadastrodecliente.model.Usuario;
import com.example.cadastrodecliente.repository.ClienteRepository;
import com.example.cadastrodecliente.repository.ImagensProdutoRepository;
import com.example.cadastrodecliente.repository.ProdutoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProdutoService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ImagensProdutoRepository imagensProdutoRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    ModelMapper modelMapper;

    @Transactional
    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id).get();
    }

    @Transactional
    public List<Produto> buscarTodos(Usuario usuario) {
        Cliente cliente = clienteRepository.findById(usuario.getId()).get();
        return produtoRepository.findProdutosByCliente(cliente);
    }

    @Transactional
    public Produto apagarProduto(Usuario usuario, Long id) {
        Cliente cliente = clienteRepository.findById(usuario.getId()).get();
        Produto produto = produtoRepository.findProdutoByIdAndClienteId(id, cliente.getId());
        produtoRepository.delete(produto);
        return produto;
    }

    public Produto salvarProduto(Usuario usuario, ProdutoDto produtoDto) {

        Cliente cliente = clienteRepository.findById(usuario.getId()).get();

        ProdutoDto produtoSaveDto = new ProdutoDto(
                cliente,
                null,
                produtoDto.getNome(),
                produtoDto.getValor(),
                produtoDto.getQuantidade(),
                produtoDto.getVendidos()
        );

        Produto produto = modelMapper.map(produtoSaveDto, Produto.class);
        produtoRepository.save(produto);

        List<ImagensProduto> listaImagens = new ArrayList<>();

        for (ImagensProdutoDto img : produtoDto.getImagensProdutos()) {
            ImagensProdutoDto imagensProdutoDto = new ImagensProdutoDto(null, produto, img.getNome(), img.getUrl(), img.getDestaque());
            listaImagens.add(imagensProdutoRepository.save(modelMapper.map(imagensProdutoDto, ImagensProduto.class)));
        }

        ProdutoResponseDto produtoResponseDto = new ProdutoResponseDto(
                produto.getId(),
                listaImagens,
                produtoDto.getNome(),
                produtoDto.getValor(),
                produtoDto.getQuantidade(),
                produtoDto.getVendidos()
        );
        return modelMapper.map(produtoResponseDto, Produto.class);

    }


}
