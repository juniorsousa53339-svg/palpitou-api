package br.com.palpitou.dto.response;

public record TimeResponse(
    Long id,
    String nome,
    String escudo,
    String sigla
) {}
