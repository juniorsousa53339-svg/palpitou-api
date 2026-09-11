package br.com.palpitou.service;

import br.com.palpitou.dto.*;
import br.com.palpitou.entity.Jogo;
import br.com.palpitou.entity.Palpite;
import br.com.palpitou.entity.User;
import br.com.palpitou.exception.BusinessRuleException;
import br.com.palpitou.exception.ResourceNotFoundException;
import br.com.palpitou.mapper.PalpiteMapper;
import br.com.palpitou.repository.JogoRepository;
import br.com.palpitou.repository.PalpiteRepository;
import br.com.palpitou.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PalpiteService {

    private final PalpiteRepository palpiteRepository;
    private final JogoRepository jogoRepository;
    private final PalpiteMapper palpiteMapper;
    private final UserRepository userRepository;

    // =========================
    // Métodos auxiliareS
    // =========================

    private Jogo buscarJogo(Long id) {
        return jogoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Jogo não encontrado!"));
    }

    private Palpite buscarPalpite(Long id) {
        return palpiteRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Palpite não encontrado!"));
    }

    private User buscarUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuário não encontrado!"));
    }

    // =======================
    // CRUDD
    // =======================

    public PalpiteResponse salvar(PalpiteRequest palpiteRequest) {

        Jogo jogo =
                buscarJogo(palpiteRequest.getJogoId());


        User user =
                buscarUser(palpiteRequest.getUserId());

        validarPalpiteDuplicado
                (palpiteRequest);

        validarSeJogoComecou(jogo.getDataHora());

        Palpite palpite =
                palpiteMapper.toEntity(
                        palpiteRequest,
                        jogo,
                        user
                );

        Palpite palpiteSalvo =
                palpiteRepository.save(palpite);

        return palpiteMapper.
                toResponse(palpiteSalvo);
    }

    public PalpiteResponse buscar(Long id) {
        Palpite palpite = buscarPalpite(id);
        return palpiteMapper.toResponse(palpite);
    }

    public List<PalpiteResponse> listarTodos() {
        List<Palpite> palpites =
                palpiteRepository.findAll();

        List<PalpiteResponse> response =
                palpites.stream().
                        map(palpiteMapper::toResponse).
                        toList();

        return response;
    }

    public PutResponsePalpite update(
            Long id,
            PutRequestPalpite put
    ) {
        Palpite palpiteBd =
                buscarPalpite(id);

        palpiteBd.alterarPontosObtidos
                (put.getPontosObtidos());

        Palpite palpiteSalvo =
                palpiteRepository.save(palpiteBd);

        return palpiteMapper.toPut(palpiteSalvo);
    }

    public void delete(Long id) {
        Palpite palpite = buscarPalpite(id);
        palpiteRepository.delete(palpite);
    }

    // =========================
    // Regras de negócio
    // =========================

    private void validarPalpiteDuplicado(PalpiteRequest request) {

        if (palpiteRepository.existsByUserIdAndJogoId(
                request.getUserId(),
                request.getJogoId())
        ) {
            throw new BusinessRuleException(
                    "O usuário já possui um " +
                            "palpite para este jogo.");
        }
    }

    private void validarSeJogoComecou(LocalDateTime dataJogo){

        if(!dataJogo.isAfter(LocalDateTime.now())){
            throw new BusinessRuleException(
                    "Não é possível realizar" +
                            " um palpite após o início do jogo."
            );
        }
    }
}
