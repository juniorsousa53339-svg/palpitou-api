package br.com.palpitou.dto.Dashboard;

import java.math.BigDecimal;

public record DashboardResponse(
    int totalBoloes,
    int participantesAprovados,
    int pagamentosPendentes,
    int pagamentosAprovados,
    int pagamentosRejeitados,
    BigDecimal faturamento
) {}
