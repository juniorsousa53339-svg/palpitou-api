package br.com.palpitou.dto.putRequest;

import br.com.palpitou.enums.StatusParticipacao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PutRequestParticipacao {
    private int pontos;
    private StatusParticipacao status;
}
