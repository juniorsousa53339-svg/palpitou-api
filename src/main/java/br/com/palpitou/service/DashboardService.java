package br.com.palpitou.service;

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
    private int buscarParticipantesAprovados() {
        return Math.toIntExact(
                participacaoRepository.countByStatus
                        (StatusParticipacao.APROVADA));
    }

    //Metodo auxiliar
    private int buscarQuantidadePagamentosPorStatus(StatusPagamento status) {
        return Math.toIntExact(
                pagamentoRepository.countByStatus(status)
        );
    }

}
