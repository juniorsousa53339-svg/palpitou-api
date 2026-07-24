package br.com.palpitou.mapper;

import br.com.palpitou.dto.ParticipacaoRequest;
import br.com.palpitou.dto.ParticipacaoResponse;
import br.com.palpitou.dto.PutResponseParticipacao;
import br.com.palpitou.entity.Bolao;
import br.com.palpitou.entity.Pagamento;
import br.com.palpitou.entity.Participacao;
import br.com.palpitou.entity.User;

import org.springframework.stereotype.Component;

@Component
public class ParticipacaoMapper {

    public Participacao toEntity
            (
                    ParticipacaoRequest request,
                    Pagamento pagamento,
                    User user,
                    Bolao bolao
            ) {

        Participacao participacao = new Participacao();

        participacao.setStatus(request.getStatus());
        participacao.setDataInscricao(request.getDataInscricao());
        participacao.setPontos(request.getPontos());
        participacao.setUsuario(user);
        participacao.setPagamento(pagamento);
        participacao.setBolao(bolao);
        return participacao;
    }

    public ParticipacaoResponse toResponse(Participacao participacao) {

        ParticipacaoResponse response = new ParticipacaoResponse();

        response.setStatus(participacao.getStatus());
        response.setDataInscricao(participacao.getDataInscricao());
        response.setPontos(participacao.getPontos());

        return  response;
    }

    public PutResponseParticipacao toPutResponseParticipacao
            (Participacao participacao) {

        PutResponseParticipacao putResponseParticipacao = new PutResponseParticipacao();

        putResponseParticipacao.setStatus(participacao.getStatus());
        putResponseParticipacao.setPontos(participacao.getPontos());

        return putResponseParticipacao;
    }


}
