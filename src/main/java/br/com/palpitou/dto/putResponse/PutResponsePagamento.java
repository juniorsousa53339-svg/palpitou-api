package br.com.palpitou.dto.putResponse;

import br.com.palpitou.enums.StatusPagamento;

public record PutResponsePagamento(
    StatusPagamento status
) {}
