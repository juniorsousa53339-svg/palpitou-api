package br.com.palpitou.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record PremiacaoRequest(
    @NotNull(message = "A posição é obrigatória")
    @Min(value = 1, message = "A posição deve ser maior ou igual a 1")
    Integer posicao,

    @NotNull(message = "O valor é obrigatório")
    @Positive(message = "O valor deve ser maior que zero")
    BigDecimal valor,

    @NotBlank(message = "A descrição é obrigatória")
    String descricao,

    @NotNull(message = "O ID do bolão é obrigatório")
    Long bolaoId
) {}
