package br.com.palpitou.mapper;

import br.com.palpitou.dto.request.PremiacaoRequest;
import br.com.palpitou.dto.response.PremiacaoResponse;
import br.com.palpitou.entity.Bolao;
import br.com.palpitou.entity.Premiacao;
import org.springframework.stereotype.Component;

@Component
public class PremiacaoMapper {

    public Premiacao toEntity(PremiacaoRequest request, Bolao bolao) {
        Premiacao premiacao = new Premiacao();
        premiacao.setPosicao(request.posicao());
        premiacao.setValor(request.valor());
        premiacao.setDescricao(request.descricao());
        premiacao.setBolao(bolao);
        return premiacao;
    }

    public PremiacaoResponse toResponse(Premiacao premiacao) {
        Long userId = premiacao.getUser() != null ? premiacao.getUser().getId() : null;
        Long bolaoId = premiacao.getBolao() != null ? premiacao.getBolao().getId() : null;
        return new PremiacaoResponse(
            userId,
            bolaoId,
            premiacao.getPosicao(),
            premiacao.getValor(),
            premiacao.getDescricao()
        );
    }
}
