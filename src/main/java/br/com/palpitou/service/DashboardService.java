package br.com.palpitou.service;

import br.com.palpitou.dto.Dashboard.DashboardResponse;
import br.com.palpitou.enums.StatusPagamento;
import br.com.palpitou.enums.StatusParticipacao;
import br.com.palpitou.repository.BolaoRepository;
import br.com.palpitou.repository.PagamentoRepository;
import br.com.palpitou.repository.ParticipacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
@RequiredArgsConstructor
public class DashboardService {

    private final BolaoRepository bolaoRepository;
    private final ParticipacaoRepository participacaoRepository;
    private final PagamentoRepository pagamentoRepository;

    //Metodo auxiliar
    private int buscarTotalBoloes() {
        return Math.toIntExact
                (bolaoRepository.count());
    }

    //Metodo auxiliar
    private int buscarParticipantesPorStatus(StatusParticipacao status) {
        return Math.toIntExact(
                participacaoRepository.countByStatus
                        (status));
    }

    //Metodo auxiliar
    private int buscarQuantidadePagamentosPorStatus(StatusPagamento status) {
        return Math.toIntExact(
                pagamentoRepository.countByStatus(status)
        );
    }

    //Metodo auxiliar
    private BigDecimal buscarFaturamento() {
        return pagamentoRepository.
                sumFaturamento(
                        StatusPagamento.APROVADO);
    }

    public DashboardResponse buscarDashboard() {

        DashboardResponse resposta = new DashboardResponse();

        resposta.setTotalBoloes(
                buscarTotalBoloes()
        );

        resposta.setParticipantesAprovados(
                buscarParticipantesPorStatus(
                        StatusParticipacao.APROVADA
                )
        );

        resposta.setPagamentosAprovados(
                buscarQuantidadePagamentosPorStatus(
                        StatusPagamento.APROVADO
                )
        );

        resposta.setPagamentosPendentes(
                buscarQuantidadePagamentosPorStatus(
                        StatusPagamento.PENDENTE
                )
        );

        resposta.setPagamentosRejeitados(
                buscarQuantidadePagamentosPorStatus(
                        StatusPagamento.RECUSADO
                )
        );

        resposta.setFaturamento(
                buscarFaturamento()
        );

        return resposta;
    }
}
