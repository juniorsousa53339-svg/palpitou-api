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

    private boolean mandanteVenceu(Jogo jogo) {
        return jogo.getGolsMandante() > jogo.getGolsVisitante();
    }

    private boolean visitanteVenceu(Jogo jogo) {
        return jogo.getGolsMandante() < jogo.getGolsVisitante();
    }

    private boolean mandanteFoiPrevistoComoVencedor(Palpite palpite) {
        return palpite.getGolsMandante() > palpite.getGolsVisitante();
    }

    private boolean visitanteFoiPrevistoComoVencedor(Palpite palpite) {
        return palpite.getGolsMandante() < palpite.getGolsVisitante();
    }

    private boolean jogoTerminouEmpatado(Jogo jogo) {
        return jogo.getGolsMandante() == jogo.getGolsVisitante();
    }

    private boolean palpitePreviuEmpate(Palpite palpite){
        return palpite.getGolsMandante() == palpite.getGolsVisitante();
    }

    // ========================
    // Regras de negócio
    // ========================

    private int calcularPontuacao(Jogo jogo, Palpite palpite) {

        if (placarExato(jogo, palpite)) {
            return 10;
        }

        if (mandanteVenceu(jogo)
                && mandanteFoiPrevistoComoVencedor(palpite)
                && jogo.getGolsMandante() == palpite.getGolsMandante()) {
            return 7;
        }

        if (visitanteVenceu(jogo)
                && visitanteFoiPrevistoComoVencedor(palpite)
                && jogo.getGolsVisitante() == palpite.getGolsVisitante()) {
            return 7;
        }

        if (mandanteVenceu(jogo)
                && mandanteFoiPrevistoComoVencedor(palpite)) {
            return 5;
        }

        if (visitanteVenceu(jogo)
                && visitanteFoiPrevistoComoVencedor(palpite)) {
            return 5;
        }

        if (
                jogoTerminouEmpatado(jogo)
                        &&
                palpitePreviuEmpate(palpite)
        ) {
            return 5;
        }

        return 0;
    }
}