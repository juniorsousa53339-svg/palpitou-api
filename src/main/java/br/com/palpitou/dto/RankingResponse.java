package br.com.palpitou.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RankingResponse {

    private int posicao;
    private String nome;
    private int pontuacao;
}
