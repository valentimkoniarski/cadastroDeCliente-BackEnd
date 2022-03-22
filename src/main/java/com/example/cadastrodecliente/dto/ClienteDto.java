package com.example.cadastrodecliente.dto;

//import java.util.List;
//import com.example.cadastrodecliente.model.Endereco;
//import com.example.cadastrodecliente.model.Telefone;
import com.example.cadastrodecliente.model.TipoPessoaCliente;

public class ClienteDto {

    private String nome;
    private String numDocumento;
    private TipoPessoaCliente tipoPessoa;

    //private List<Telefone> telefone;
    //private List<Endereco> endereco;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public TipoPessoaCliente getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(TipoPessoaCliente tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    /* 
    public List<Telefone> getTelefone() {
        return telefone;
    }

    public void setTelefone(List<Telefone> telefone) {
        this.telefone = telefone;
    }

    public List<Endereco> getEndereco() {
        return endereco;
    }

    public void setEndereco(List<Endereco> endereco) {
        this.endereco = endereco;
    } 
    */

}
