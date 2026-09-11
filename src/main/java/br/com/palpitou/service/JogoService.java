package br.com.palpitou.service;
import br.com.palpitou.exception.BusinessRuleException;
import br.com.palpitou.exception.ResourceNotFoundException;


import br.com.palpitou.dto.JogoRequest;
import br.com.palpitou.dto.JogoResponse;
import br.com.palpitou.dto.PutRequestJogo;
import br.com.palpitou.dto.PutResponseJogo;
import br.com.palpitou.entity.Campeonato;
import br.com.palpitou.entity.Jogo;
import br.com.palpitou.entity.Time;
import br.com.palpitou.enums.StatusGlobal;
import br.com.palpitou.mapper.JogoMapper;
import br.com.palpitou.repository.CampeonatoRepository;
import br.com.palpitou.repository.JogoRepository;
import br.com.palpitou.repository.TimeRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JogoService {

    private final JogoRepository jogoRepository;
    private final CampeonatoRepository campeonatoRepository;
    private final TimeRepository timeRepository;
    private final JogoMapper jogoMapper;

    // =========================
    // Métodos auxiliares
    // =========================

    private Campeonato buscarCampeonato(Long id) {
        return campeonatoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Campeonato não encontrado!"));
    }

    private Time buscarTime(Long id) {
        return timeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Time não encontrado!"));
    }

    private Jogo buscarJogo(Long id) {
        return jogoRepository.findById(id)
                .orElseThrow(() ->
                        new  ResourceNotFoundException("jogo não encontrado!"));
    }

    // =========================
    // CRUD
    // =========================

    public JogoResponse salvar(JogoRequest request) {


        Time timeMandante =
                buscarTime(request.getTimeMandanteId());

        Time timeVisitante =
                buscarTime(request.getTimeVisitanteId());

        Campeonato campeonato =
                buscarCampeonato(request.getCampeonatoId());

        validarStatusCampeonato(
                campeonato.getStatus());

        validarTimesDuplicados(
                timeMandante, timeVisitante);

        Jogo jogo = jogoMapper.toEntity
                (
                request,
                campeonato,
                timeMandante,
                timeVisitante
        );

        Jogo jogoSalvo = jogoRepository.save(jogo);
        return jogoMapper.toResponse(jogoSalvo);
    }

    public JogoResponse buscar(Long id) {

        Jogo jogo = buscarJogo(id);
        return jogoMapper.toResponse(jogo);
    }

    public List<JogoResponse> listarTodos() {
        List<Jogo> jogos = jogoRepository.findAll();

        List<JogoResponse> resposta =
                jogos.stream()
                        .map(jogoMapper::toResponse)
                        .toList();

        return resposta;
    }


    public PutResponseJogo updateJogo(Long id, PutRequestJogo putjogo) {


        Jogo jogoBd = buscarJogo(id);

        jogoBd.alterarDados(
                putjogo.getDataHora(),
                putjogo.getRodada(),
                putjogo.getGolsMandante(),
                putjogo.getGolsVisitante(),
                putjogo.getStatus()
        );

        Jogo jogoUpdated = jogoRepository.save(jogoBd);
        return jogoMapper.toPutResponseJogo(jogoUpdated);
    }


    public void delete(Long id) {
        Jogo jogoBd = buscarJogo(id);
        jogoRepository.delete(jogoBd);
    }

    // =========================
    // Regras de negócio
    // =========================


    private void validarStatusCampeonato(StatusGlobal status) {

        if (status == StatusGlobal.FINALIZADA) {
            throw new BusinessRuleException(
                    "Não é permitido cadastrar jogos" +
                            "em um campeonato finalizado."
            );
        }
    }
    private void validarTimesDuplicados(
            Time timeMandante,
            Time timeVisitante
    ) {
        if (timeMandante.getId().equals(timeVisitante.getId())) {
            throw new BusinessRuleException(
                    "O time mandante e o time visitante não podem ser o mesmo."
            );
        }
    }

}
