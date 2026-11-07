package br.com.palpitou.dto.request;

import br.com.palpitou.enums.StatusGlobal;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record BolaoRequest(
    @NotBlank(message = "O nome é obrigatório")
    String nome,

    @NotNull(message = "O valor de inscrição é obrigatório")
    @PositiveOrZero(message = "O valor de inscrição deve ser maior ou igual a zero")
    BigDecimal valorInscricao,

    @NotNull(message = "A data de início é obrigatória")
    LocalDateTime dataInicio,

    @NotNull(message = "A data de fim é obrigatória")
    LocalDateTime dataFim,

    @NotNull(message = "O status é obrigatório")
    StatusGlobal status,

    @NotNull(message = "O ID do campeonato é obrigatório")
    Long campeonatoId
) {}
