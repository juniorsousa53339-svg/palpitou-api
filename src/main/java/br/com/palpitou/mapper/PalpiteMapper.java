package br.com.palpitou.mapper;

import br.com.palpitou.dto.PalpiteRequest;
import br.com.palpitou.dto.PalpiteResponse;
import br.com.palpitou.dto.PutResponsePalpite;
import br.com.palpitou.entity.Jogo;
import br.com.palpitou.entity.Palpite;
import br.com.palpitou.entity.User;
import org.springframework.stereotype.Component;

@Component
public class PalpiteMapper {

    public Palpite toEntity(PalpiteRequest request,
                            Jogo jogo, User usuario
    ) {


        Palpite palpite = new Palpite();

        palpite.setGolsMandante(request.getGolsMandante());
        palpite.setGolsVisitante(request.getGolsVisitante());
        palpite.setJogo(jogo);
        palpite.setUsuario(usuario);
        return palpite;
    }

    public PalpiteResponse toResponse(Palpite palpite) {

        PalpiteResponse response = new PalpiteResponse();

        response.setGolsMandante(palpite.getGolsMandante());
        response.setGolsVisitante(palpite.getGolsVisitante());
        response.setPontosObtidos(palpite.getPontosObtidos());
        return  response;
    }

    public PutResponsePalpite toPut(Palpite palpite) {
        PutResponsePalpite put = new PutResponsePalpite();
        put.setPontosObtidos(palpite.getPontosObtidos());
        return put;
    }
}
