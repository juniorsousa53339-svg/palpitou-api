package br.com.palpitou.dto.request;

import br.com.palpitou.enums.StatusParticipacao;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ParticipacaoRequest {

    private StatusParticipacao status;
    private int pontos;
    private Long userId;
    private Long bolaoId;
    private Long pagamentoId;
}
