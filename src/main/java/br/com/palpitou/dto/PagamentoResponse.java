package br.com.palpitou.dto;


import br.com.palpitou.enums.StatusPagamento;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class PagamentoResponse {

    private BigDecimal valor;
    private StatusPagamento status;
    private String nomePagadorPix;
    private String comprovante;
}
