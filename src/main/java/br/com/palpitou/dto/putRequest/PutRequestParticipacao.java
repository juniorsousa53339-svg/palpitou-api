package br.com.palpitou.dto.putRequest;

import br.com.palpitou.enums.StatusParticipacao;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record PutRequestParticipacao(
    @PositiveOrZero(message = "Os pontos não podem ser negativos")
    int pontos,

    @NotNull(message = "O status da participação é obrigatório")
    StatusParticipacao status
) {}
