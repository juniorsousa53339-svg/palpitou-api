package br.com.palpitou.service;

import br.com.palpitou.dto.request.ParticipacaoRequest;
import br.com.palpitou.dto.response.ParticipacaoResponse;
import br.com.palpitou.entity.Bolao;
import br.com.palpitou.entity.Pagamento;
import br.com.palpitou.entity.Participacao;
import br.com.palpitou.entity.User;
import br.com.palpitou.enums.StatusGlobal;
import br.com.palpitou.enums.StatusPagamento;
import br.com.palpitou.enums.StatusParticipacao;
import br.com.palpitou.mapper.ParticipacaoMapper;
import br.com.palpitou.repository.BolaoRepository;
import br.com.palpitou.repository.PagamentoRepository;
import br.com.palpitou.repository.ParticipacaoRepository;
import br.com.palpitou.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ParticipacaoServiceTest {

    @Mock
    private ParticipacaoRepository participacaoRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PagamentoRepository pagamentoRepository;

    @Mock
    private BolaoRepository bolaoRepository;

    @Mock
    private ParticipacaoMapper participacaoMapper;

    @InjectMocks
    private ParticipacaoService participacaoService;

    @Test
    public void deveSalvarParticipacaoComSucesso() {
        // ARRANGE
        User usuario = new User();
        usuario.setId(1L);
        usuario.setNome("João");

        Bolao bolao = new Bolao();
        bolao.setId(2L);
        bolao.setNome("Bolão Teste");
        bolao.setDataInicio(LocalDateTime.now().plusDays(3));
        bolao.setStatus(StatusGlobal.ABERTA);

        Pagamento pagamento = new Pagamento();
        pagamento.setId(3L);
        pagamento.setStatus(StatusPagamento.APROVADO);

        ParticipacaoRequest request = new ParticipacaoRequest();
        request.setUserId(1L);
        request.setBolaoId(2L);
        request.setPagamentoId(3L);
        request.setPontos(10);

        Participacao participacao = new Participacao();
        participacao.setId(99L);
        participacao.setUsuario(usuario);
        participacao.setBolao(bolao);
        participacao.setPagamento(pagamento);
        participacao.setPontos(10);
        participacao.setStatus(StatusParticipacao.PENDENTE);
        participacao.setDataInscricao(LocalDate.now());

        ParticipacaoResponse responseEsperado = new ParticipacaoResponse();
        responseEsperado.setPontos(10);
        responseEsperado.setStatus(StatusParticipacao.PENDENTE);
        responseEsperado.setDataInscricao(LocalDate.now());

        when(userRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(bolaoRepository.findById(2L)).thenReturn(Optional.of(bolao));
        when(pagamentoRepository.findById(3L)).thenReturn(Optional.of(pagamento));
        when(participacaoRepository.existsByBolaoIdAndUsuarioId(2L, 1L)).thenReturn(false);
        when(participacaoMapper.toEntity(request, usuario, bolao, pagamento)).thenReturn(participacao);
        when(participacaoRepository.save(participacao)).thenReturn(participacao);
        when(participacaoMapper.toResponse(participacao)).thenReturn(responseEsperado);

        // ACT
        ParticipacaoResponse response = participacaoService.salvar(request);

        // ASSERT
        assertNotNull(response);
        assertEquals(10, response.getPontos());
        assertEquals(StatusParticipacao.PENDENTE, response.getStatus());
    }

    @Test
    public void deveListarTodasAsParticipacoes() {
        // ARRANGE
        Participacao participacao = new Participacao();
        participacao.setId(1L);
        participacao.setPontos(15);
        participacao.setStatus(StatusParticipacao.APROVADA);
        participacao.setDataInscricao(LocalDate.now());

        ParticipacaoResponse response = new ParticipacaoResponse();
        response.setPontos(15);
        response.setStatus(StatusParticipacao.APROVADA);
        response.setDataInscricao(LocalDate.now());

        when(participacaoRepository.findAll()).thenReturn(List.of(participacao));
        when(participacaoMapper.toResponse(participacao)).thenReturn(response);

        // ACT
        List<ParticipacaoResponse> respostas = participacaoService.listarTodos();

        // ASSERT
        assertNotNull(respostas);
        assertEquals(1, respostas.size());
        assertEquals(15, respostas.get(0).getPontos());
    }
}

