package br.com.palpitou.dto.response;

import java.math.BigDecimal;

public record PremiacaoResponse(
    Long userId,
    Long bolaoId,
    int posicao,
    BigDecimal valor,
    String descricao
) {}
