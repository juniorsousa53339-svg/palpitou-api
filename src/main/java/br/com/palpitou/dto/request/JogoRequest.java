package br.com.palpitou.dto.request;

import br.com.palpitou.enums.StatusGlobal;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record JogoRequest(
    @NotNull(message = "A data e hora são obrigatórias")
    LocalDateTime dataHora,

    @Min(value = 1, message = "A rodada deve ser maior ou igual a 1")
    int rodada,

    @NotNull(message = "O status é obrigatório")
    StatusGlobal status,

    @NotNull(message = "O ID do campeonato é obrigatório")
    Long campeonatoId,

    @NotNull(message = "O ID do time mandante é obrigatório")
    Long timeMandanteId,

    @NotNull(message = "O ID do time visitante é obrigatório")
    Long timeVisitanteId
) {}
