package br.com.palpitou.dto.putRequest;

import br.com.palpitou.enums.StatusGlobal;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class PutRequestJogo {

    private LocalDate data;
    private LocalDateTime dataHora;
    private int rodada;
    private int golsMandante;
    private int golsVisitante;
    private StatusGlobal status;
}
