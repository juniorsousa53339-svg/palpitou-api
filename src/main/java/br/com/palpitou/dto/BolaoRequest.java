package br.com.palpitou.dto;

import br.com.palpitou.enums.StatusGlobal;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter @Setter
public class BolaoRequest {
    private String nome;
    private BigDecimal valorInscricao;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private StatusGlobal status;
    private Long campeonatoId;

}
