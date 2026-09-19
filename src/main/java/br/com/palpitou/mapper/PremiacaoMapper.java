package br.com.palpitou.mapper;


import br.com.palpitou.dto.request.PremiacaoRequest;
import br.com.palpitou.dto.response.PremiacaoResponse;
import br.com.palpitou.entity.Bolao;
import br.com.palpitou.entity.Premiacao;
import org.springframework.stereotype.Component;

@Component
public class PremiacaoMapper {

    public Premiacao toEntity(PremiacaoRequest request , Bolao bolao) {
        Premiacao premiacao = new Premiacao();
        premiacao.setPosicao(request.getPosicao());
        premiacao.setValor(request.getValor());
        premiacao.setDescricao(request.getDescricao());
        premiacao.setBolao(bolao);

    return premiacao;
    }

    public PremiacaoResponse toResponse(Premiacao premiacao) {

        PremiacaoResponse response = new PremiacaoResponse();

        response.setUserId(
                premiacao.getUser() != null
                  ? premiacao.getUser().getId()
                  : null
        );

        response.setBolaoId(premiacao.getBolao().getId());
        response.setDescricao(premiacao.getDescricao());
        response.setPosicao(premiacao.getPosicao());
        response.setValor(premiacao.getValor());
        return response;
    }
}
