package br.com.palpitou.mapper;

import br.com.palpitou.dto.request.PagamentoRequest;
import br.com.palpitou.dto.response.PagamentoResponse;
import br.com.palpitou.dto.putResponse.PutResponsePagamento;
import br.com.palpitou.entity.Bolao;
import br.com.palpitou.entity.Pagamento;

import br.com.palpitou.entity.User;
import br.com.palpitou.enums.StatusPagamento;
import org.springframework.stereotype.Component;

@Component
public class PagamentoMapper {

    public Pagamento toEntity(
            PagamentoRequest request,
            User user,
            Bolao bolao
    ) {

        Pagamento pagamento = new Pagamento();

        pagamento.setValor(request.valor());
        pagamento.setNomePagadorPix(request.nomePagadorPix());
        pagamento.setComprovante(request.comprovante());
        pagamento.setUser(user);
        pagamento.setBolao(bolao);
        pagamento.setStatus(StatusPagamento.PENDENTE);
        return pagamento;
    }

    public PagamentoResponse toResponse(Pagamento pagamento) {
        return new PagamentoResponse(
            pagamento.getValor(),
            pagamento.getStatus(),
            pagamento.getNomePagadorPix(),
            pagamento.getComprovante()
        );
    }

    public PutResponsePagamento toPut(Pagamento pagamento) {
        return new PutResponsePagamento(pagamento.getStatus());
    }
}
