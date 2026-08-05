package br.com.palpitou.mapper;

import br.com.palpitou.dto.PremiacaoRequest;
import br.com.palpitou.dto.PremiacaoResponse;
import br.com.palpitou.entity.Bolao;
import br.com.palpitou.entity.Premiacao;
import br.com.palpitou.entity.User;
import org.springframework.stereotype.Component;

@Component
public class PremiacaoMapper {

    public Premiacao toEntity(
            PremiacaoRequest premiacaoRequest,
            Bolao bolao ,
            User user
    ) {

        Premiacao premiacao = new Premiacao();
        premiacao.setUser(user);
        premiacao.setBolao(bolao);
        return premiacao;
    }

    public PremiacaoResponse toResponse(Premiacao premiacao) {

        PremiacaoResponse response = new PremiacaoResponse();
        response.setUserId(premiacao.getUser().getId());
        response.setBolaoId(premiacao.getBolao().getId());
        response.setDescricao(premiacao.getDescricao());
        response.setPosicao(premiacao.getPosicao());
        response.setValor(premiacao.getValor());
        return response;
    }
}
