package br.com.palpitou.mapper;

import br.com.palpitou.dto.request.JogoRequest;
import br.com.palpitou.dto.response.JogoResponse;
import br.com.palpitou.dto.putResponse.PutResponseJogo;
import br.com.palpitou.entity.Campeonato;
import br.com.palpitou.entity.Jogo;
import br.com.palpitou.entity.Time;
import org.springframework.stereotype.Component;

@Component
public class JogoMapper {

    public Jogo toEntity(
            JogoRequest request,
            Campeonato campeonato,
            Time timeMandante,
            Time timeVisitante
    ) {

        Jogo jogo = new Jogo();

        jogo.setDataHora(request.dataHora());
        jogo.setRodada(request.rodada());
        jogo.setStatus(request.status());
        jogo.setCampeonato(campeonato);
        jogo.setTimeMandante(timeMandante);
        jogo.setTimeVisitante(timeVisitante);

        return jogo;
    }

    public JogoResponse toResponse(Jogo jogo) {
        return new JogoResponse(
            jogo.getId(),
            jogo.getDataHora(),
            jogo.getRodada(),
            jogo.getStatus(),
            jogo.getCampeonato() != null ? jogo.getCampeonato().getId() : null,
            jogo.getTimeMandante() != null ? jogo.getTimeMandante().getId() : null,
            jogo.getTimeVisitante() != null ? jogo.getTimeVisitante().getId() : null
        );
    }

    public PutResponseJogo toPutResponseJogo(Jogo jogo) {
        return new PutResponseJogo(
            jogo.getId(),
            null,
            jogo.getDataHora(),
            jogo.getRodada(),
            jogo.getGolsMandante(),
            jogo.getGolsVisitante(),
            jogo.getStatus()
        );
    }
}
