package br.com.palpitou.dto;

import br.com.palpitou.entity.Campeonato;
import br.com.palpitou.entity.Time;
import br.com.palpitou.enums.StatusGlobal;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter @Setter
public class JogoRequest {

    private LocalDateTime dataHora;
    private int rodada;

    private StatusGlobal status;

    private Campeonato campeonato;
    private Time timeMandante;
    private Time timeVisitante;
}
