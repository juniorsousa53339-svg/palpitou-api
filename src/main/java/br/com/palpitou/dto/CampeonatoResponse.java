package br.com.palpitou.dto;

import br.com.palpitou.enums.StatusGlobal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CampeonatoResponse {
    private Long id;
    private String nome;
    private int temporada;
    private StatusGlobal status;
}
