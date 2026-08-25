package br.com.palpitou.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PalpiteResponse {

    private int golsMandante;
    private int golsVisitante;
    private int pontosObtidos;
    private Long userId;
}
