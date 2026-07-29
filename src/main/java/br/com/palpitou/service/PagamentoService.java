package br.com.palpitou.service;

import br.com.palpitou.dto.PagamentoRequest;
import br.com.palpitou.dto.PagamentoResponse;
import br.com.palpitou.dto.PutRequestPagamento;
import br.com.palpitou.dto.PutResponsePagamento;
import br.com.palpitou.entity.Bolao;
import br.com.palpitou.entity.Pagamento;
import br.com.palpitou.entity.Participacao;
import br.com.palpitou.entity.User;
import br.com.palpitou.mapper.PagamentoMapper;
import br.com.palpitou.repository.PagamentoRepository;
import br.com.palpitou.repository.ParticipacaoRepository;
import br.com.palpitou.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final UserRepository userRepository;
    private final ParticipacaoRepository participacaoRepository;
    private final PagamentoMapper pagamentoMapper;

    // =========================
    // Métodos auxiliares
    // =========================

    private User buscarUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User não encontrado!"));
    }

    private Pagamento buscarPagamento(Long id) {
        return pagamentoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Pagamento não encontrado!"));
    }

    private Participacao buscarParticipacao(Long id) {
        return participacaoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Participação não encontrada!"));
    }

    // =========================
    // CRUD
    // =========================

    public PagamentoResponse salvar(PagamentoRequest request) {

       User user =
               buscarUser(request.getUserId());

       Participacao participacao =
               buscarParticipacao(request.getParticipacaoId());

        Pagamento pagamento = pagamentoMapper.toEntity(
                request,
                user,
                participacao
        );

        Pagamento pagamentoSalvo =
                pagamentoRepository.save(pagamento);

        return pagamentoMapper.toResponse(pagamentoSalvo);
    }

    public PagamentoResponse buscar(Long id) {
        Pagamento pagamento = buscarPagamento(id);
        return pagamentoMapper.toResponse(pagamento);
    }

    public List<PagamentoResponse> listarTodos(){
        List<Pagamento> pagamentos =
                pagamentoRepository.findAll();

        List<PagamentoResponse> response =
                pagamentos.stream().map(pagamentoMapper::toResponse)
                        .toList();

        return response;
    }

    public PutResponsePagamento update(Long id, PutRequestPagamento put) {

        Pagamento pagamentoBd = buscarPagamento(id);

        pagamentoBd.alterarDados
                (put.getStatus());

        Pagamento pagamentoUpdated =
                pagamentoRepository.save(pagamentoBd);

                return pagamentoMapper.toPut(pagamentoUpdated);
    }

    public  void delete(Long id) {
        Pagamento pagamentoBd = buscarPagamento(id);
        pagamentoRepository.delete(pagamentoBd);
    }

    // =========================
    // Regras de negócio
    // =========================

}
