package br.com.palpitou.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record ParticipacaoRequest(
    @PositiveOrZero(message = "Os pontos não podem ser negativos")
    int pontos,

    @NotNull(message = "O ID do usuário é obrigatório")
    Long userId,

    @NotNull(message = "O ID do bolão é obrigatório")
    Long bolaoId,

    @NotNull(message = "O ID do pagamento é obrigatório")
    Long pagamentoId
) {}
