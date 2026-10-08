
package br.com.palpitou.service;

import br.com.palpitou.dto.Dashboard.BolaoDashboardResponse;
import br.com.palpitou.dto.Dashboard.DashboardResponse;
import br.com.palpitou.entity.Bolao;
import br.com.palpitou.entity.Campeonato;
import br.com.palpitou.enums.StatusPagamento;
import br.com.palpitou.enums.StatusParticipacao;
import br.com.palpitou.repository.BolaoRepository;
import br.com.palpitou.repository.PagamentoRepository;
import br.com.palpitou.repository.ParticipacaoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DashboardServiceTest {

    @Mock
    private BolaoRepository bolaoRepository;

    @Mock
    private ParticipacaoRepository participacaoRepository;

    @Mock
    private PagamentoRepository pagamentoRepository;

    @InjectMocks
    private DashboardService dashboardService;

    @Test
    void deveBuscarDashboard() {
        // Arrange: configuramos as respostas dos mocks
        when(bolaoRepository.count()).thenReturn(3L);

        when(participacaoRepository.countByStatus(
                StatusParticipacao.APROVADA
        )).thenReturn(10L);

        when(pagamentoRepository.countByStatus(
                StatusPagamento.APROVADO
        )).thenReturn(5L);

        when(pagamentoRepository.countByStatus(
                StatusPagamento.PENDENTE
        )).thenReturn(2L);

        when(pagamentoRepository.countByStatus(
                StatusPagamento.RECUSADO
        )).thenReturn(1L);

        when(pagamentoRepository.sumFaturamento(
                StatusPagamento.APROVADO
        )).thenReturn(new BigDecimal("250.00"));

        // Act: executamos o método que queremos testar
        DashboardResponse resposta = dashboardService.buscarDashboard();

        // Assert: verificamos o resultado
        assertEquals(3, resposta.getTotalBoloes());
        assertEquals(10, resposta.getParticipantesAprovados());
        assertEquals(5, resposta.getPagamentosAprovados());
        assertEquals(2, resposta.getPagamentosPendentes());
        assertEquals(1, resposta.getPagamentosRejeitados());

        assertEquals(
                0,
                new BigDecimal("250.00").compareTo(resposta.getFaturamento())
        );
    }

    @Test
    void deveBuscarBoloesDashboard() {
        // Arrange: criamos os bolões de exemplo
        Campeonato campeonato1 = new Campeonato();
        campeonato1.setNome("Brasileirão");

        Bolao bolao1 = new Bolao();
        bolao1.setId(1L);
        bolao1.setNome("Bolão 1");
        bolao1.setCampeonato(campeonato1);

        Campeonato campeonato2 = new Campeonato();
        campeonato2.setNome("Paulistão");

        Bolao bolao2 = new Bolao();
        bolao2.setId(2L);
        bolao2.setNome("Bolão 2");
        bolao2.setCampeonato(campeonato2);

        List<Bolao> boloes = List.of(bolao1, bolao2);

        // Configuramos o retorno dos repositórios
        when(bolaoRepository.findAll()).thenReturn(boloes);

        when(participacaoRepository.countByBolaoIdAndStatus(
                1L, StatusParticipacao.APROVADA
        )).thenReturn(4L);

        when(participacaoRepository.countByBolaoIdAndStatus(
                2L, StatusParticipacao.APROVADA
        )).thenReturn(7L);

        when(pagamentoRepository.sumByBolaoIdAndStatus(
                1L, StatusPagamento.APROVADO
        )).thenReturn(new BigDecimal("100.00"));

        when(pagamentoRepository.sumByBolaoIdAndStatus(
                2L, StatusPagamento.APROVADO
        )).thenReturn(new BigDecimal("200.00"));

        // Act: executamos o método que queremos testar
        List<BolaoDashboardResponse> resposta =
                dashboardService.buscarBoloesDashboard();

        // Assert: verificamos a quantidade de resultados
        assertEquals(2, resposta.size());

        // Verificamos os dados do primeiro bolão
        assertEquals("Bolão 1", resposta.get(0).getBolao());
        assertEquals("Brasileirão", resposta.get(0).getCampeonato());
        assertEquals(4, resposta.get(0).getParticipantesAprovados());
        assertEquals(
                0,
                new BigDecimal("100.00").compareTo(
                        resposta.get(0).getFaturamento()
                )
        );

        // Verificamos os dados do segundo bolão
        assertEquals("Bolão 2", resposta.get(1).getBolao());
        assertEquals("Paulistão", resposta.get(1).getCampeonato());
        assertEquals(7, resposta.get(1).getParticipantesAprovados());
        assertEquals(
                0,
                new BigDecimal("200.00").compareTo(
                        resposta.get(1).getFaturamento()
                )
        );
    }
}
