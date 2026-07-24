package br.com.palpitou.dto;

import br.com.palpitou.enums.StatusParticipacao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PutRequestParticipacao {
    private int pontos;
    private StatusParticipacao status;
}
