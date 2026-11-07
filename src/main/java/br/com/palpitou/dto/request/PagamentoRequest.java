package br.com.palpitou.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record PagamentoRequest(
    @NotNull(message = "O valor é obrigatório")
    @Positive(message = "O valor deve ser maior que zero")
    BigDecimal valor,

    @NotBlank(message = "O nome do pagador Pix é obrigatório")
    String nomePagadorPix,

    String comprovante,

    @NotNull(message = "O ID do usuário é obrigatório")
    Long userId,

    @NotNull(message = "O ID do bolão é obrigatório")
    Long bolaoId
) {}
