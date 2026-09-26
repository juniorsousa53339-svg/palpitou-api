package br.com.palpitou.dto.Dashboard;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DashboardResponse {

    private int totalBoloes;
    private int participantesAprovados;
    private int pagamentosPendentes;
    private int pagamentosAprovados;
    private int pagamentosRejeitados;
    private BigDecimal faturamento;
}
