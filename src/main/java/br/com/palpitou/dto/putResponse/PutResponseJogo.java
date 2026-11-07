package br.com.palpitou.dto.putResponse;

import br.com.palpitou.enums.StatusGlobal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record PutResponseJogo(
    Long id,
    LocalDate data,
    LocalDateTime dataHora,
    int rodada,
    int golsMandante,
    int golsVisitante,
    StatusGlobal status
) {}
