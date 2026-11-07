package br.com.palpitou.dto.response;

import br.com.palpitou.enums.StatusGlobal;
import java.time.LocalDateTime;

public record JogoResponse(
    Long id,
    LocalDateTime dataHora,
    int rodada,
    StatusGlobal status,
    Long campeonatoId,
    Long timeMandanteId,
    Long timeVisitanteId
) {}
