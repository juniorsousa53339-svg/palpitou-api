package br.com.palpitou.dto.response;

import br.com.palpitou.enums.StatusGlobal;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record BolaoResponse(
    String nome,
    BigDecimal valorInscricao,
    LocalDateTime dataInicio,
    LocalDateTime dataFim,
    StatusGlobal status
) {}
