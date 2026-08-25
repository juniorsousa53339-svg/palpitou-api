package br.com.palpitou.dto;


import br.com.palpitou.enums.StatusGlobal;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter
public class PutResponseJogo {
    private Long id;
    private LocalDate data;
    private LocalDateTime dataHora;
    private int rodada;
    private int golsMandante;
    private int golsVisitante;
    private StatusGlobal status;
}
