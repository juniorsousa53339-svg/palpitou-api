package br.com.palpitou.dto.response;

import br.com.palpitou.enums.StatusPagamento;
import java.math.BigDecimal;

public record PagamentoResponse(
    BigDecimal valor,
    StatusPagamento status,
    String nomePagadorPix,
    String comprovante
) {}
