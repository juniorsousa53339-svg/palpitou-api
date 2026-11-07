package br.com.palpitou.dto.Dashboard;

import br.com.palpitou.enums.StatusGlobal;
import java.math.BigDecimal;

public record BolaoDashboardResponse(
    String bolao,
    String campeonato,
    int participantesAprovados,
    BigDecimal faturamento,
    StatusGlobal status
) {}
