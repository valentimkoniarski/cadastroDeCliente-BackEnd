package com.example.cadastrodecliente.services;

import com.example.cadastrodecliente.dto.imagensProduto.ImagensProdutoDto;
import com.example.cadastrodecliente.dto.produto.ProdutoDto;
import com.example.cadastrodecliente.dto.produto.ProdutoResponseDto;
import com.example.cadastrodecliente.dto.produto.ProdutoSalvarDto;
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
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import static java.util.Objects.nonNull;
import static org.springframework.util.ObjectUtils.isEmpty;

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
    public List<Produto> buscarTodos(Long clienteId, Usuario usuario) {
        Cliente cliente = clienteRepository.findClienteByIdAndUsuario(clienteId, usuario);
        return produtoRepository.findProdutosByCliente(cliente);
    }

    @Transactional
    public Produto apagar(Long clienteId, Usuario usuario, ProdutoDto produtoDto) {
        Cliente cliente = clienteRepository.findClienteByIdAndUsuario(clienteId, usuario);
        Produto produto = produtoRepository.findProdutoByIdAndClienteId(produtoDto.getId(), cliente.getId());
        produtoRepository.delete(produto);
        return produto;
    }

    @Transactional
    public Produto salvar(Long clienteId, Usuario usuario, ProdutoDto produtoDto) throws Exception {
        if (!isValidSave(produtoDto)) {
            throw new Exception("Não foi possivel salvar o registro");
        }
        return getProdutoResponse(clienteId, usuario, produtoDto);
    }

    @Transactional
    public Produto atualizar(Long clienteId, Usuario usuario, ProdutoDto produtoDto) throws Exception {
        if (!isValidUpdate(produtoDto)) {
            throw new Exception("Não foi possivel atualizar o registro");
        }
        return getProdutoResponse(clienteId, usuario, produtoDto);
    }

    protected Produto getProdutoResponse(Long clienteId, Usuario usuario, ProdutoDto produtoDto) throws Exception {
        Produto produto = this.saveProduto(clienteId, usuario, produtoDto);
        List<ImagensProduto> imagensProduto = this.saveImagensProduto(produto, produtoDto.getImagens());

        ProdutoResponseDto produtoResponseDto = new ProdutoResponseDto(
                produto.getId(),
                imagensProduto,
                produtoDto.getNome(),
                produtoDto.getValor(),
                produtoDto.getQuantidade(),
                produtoDto.getVendidos()
        );

        return modelMapper.map(produtoResponseDto, Produto.class);
    }

    private Produto saveProduto(Long clienteId, Usuario usuario, ProdutoDto produtoDto) throws Exception {
        Cliente cliente = clienteRepository.findClienteByIdAndUsuario(clienteId, usuario);

        if (isEmpty(cliente)) {
            throw new Exception("Cliente não consta na base de dados do usuario logado");
        }

        ProdutoSalvarDto produtoSalvarDto = new ProdutoSalvarDto(
                produtoDto.getId(),
                cliente,
                produtoDto.getNome(),
                produtoDto.getValor(),
                produtoDto.getQuantidade(),
                produtoDto.getVendidos()
        );

        Produto produto = modelMapper.map(produtoSalvarDto, Produto.class);
        produtoRepository.save(produto);
        return produto;
    }

    private List<ImagensProduto> saveImagensProduto(Produto produto, List<ImagensProdutoDto> imagensProdutosDto) throws Exception {
        List<ImagensProduto> listaImagens = new ArrayList<>();

        if (!hasUmaImagemDestaque(imagensProdutosDto)) {
            throw new Exception("Apenas uma imagem pode ser destaque");
        }

        for (ImagensProdutoDto img : imagensProdutosDto) {
            ImagensProdutoDto imagensProdutoDto = new ImagensProdutoDto(img.getId(), produto, img.getNome(), img.getUrl(), img.getDestaque());
            listaImagens.add(imagensProdutoRepository.save(modelMapper.map(imagensProdutoDto, ImagensProduto.class)));
        }

        return listaImagens;
    }

    private Boolean isValidUpdate(ProdutoDto produtoDto) {

        AtomicReference<Boolean> isUpdate = new AtomicReference<>(true);

        if (isEmpty(produtoRepository.findById(produtoDto.getId()).get())) {
            isUpdate.set(false);
        }

        if (nonNull(produtoDto.getImagens())) {
            produtoDto.getImagens().stream().forEach(imagem -> {
                if (isEmpty(imagem.getId())) {
                    isUpdate.set(false);
                }
                if (!Objects.equals(imagensProdutoRepository.findById(imagem.getId()).get().getProduto().getId(), produtoDto.getId())) {
                    isUpdate.set(false);
                }
            });
        }
        return isUpdate.get();
    }

    private Boolean isValidSave(ProdutoDto produtoDto) {

        AtomicReference<Boolean> isSave = new AtomicReference<>(true);

        if (nonNull(produtoDto.getId())) {
            Produto hasProduto = produtoRepository.findById(produtoDto.getId()).get();
            if (nonNull(hasProduto)) {
                isSave.set(false);
            }
        }

        produtoDto.getImagens().stream().forEach(imagem -> {
            if (nonNull(imagem.getId())) {
                isSave.set(false);
            }
        });

        return isSave.get();
    }


    private Boolean hasUmaImagemDestaque(List<ImagensProdutoDto> imagensProdutosDto) {

        AtomicInteger count = new AtomicInteger();

        imagensProdutosDto.stream().forEach(img -> {
            if (img.getDestaque() == true) {
                count.getAndIncrement();
            }
        });

        if (count.get() > 1) {
            return false;
        }

        return true;
    }

}
