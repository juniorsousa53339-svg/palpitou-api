package br.com.palpitou.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class PremiacaoRequest {

    private Integer posicao;
    private BigDecimal valor;
    private String descricao;
    private Long bolaoId;
}
