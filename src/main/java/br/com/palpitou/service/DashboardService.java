package br.com.palpitou.service;

import br.com.palpitou.dto.Dashboard.BolaoDashboardResponse;
import br.com.palpitou.dto.Dashboard.DashboardResponse;
import br.com.palpitou.entity.Bolao;
import br.com.palpitou.enums.StatusPagamento;
import br.com.palpitou.enums.StatusParticipacao;
import br.com.palpitou.repository.BolaoRepository;
import br.com.palpitou.repository.PagamentoRepository;
import br.com.palpitou.repository.ParticipacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class DashboardService {

    private final BolaoRepository bolaoRepository;
    private final ParticipacaoRepository participacaoRepository;
    private final PagamentoRepository pagamentoRepository;

    private int buscarTotalBoloes() {
        return Math.toIntExact(bolaoRepository.count());
    }

    private int buscarParticipantesPorStatus(StatusParticipacao status) {
        return Math.toIntExact(participacaoRepository.countByStatus(status));
    }

    private int buscarQuantidadePagamentosPorStatus(StatusPagamento status) {
        return Math.toIntExact(pagamentoRepository.countByStatus(status));
    }

    private BigDecimal buscarFaturamento() {
        return pagamentoRepository.sumFaturamento(StatusPagamento.APROVADO);
    }

    private BigDecimal buscarFaturamentoPorBolao(Long bolaoId) {
        return pagamentoRepository.sumByBolaoIdAndStatus(bolaoId, StatusPagamento.APROVADO);
    }

    private int buscarParticipantesPorStatusPorBolao(Long bolaoId, StatusParticipacao status) {
        return Math.toIntExact(participacaoRepository.countByBolaoIdAndStatus(bolaoId, status));
    }

    public DashboardResponse buscarDashboard() {
        return new DashboardResponse(
            buscarTotalBoloes(),
            buscarParticipantesPorStatus(StatusParticipacao.APROVADA),
            buscarQuantidadePagamentosPorStatus(StatusPagamento.PENDENTE),
            buscarQuantidadePagamentosPorStatus(StatusPagamento.APROVADO),
            buscarQuantidadePagamentosPorStatus(StatusPagamento.RECUSADO),
            buscarFaturamento()
        );
    }

    public List<BolaoDashboardResponse> buscarBoloesDashboard() {
        List<Bolao> boloes = bolaoRepository.findAll();

        return boloes.stream()
                .map(bolao -> new BolaoDashboardResponse(
                    bolao.getNome(),
                    bolao.getCampeonato() != null ? bolao.getCampeonato().getNome() : null,
                    buscarParticipantesPorStatusPorBolao(bolao.getId(), StatusParticipacao.APROVADA),
                    buscarFaturamentoPorBolao(bolao.getId()),
                    bolao.getStatus()
                ))
                .collect(Collectors.toList());
    }
}
