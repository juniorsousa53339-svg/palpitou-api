package br.com.palpitou.dto.response;

public record RankingResponse(
    int posicao,
    String nome,
    int pontuacao
) {}
