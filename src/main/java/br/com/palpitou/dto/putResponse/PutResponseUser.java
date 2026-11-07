package br.com.palpitou.dto.putResponse;

public record PutResponseUser(
    String nome,
    String email,
    String senha
) {}
