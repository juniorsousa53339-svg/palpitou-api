package br.com.palpitou.mapper;

import br.com.palpitou.dto.CampeonatoRequest;

import br.com.palpitou.dto.CampeonatoResponse;
import br.com.palpitou.entity.Campeonato;
import org.springframework.stereotype.Component;

@Component
public class CampeonatoMapper {

    public Campeonato toEntity(CampeonatoRequest request) {

        Campeonato camp = new Campeonato();

        camp.setNome(request.getNome());
        camp.setTemporada(request.getTemporada());
        camp.setStatus(request.getStatus());

        return camp;
    }

    public CampeonatoResponse toResponse(Campeonato campeonato) {

        CampeonatoResponse response = new CampeonatoResponse();

        response.setId(campeonato.getId());
        response.setNome(campeonato.getNome());
        response.setTemporada(campeonato.getTemporada());
        response.setStatus(campeonato.getStatus());

        return response;
    }

}
