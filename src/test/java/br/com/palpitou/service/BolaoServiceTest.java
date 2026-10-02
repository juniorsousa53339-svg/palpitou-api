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

        BolaoRequest request = new BolaoRequest();
        request.setNome("Campeonato de Teste");
        request.setValorInscricao(new BigDecimal("50.00"));
        request.setDataInicio(LocalDateTime.now().plusDays(1));
        request.setDataFim(LocalDateTime.now().plusDays(10));
        request.setStatus(StatusGlobal.ABERTA);
        request.setCampeonatoId(1L);

        Bolao bolao = new Bolao();
        bolao.setNome(request.getNome());
        bolao.setValorInscricao(request.getValorInscricao());
        bolao.setDataInicio(request.getDataInicio());
        bolao.setDataFim(request.getDataFim());
        bolao.setStatus(request.getStatus());
        bolao.setCampeonato(campeonato);

        BolaoResponse responseEsperado = new BolaoResponse();
        responseEsperado.setNome("Campeonato de Teste");
        responseEsperado.setValorInscricao(new BigDecimal("50.00"));
        responseEsperado.setDataInicio(request.getDataInicio());
        responseEsperado.setDataFim(request.getDataFim());
        responseEsperado.setStatus(StatusGlobal.ABERTA);

        when(campeonatoRepository.findById(1L)).thenReturn(Optional.of(campeonato));
        when(bolaoMapper.toEntity(request, campeonato)).thenReturn(bolao);
        when(bolaoRepository.save(bolao)).thenReturn(bolao);
        when(bolaoMapper.toResponse(bolao)).thenReturn(responseEsperado);

        // ACT
        BolaoResponse response = bolaoService.salvar(request);

        // ASSERT
        assertNotNull(response);
        assertEquals("Campeonato de Teste", response.getNome());
        assertEquals(new BigDecimal("50.00"), response.getValorInscricao());
        assertEquals(StatusGlobal.ABERTA, response.getStatus());
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

        BolaoResponse responseEsperado = new BolaoResponse();
        responseEsperado.setNome("Bolão da Liga");
        responseEsperado.setValorInscricao(new BigDecimal("75.00"));
        responseEsperado.setStatus(StatusGlobal.ABERTA);

        when(bolaoRepository.findById(10L)).thenReturn(Optional.of(bolao));
        when(bolaoMapper.toResponse(bolao)).thenReturn(responseEsperado);

        // ACT
        BolaoResponse response = bolaoService.buscar(10L);

        // ASSERT
        assertNotNull(response);
        assertEquals("Bolão da Liga", response.getNome());
        assertEquals(new BigDecimal("75.00"), response.getValorInscricao());
    }
}

