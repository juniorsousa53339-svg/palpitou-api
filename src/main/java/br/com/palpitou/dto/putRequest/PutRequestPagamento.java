package br.com.palpitou.dto.putRequest;

import br.com.palpitou.enums.StatusPagamento;
import jakarta.validation.constraints.NotNull;

public record PutRequestPagamento(
    @NotNull(message = "O status do pagamento é obrigatório")
    StatusPagamento status
) {}
