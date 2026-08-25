package br.com.palpitou.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter @Setter
public class PremiacaoResponse {

    private Long userId;
    private Long  bolaoId;
    private int posicao;
    private BigDecimal valor;
    private String descricao;
}
