package br.com.palpitou.mapper;

import br.com.palpitou.dto.JogoRequest;
import br.com.palpitou.dto.JogoResponse;
import br.com.palpitou.dto.PutResponseJogo;
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

        jogo.setDataHora(request.getDataHora());
        jogo.setRodada(request.getRodada());
        jogo.setStatus(request.getStatus());
        jogo.setCampeonato(campeonato);
        jogo.setTimeMandante(timeMandante);
        jogo.setTimeVisitante(timeVisitante);

        return jogo;
    }

    public JogoResponse toResponse(Jogo jogo) {

        JogoResponse response = new JogoResponse();

        response.setId(jogo.getId());
        response.setDataHora(jogo.getDataHora());
        response.setRodada(jogo.getRodada());
        response.setStatus(jogo.getStatus());

        response.setCampeonatoId(jogo.getCampeonato().getId());
        response.setTimeMandanteId(jogo.getTimeMandante().getId());
        response.setTimeVisitanteId(jogo.getTimeVisitante().getId());

        return response;
    }

    public PutResponseJogo toPutResponseJogo(Jogo jogo) {

        PutResponseJogo putResponseJogo = new PutResponseJogo();

        putResponseJogo.setDataHora(jogo.getDataHora());
        putResponseJogo.setRodada(jogo.getRodada());
        putResponseJogo.setGolsMandante(jogo.getGolsMandante());
        putResponseJogo.setGolsVisitante(jogo.getGolsVisitante());
        putResponseJogo.setStatus(jogo.getStatus());
        return putResponseJogo;
    }
}
