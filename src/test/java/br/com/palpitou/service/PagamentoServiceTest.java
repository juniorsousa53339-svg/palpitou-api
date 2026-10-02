package br.com.palpitou.service;

import br.com.palpitou.dto.request.PagamentoRequest;
import br.com.palpitou.dto.response.PagamentoResponse;
import br.com.palpitou.entity.Bolao;
import br.com.palpitou.entity.Pagamento;
import br.com.palpitou.entity.User;
import br.com.palpitou.enums.StatusPagamento;
import br.com.palpitou.mapper.PagamentoMapper;
import br.com.palpitou.repository.BolaoRepository;
import br.com.palpitou.repository.PagamentoRepository;
import br.com.palpitou.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PagamentoServiceTest {

    @Mock
    private PagamentoRepository pagamentoRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BolaoRepository bolaoRepository;

    @Mock
    private PagamentoMapper pagamentoMapper;

    @InjectMocks
    private PagamentoService pagamentoService;

    @Test
    public void deveSalvarPagamentoComSucesso() {
        // ARRANGE
        User usuario = new User();
        usuario.setId(5L);
        usuario.setNome("Carlos");

        Bolao bolao = new Bolao();
        bolao.setId(9L);
        bolao.setNome("Bolão do Verão");

        PagamentoRequest request = new PagamentoRequest();
        request.setValor(new BigDecimal("80.00"));
        request.setNomePagadorPix("Carlos");
        request.setComprovante("comprovante-123");
        request.setUserId(5L);
        request.setBolaoId(9L);

        Pagamento pagamento = new Pagamento();
        pagamento.setId(1L);
        pagamento.setValor(new BigDecimal("80.00"));
        pagamento.setNomePagadorPix("Carlos");
        pagamento.setComprovante("comprovante-123");
        pagamento.setUser(usuario);
        pagamento.setBolao(bolao);
        pagamento.setStatus(StatusPagamento.PENDENTE);

        PagamentoResponse responseEsperado = new PagamentoResponse();
        responseEsperado.setValor(new BigDecimal("80.00"));
        responseEsperado.setNomePagadorPix("Carlos");
        responseEsperado.setComprovante("comprovante-123");
        responseEsperado.setStatus(StatusPagamento.PENDENTE);

        when(userRepository.findById(5L)).thenReturn(Optional.of(usuario));
        when(bolaoRepository.findById(9L)).thenReturn(Optional.of(bolao));
        when(pagamentoMapper.toEntity(request, usuario, bolao)).thenReturn(pagamento);
        when(pagamentoRepository.save(pagamento)).thenReturn(pagamento);
        when(pagamentoMapper.toResponse(pagamento)).thenReturn(responseEsperado);

        // ACT
        PagamentoResponse response = pagamentoService.salvar(request);

        // ASSERT
        assertNotNull(response);
        assertEquals(new BigDecimal("80.00"), response.getValor());
        assertEquals("Carlos", response.getNomePagadorPix());
        assertEquals(StatusPagamento.PENDENTE, response.getStatus());
    }

    @Test
    public void deveListarTodosOsPagamentos() {
        // ARRANGE
        Pagamento pagamento = new Pagamento();
        pagamento.setId(2L);
        pagamento.setValor(new BigDecimal("120.00"));
        pagamento.setStatus(StatusPagamento.APROVADO);
        pagamento.setNomePagadorPix("Ana");
        pagamento.setComprovante("doc-456");

        PagamentoResponse response = new PagamentoResponse();
        response.setValor(new BigDecimal("120.00"));
        response.setStatus(StatusPagamento.APROVADO);
        response.setNomePagadorPix("Ana");
        response.setComprovante("doc-456");

        when(pagamentoRepository.findAll()).thenReturn(List.of(pagamento));
        when(pagamentoMapper.toResponse(pagamento)).thenReturn(response);

        // ACT
        List<PagamentoResponse> respostas = pagamentoService.listarTodos();

        // ASSERT
        assertNotNull(respostas);
        assertEquals(1, respostas.size());
        assertEquals(new BigDecimal("120.00"), respostas.get(0).getValor());
    }
}

