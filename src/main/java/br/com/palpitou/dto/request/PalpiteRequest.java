package br.com.palpitou.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record PalpiteRequest(
    @PositiveOrZero(message = "Os gols do mandante não podem ser negativos")
    int golsMandante,

    @PositiveOrZero(message = "Os gols do visitante não podem ser negativos")
    int golsVisitante,

    @NotNull(message = "O ID do jogo é obrigatório")
    Long jogoId,

    @NotNull(message = "O ID do usuário é obrigatório")
    Long userId
) {}
