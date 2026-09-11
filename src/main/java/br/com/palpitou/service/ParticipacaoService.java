package br.com.palpitou.service;


import br.com.palpitou.dto.ParticipacaoRequest;
import br.com.palpitou.dto.ParticipacaoResponse;
import br.com.palpitou.dto.PutRequestParticipacao;
import br.com.palpitou.dto.PutResponseParticipacao;
import br.com.palpitou.entity.Bolao;
import br.com.palpitou.entity.Pagamento;
import br.com.palpitou.entity.Participacao;
import br.com.palpitou.entity.User;
import br.com.palpitou.exception.BusinessRuleException;
import br.com.palpitou.exception.ResourceNotFoundException;
import br.com.palpitou.mapper.ParticipacaoMapper;
import br.com.palpitou.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipacaoService {

    private final ParticipacaoRepository participacaoRepository;
    private final UserRepository userRepository;
    private final PagamentoRepository pagamentoRepository;
    private final BolaoRepository bolaoRepository;
    private final ParticipacaoMapper participacaoMapper;

    // ========================
    // Métodos auxiliares
    // ========================

    private Participacao buscarParticipacao(Long idParticipacao) {
        return participacaoRepository.findById(idParticipacao)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Participação não encontrada!"));
    }

    private User buscarUser(Long idUser) {
        return userRepository.findById(idUser)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuario não encontrado!"));
    }


    private Bolao buscarBolao(Long idBolao) {
        return bolaoRepository.findById(idBolao)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Bolão não encontrado!"));
    }

    private Pagamento buscarPagamento(Long id) {
        return pagamentoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Pagamento não encontrado!"));
    }

    // ========================
    // CRUD
    // ========================

    public ParticipacaoResponse salvar(ParticipacaoRequest request) {

        User user =
                buscarUser(request.getUserId());

        Bolao bolao =
                buscarBolao(request.getBolaoId());


          Pagamento pagamento =
                  buscarPagamento(request.getPagamentoId());

          validarPagamento(pagamento);

        validaData
                (bolao.getDataInicio());

        validarParticipacaoDuplicada
                (request);

        Participacao participacao =
                participacaoMapper.toEntity(

                request, user, bolao, pagamento
        );

        participacao.setDataInscricao(LocalDate.now());

        Participacao participacaoSalvo =
                participacaoRepository.save(participacao);

        return participacaoMapper.
                toResponse(participacaoSalvo);
    }

    public ParticipacaoResponse buscar(Long id) {
        Participacao participacao = buscarParticipacao(id);
        return participacaoMapper.toResponse(participacao);
    }

    public List<ParticipacaoResponse> listarTodos() {
        List<Participacao> participacoes =
                participacaoRepository.findAll();

        List<ParticipacaoResponse> responses =
                participacoes.stream().map(
                        participacaoMapper::toResponse)
                        .toList();
        return responses;
    }

    public PutResponseParticipacao updateParticipacao(
            Long id, PutRequestParticipacao put
    ) {
        Participacao participacao = buscarParticipacao(id);

        participacao.alterarDados(

                put.getPontos(),
                put.getStatus()
        );

        Participacao participacaoSalvo =
                participacaoRepository.save(participacao);

        return participacaoMapper.
                toPutResponseParticipacao(participacaoSalvo);
    }

    public void delete(long id) {
        Participacao participacao = buscarParticipacao(id);
        participacaoRepository.delete(participacao);
    }

    // =========================
    // Regras de negócio
    // =========================


    private void validaData(LocalDateTime dataInicio) {

        if (dataInicio.isBefore(LocalDateTime.now())) {
            throw new BusinessRuleException(
                    "Este bolão já foi iniciado" +
                            " e não aceita novas participações."
            );
        }
    }

    private void validarParticipacaoDuplicada(ParticipacaoRequest request) {

        if (participacaoRepository.existsByBolaoIdAndUsuarioId(
                request.getBolaoId(),
                request.getUserId())
        ) {
            throw new BusinessRuleException(
                    "Usuário já possui " +
                    "uma participação neste bolão.");
        }
    }

    private void validarPagamento(Pagamento pagamento) {
        pagamento.isPagamentoAprovado
                (pagamento.getStatus());
    }
}
