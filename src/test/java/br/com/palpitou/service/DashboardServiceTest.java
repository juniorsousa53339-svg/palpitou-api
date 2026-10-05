package br.com.palpitou.service;

import br.com.palpitou.repository.BolaoRepository;
import br.com.palpitou.repository.PagamentoRepository;
import br.com.palpitou.repository.ParticipacaoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DashboardServiceTest {

    @Mock
    private BolaoRepository bolaoRepository;

    @Mock
    private ParticipacaoRepository participacaoRepository;

    @Mock
    private PagamentoRepository pagamentoRepository;

    @InjectMocks
    private DashboardService dashboardService;

    @Test
    public void deveBuscarBoloesDoDashboard(){
    }

}
