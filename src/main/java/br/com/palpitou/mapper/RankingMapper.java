package br.com.palpitou.mapper;

import br.com.palpitou.dto.RankingResponse;


import br.com.palpitou.entity.User;
import org.springframework.stereotype.Component;

@Component
public class RankingMapper {

    public RankingResponse toResponse(User user, int pontuacao , int posicao) {

        RankingResponse response = new RankingResponse();

       response.setNome(user.getNome());
       response.setPontuacao(pontuacao);
       response.setPosicao(posicao);

        return  response;
    }
}
