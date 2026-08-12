package br.com.palpitou.service;

import br.com.palpitou.entity.Jogo;
import br.com.palpitou.entity.Palpite;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PontuacaoService {

    // ========================
    // Métodos auxiliares
    // ========================
    private boolean placarExato(Jogo jogo, Palpite palpite) {

        return jogo.getGolsMandante() == palpite.getGolsMandante()
                && jogo.getGolsVisitante() == palpite.getGolsVisitante();
    }

    private boolean vencedorEGols(Jogo jogo, Palpite palpite){

    }


    // =========================
    // Regras de negócio
    // =========================

    private int calcularPontuacao(Jogo jogo, Palpite palpite) {

        int pontuacao = 0;

        if (placarExato(jogo, palpite)) {
            return 10;

        } else if (vencedorEGols(jogo, palpite)) {
            return 7;
        }

        return pontuacao;
    }

}
