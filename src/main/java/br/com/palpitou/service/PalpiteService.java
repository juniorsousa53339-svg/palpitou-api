package br.com.palpitou.service;

import br.com.palpitou.dto.*;
import br.com.palpitou.entity.Jogo;
import br.com.palpitou.entity.Palpite;
import br.com.palpitou.mapper.PalpiteMapper;
import br.com.palpitou.repository.JogoRepository;
import br.com.palpitou.repository.PalpiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PalpiteService {

    private final PalpiteRepository palpiteRepository;
    private final JogoRepository jogoRepository;
    private final PalpiteMapper palpiteMapper;

    // =========================
    // Métodos auxiliares
    // =========================

    private Jogo buscarJogo(Long id) {
        return jogoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Jogo não encontrado!"));
    }

    private Palpite buscarPalpite(Long id) {
        return palpiteRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Palpite não encontrado!"));
    }

    // =========================
    // CRUD
    // =========================

    public PalpiteResponse salvar(PalpiteRequest palpiteRequest) {

        Jogo jogo =
                buscarJogo(palpiteRequest.getJogoId());

        Palpite palpite =
                palpiteMapper.toEntity(
                        palpiteRequest,
                        jogo
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
}
