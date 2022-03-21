package com.example.cadastrodecliente.dto;

public class TokenDto {

    private String token;
    private String tipo;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public TokenDto(String token, String tipo) {
        this.token = token;
        this.tipo = tipo;
    }

}
