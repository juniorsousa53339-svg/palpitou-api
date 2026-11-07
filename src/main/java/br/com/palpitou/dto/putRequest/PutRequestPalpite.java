package br.com.palpitou.dto.putRequest;

import jakarta.validation.constraints.PositiveOrZero;

public record PutRequestPalpite(
    @PositiveOrZero(message = "Os pontos obtidos não podem ser negativos")
    int pontosObtidos
) {}
