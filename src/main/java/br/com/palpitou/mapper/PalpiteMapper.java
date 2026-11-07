package br.com.palpitou.mapper;

import br.com.palpitou.dto.request.PalpiteRequest;
import br.com.palpitou.dto.response.PalpiteResponse;
import br.com.palpitou.dto.putResponse.PutResponsePalpite;
import br.com.palpitou.entity.Jogo;
import br.com.palpitou.entity.Palpite;
import br.com.palpitou.entity.User;
import org.springframework.stereotype.Component;

@Component
public class PalpiteMapper {

    public Palpite toEntity(PalpiteRequest request,
                            Jogo jogo, User user
    ) {


        Palpite palpite = new Palpite();

        palpite.setGolsMandante(request.golsMandante());
        palpite.setGolsVisitante(request.golsVisitante());
        palpite.setJogo(jogo);
        palpite.setUser(user);
        return palpite;
    }

    public PalpiteResponse toResponse(Palpite palpite) {
        return new PalpiteResponse(
            palpite.getGolsMandante(),
            palpite.getGolsVisitante(),
            palpite.getPontosObtidos(),
            palpite.getUser() != null ? palpite.getUser().getId() : null
        );
    }

    public PutResponsePalpite toPut(Palpite palpite) {
        return new PutResponsePalpite(palpite.getPontosObtidos());
    }
}
