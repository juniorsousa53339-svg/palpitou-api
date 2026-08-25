package br.com.palpitou.mapper;


import br.com.palpitou.dto.PremiacaoResponse;
import br.com.palpitou.entity.Premiacao;
import org.springframework.stereotype.Component;

@Component
public class PremiacaoMapper {

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
