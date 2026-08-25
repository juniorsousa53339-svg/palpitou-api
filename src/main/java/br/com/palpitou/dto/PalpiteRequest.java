package br.com.palpitou.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PalpiteRequest {

    private int golsMandante;
    private int golsVisitante;
    private Long jogoId;
    private Long userId;
}
