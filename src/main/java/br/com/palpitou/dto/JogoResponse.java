package br.com.palpitou.dto;

import br.com.palpitou.enums.StatusGlobal;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class JogoResponse {

    private Long id;
    private LocalDateTime dataHora;
    private int rodada;

    private StatusGlobal status;

    private Long campeonatoId;
    private Long timeMandanteId;
    private Long timeVisitanteId;
}
