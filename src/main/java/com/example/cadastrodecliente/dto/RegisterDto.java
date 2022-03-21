package com.example.cadastrodecliente.dto;

import com.example.cadastrodecliente.model.Usuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class RegisterDto {

    private String nome;
    private String email;
    private String senha;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Usuario toUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(new BCryptPasswordEncoder().encode(senha));

        return usuario;
    }


}
