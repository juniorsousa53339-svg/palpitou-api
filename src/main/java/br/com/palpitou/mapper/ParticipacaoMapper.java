package br.com.palpitou.mapper;

import br.com.palpitou.dto.request.ParticipacaoRequest;
import br.com.palpitou.dto.response.ParticipacaoResponse;
import br.com.palpitou.dto.putResponse.PutResponseParticipacao;
import br.com.palpitou.entity.Bolao;
import br.com.palpitou.entity.Pagamento;
import br.com.palpitou.entity.Participacao;
import br.com.palpitou.entity.User;

import br.com.palpitou.enums.StatusParticipacao;
import org.springframework.stereotype.Component;

@Component
public class ParticipacaoMapper {

    public Participacao toEntity
            (
                    ParticipacaoRequest request,
                    User user,
                    Bolao bolao,
                    Pagamento pagamento
            ) {

        Participacao participacao = new Participacao();

        participacao.setPontos(request.pontos());
        participacao.setUsuario(user);
        participacao.setBolao(bolao);
        participacao.setPagamento(pagamento);
        participacao.setStatus(StatusParticipacao.PENDENTE);

        return participacao;
    }

    public ParticipacaoResponse toResponse(Participacao participacao) {
        return new ParticipacaoResponse(
            participacao.getStatus(),
            participacao.getDataInscricao(),
            participacao.getPontos()
        );
    }

    public PutResponseParticipacao toPutResponseParticipacao
            (Participacao participacao) {
        return new PutResponseParticipacao(
            participacao.getPontos(),
            participacao.getStatus()
        );
    }


}
