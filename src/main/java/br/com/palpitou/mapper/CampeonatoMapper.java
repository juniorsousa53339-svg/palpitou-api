package br.com.palpitou.mapper;

import br.com.palpitou.dto.request.CampeonatoRequest;

import br.com.palpitou.dto.response.CampeonatoResponse;
import br.com.palpitou.entity.Campeonato;
import org.springframework.stereotype.Component;

@Component
public class CampeonatoMapper {

    public Campeonato toEntity(CampeonatoRequest request) {

        Campeonato camp = new Campeonato();

        camp.setNome(request.nome());
        camp.setTemporada(request.temporada());
        camp.setStatus(request.status());

        return camp;
    }

    public CampeonatoResponse toResponse(Campeonato campeonato) {
        return new CampeonatoResponse(
            campeonato.getId(),
            campeonato.getNome(),
            campeonato.getTemporada(),
            campeonato.getStatus()
        );
    }

}
