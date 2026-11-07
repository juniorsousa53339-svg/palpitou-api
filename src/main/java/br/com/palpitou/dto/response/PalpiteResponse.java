package br.com.palpitou.dto.response;

public record PalpiteResponse(
    int golsMandante,
    int golsVisitante,
    int pontosObtidos,
    Long userId
) {}
