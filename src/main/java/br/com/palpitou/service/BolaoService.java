package br.com.palpitou.service;

import br.com.palpitou.dto.*;
import br.com.palpitou.entity.Bolao;
import br.com.palpitou.entity.Campeonato;
import br.com.palpitou.enums.StatusGlobal;
import br.com.palpitou.exception.BusinessRuleException;
import br.com.palpitou.exception.ResourceNotFoundException;
import br.com.palpitou.mapper.BolaoMapper;
import br.com.palpitou.repository.BolaoRepository;
import br.com.palpitou.repository.CampeonatoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BolaoService {

    private final BolaoRepository bolaoRepository;
    private final CampeonatoRepository campeonatoRepository;
    private final BolaoMapper bolaoMapper;

    // =========================
    // Métodos auxiliares
    // =========================

    private void validarStatusBolao(StatusGlobal status) {

        if (status == StatusGlobal.FINALIZADA) {
            throw new BusinessRuleException(
                    "Não é permitido cadastrar bolão finalizado."
            );
        }
    }

    private Bolao buscarBolaoPorId(Long id) {
        return bolaoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Bolão não encontrado!"));
    }

    private Campeonato buscarCampeonato(Long id) {
        return campeonatoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Campeonato não encontrado!"));
    }

    // =========================
    // CRUD
    // =========================

    public BolaoResponse salvar(BolaoRequest request) {

        Campeonato campeonato = buscarCampeonato(request.getCampeonatoId());

        validarStatusBolao(request.getStatus());

        Bolao bolao = bolaoMapper.toEntity(request, campeonato);

        Bolao bolaoSalvo = bolaoRepository.save(bolao);
        return bolaoMapper.toResponse(bolaoSalvo);
    }

    public BolaoResponse buscar(Long id) {

        Bolao bolao = buscarBolaoPorId(id);
        return bolaoMapper.toResponse(bolao);
    }

    public List<BolaoResponse> listarTodos() {
        List<Bolao> bolao = bolaoRepository.findAll();

        List<BolaoResponse> reponse =
                bolao.stream().
                        map(bolaoMapper::toResponse)
                        .toList();

        return reponse;
    }

    public PutResponseBolao updateBolao(Long id, PutRequestBolao put) {

        Bolao bolaoBd = buscarBolaoPorId(id);

        bolaoBd.alterarDados(
            put.getNome(),
            put.getValorInscricao(),
            put.getDataInicio(),
            put.getDataFim(),
            put.getStatus()
        );

        Bolao bolaoUpdated = bolaoRepository.save(bolaoBd);
        return bolaoMapper.toPutResponseBolao(bolaoUpdated);
    }

    public void delete(Long id) {
        Bolao bolao = buscarBolaoPorId(id);
        bolaoRepository.delete(bolao);
    }

    // =========================
    // Regras de negócio
    // =========================
}
