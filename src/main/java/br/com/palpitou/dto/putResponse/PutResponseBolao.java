package br.com.palpitou.dto.putResponse;

import br.com.palpitou.enums.StatusGlobal;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PutResponseBolao(
    String nome,
    BigDecimal valorInscricao,
    LocalDateTime dataInicio,
    LocalDateTime dataFim,
    StatusGlobal status
) {}
