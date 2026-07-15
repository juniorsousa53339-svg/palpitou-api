package br.com.palpitou.dto;

import br.com.palpitou.enums.StatusGlobal;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CampeonatoRequest {
    private String nome;
    private int temporada;
    private StatusGlobal status;
}
