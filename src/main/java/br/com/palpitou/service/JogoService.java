package br.com.palpitou.service;


import br.com.palpitou.dto.JogoRequest;
import br.com.palpitou.dto.JogoResponse;
import br.com.palpitou.dto.PutRequestJogo;
import br.com.palpitou.dto.PutResponseJogo;
import br.com.palpitou.entity.Campeonato;
import br.com.palpitou.entity.Jogo;
import br.com.palpitou.entity.Time;
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
                        new RuntimeException("Campeonato não encontrado!"));
    }

    private Time buscarTime(Long id) {
        return timeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Time não encontrado!"));
    }

    private Jogo buscarJogo(Long id){
        return jogoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("jogo não encontrado!"));
    }


    // =========================
    // CRUD
    // =========================

    public JogoResponse salvar(JogoRequest request) {

        Campeonato campeonato = buscarCampeonato(request.getCampeonatoId());

        Time timeMandante = buscarTime(request.getTimeMandanteId());

        Time timeVisitante = buscarTime(request.getTimeVisitanteId());

        Jogo jogo = jogoMapper.toEntity(
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
                putjogo.getData(),
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
}
