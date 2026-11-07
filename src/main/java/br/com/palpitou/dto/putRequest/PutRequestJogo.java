package br.com.palpitou.dto.putRequest;

import br.com.palpitou.enums.StatusGlobal;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record PutRequestJogo(
    LocalDate data,

    @NotNull(message = "A data e hora são obrigatórias")
    LocalDateTime dataHora,

    @Min(value = 1, message = "A rodada deve ser maior ou igual a 1")
    int rodada,

    @PositiveOrZero(message = "Os gols do mandante não podem ser negativos")
    int golsMandante,

    @PositiveOrZero(message = "Os gols do visitante não podem ser negativos")
    int golsVisitante,

    @NotNull(message = "O status é obrigatório")
    StatusGlobal status
) {}
