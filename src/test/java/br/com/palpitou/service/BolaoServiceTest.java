package br.com.palpitou.service;

import br.com.palpitou.dto.request.BolaoRequest;
import br.com.palpitou.dto.response.BolaoResponse;
import br.com.palpitou.entity.Bolao;
import br.com.palpitou.entity.Campeonato;
import br.com.palpitou.enums.StatusGlobal;
import br.com.palpitou.mapper.BolaoMapper;
import br.com.palpitou.repository.BolaoRepository;
import br.com.palpitou.repository.CampeonatoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BolaoServiceTest {

    @Mock
    private BolaoRepository bolaoRepository;

    @Mock
    private CampeonatoRepository campeonatoRepository;

    @Mock
    private BolaoMapper bolaoMapper;

    @InjectMocks
    private BolaoService bolaoService;

    @Test
    public void deveSalvarBolaoComSucesso() {
        // ARRANGE
        Campeonato campeonato = new Campeonato();
        campeonato.setId(1L);

        BolaoRequest request = new BolaoRequest(
            "Campeonato de Teste",
            new BigDecimal("50.00"),
            LocalDateTime.now().plusDays(1),
            LocalDateTime.now().plusDays(10),
            StatusGlobal.ABERTA,
            1L
        );

        Bolao bolao = new Bolao();
        bolao.setNome(request.nome());
        bolao.setValorInscricao(request.valorInscricao());
        bolao.setDataInicio(request.dataInicio());
        bolao.setDataFim(request.dataFim());
        bolao.setStatus(request.status());
        bolao.setCampeonato(campeonato);

        BolaoResponse responseEsperado = new BolaoResponse(
            "Campeonato de Teste",
            new BigDecimal("50.00"),
            request.dataInicio(),
            request.dataFim(),
            StatusGlobal.ABERTA
        );

        when(campeonatoRepository.findById(1L)).thenReturn(Optional.of(campeonato));
        when(bolaoMapper.toEntity(request, campeonato)).thenReturn(bolao);
        when(bolaoRepository.save(bolao)).thenReturn(bolao);
        when(bolaoMapper.toResponse(bolao)).thenReturn(responseEsperado);

        // ACT
        BolaoResponse response = bolaoService.salvar(request);

        // ASSERT
        assertNotNull(response);
        assertEquals("Campeonato de Teste", response.nome());
        assertEquals(new BigDecimal("50.00"), response.valorInscricao());
        assertEquals(StatusGlobal.ABERTA, response.status());
    }

    @Test
    public void deveBuscarBolaoPorId() {
        // ARRANGE
        Bolao bolao = new Bolao();
        bolao.setId(10L);
        bolao.setNome("Bolão da Liga");
        bolao.setValorInscricao(new BigDecimal("75.00"));
        bolao.setDataInicio(LocalDateTime.now().plusDays(2));
        bolao.setDataFim(LocalDateTime.now().plusDays(15));
        bolao.setStatus(StatusGlobal.ABERTA);

        BolaoResponse responseEsperado = new BolaoResponse(
            "Bolão da Liga",
            new BigDecimal("75.00"),
            bolao.getDataInicio(),
            bolao.getDataFim(),
            StatusGlobal.ABERTA
        );

        when(bolaoRepository.findById(10L)).thenReturn(Optional.of(bolao));
        when(bolaoMapper.toResponse(bolao)).thenReturn(responseEsperado);

        // ACT
        BolaoResponse response = bolaoService.buscar(10L);

        // ASSERT
        assertNotNull(response);
        assertEquals("Bolão da Liga", response.nome());
        assertEquals(new BigDecimal("75.00"), response.valorInscricao());
    }
}
