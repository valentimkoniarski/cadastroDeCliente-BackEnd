package com.example.cadastrodecliente.model;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cliente")
@Getter
@Setter
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonBackReference
    Usuario usuario;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cliente")
    @JsonManagedReference
    private List<Endereco> endereco;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cliente")
    @JsonManagedReference
    private List<Telefone> telefone;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cliente")
    @JsonManagedReference
    private List<Produto> produto;

    private String nome;
    private String numDocumento;

    @Enumerated(EnumType.STRING)
    private TipoPessoaCliente tipoPessoa;

    public Cliente(Usuario usuario, String nome, String numDocumento, TipoPessoaCliente tipoPessoa) {
        this.usuario = usuario;
        this.nome = nome;
        this.numDocumento = numDocumento;
        this.tipoPessoa = tipoPessoa;
    }

    public Cliente() {
    }

}
