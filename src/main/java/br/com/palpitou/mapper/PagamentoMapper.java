package br.com.palpitou.mapper;

import br.com.palpitou.dto.PagamentoRequest;
import br.com.palpitou.dto.PagamentoResponse;
import br.com.palpitou.dto.PutResponsePagamento;
import br.com.palpitou.entity.Pagamento;
import br.com.palpitou.entity.Participacao;
import br.com.palpitou.entity.User;
import org.springframework.stereotype.Component;

@Component
public class PagamentoMapper {

    public Pagamento toEntity(
            PagamentoRequest request,
            User user,
            Participacao participacao
    ) {

        Pagamento pagamento = new Pagamento();

        pagamento.setValor(request.getValor());
        pagamento.setNomePagadorPix(request.getNomePagadorPix());
        pagamento.setComprovante(request.getComprovante());
        pagamento.setUser(user);
        pagamento.setParticipacao(participacao);
        return pagamento;
    }

    public PagamentoResponse toResponse(Pagamento pagamento) {

        PagamentoResponse response = new PagamentoResponse();

        response.setValor(pagamento.getValor());
        response.setNomePagadorPix(pagamento.getNomePagadorPix());
        response.setComprovante(pagamento.getComprovante());
        response.setStatus(pagamento.getStatus());
        return response;
    }

    public PutResponsePagamento toPut(Pagamento pagamento) {
        PutResponsePagamento put = new PutResponsePagamento();
        put.setStatus(pagamento.getStatus());
        return put;
    }


}
