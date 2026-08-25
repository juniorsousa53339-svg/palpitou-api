package br.com.palpitou.dto;

import br.com.palpitou.enums.StatusParticipacao;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ParticipacaoResponse {

    private StatusParticipacao status;
    private LocalDate dataInscricao;
    private int pontos;
}
