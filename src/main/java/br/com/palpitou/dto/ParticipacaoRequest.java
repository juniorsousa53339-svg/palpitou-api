package br.com.palpitou.dto;

import br.com.palpitou.enums.StatusParticipacao;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class ParticipacaoRequest {

    private StatusParticipacao status;
    private LocalDate dataInscricao;
    private int pontos;
    private Long userId;
    private Long pagamentoId;
    private Long bolaoId;
}
