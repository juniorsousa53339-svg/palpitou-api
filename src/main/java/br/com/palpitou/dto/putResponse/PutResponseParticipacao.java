package br.com.palpitou.dto.putResponse;

import br.com.palpitou.enums.StatusParticipacao;

public record PutResponseParticipacao(
    int pontos,
    StatusParticipacao status
) {}
