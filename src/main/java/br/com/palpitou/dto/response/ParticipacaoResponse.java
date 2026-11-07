package br.com.palpitou.dto.response;

import br.com.palpitou.enums.StatusParticipacao;
import java.time.LocalDate;

public record ParticipacaoResponse(
    StatusParticipacao status,
    LocalDate dataInscricao,
    int pontos
) {}
