package br.com.palpitou.mapper;

import br.com.palpitou.dto.JogoRequest;
import br.com.palpitou.dto.JogoResponse;
import br.com.palpitou.entity.Jogo;
import org.springframework.stereotype.Component;

@Component
public class JogoMapper {

    public Jogo toEntity(JogoRequest request) {

        Jogo jogo = new Jogo();

        jogo.setDataHora(request.getDataHora());
        jogo.setRodada(request.getRodada());
        jogo.setStatus(request.getStatus());
        jogo.setCampeonato(request.getCampeonato());
        jogo.setTimeMandante(request.getTimeMandante());
        jogo.setTimeVisitante(request.getTimeVisitante());

        return jogo;
    }

    public JogoResponse toResponse(Jogo jogo) {
        JogoResponse response = new JogoResponse();

        response.setId(jogo.getId());
        response.setDataHora(jogo.getDataHora());
        response.setRodada(jogo.getRodada());
        response.setStatus(jogo.getStatus());
        response.setCampeonato(jogo.getCampeonato());
        response.setTimeMandante(jogo.getTimeMandante());
        response.setTimeVisitante(jogo.getTimeVisitante());

        return response;
    }
}
