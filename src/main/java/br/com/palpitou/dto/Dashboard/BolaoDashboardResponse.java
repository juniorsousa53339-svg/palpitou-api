package br.com.palpitou.dto.Dashboard;

import br.com.palpitou.enums.StatusGlobal;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class BolaoDashboardResponse {
    private String bolao;
    private String campeonato;
    private int participantesAprovados;
    private BigDecimal faturamento;
    private StatusGlobal status;
}
