package br.com.palpitou.service;

import br.com.palpitou.dto.CampeonatoRequest;
import br.com.palpitou.dto.CampeonatoResponse;
import br.com.palpitou.entity.Campeonato;
import br.com.palpitou.entity.User;
import br.com.palpitou.mapper.CampeonatoMapper;
import br.com.palpitou.repository.CampeonatoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampeonatoService {

    private final CampeonatoRepository campeonatoRepository;
    private final CampeonatoMapper campeonatoMapper;

    // =========================
    // Métodos auxiliares
    // =========================

    private Campeonato buscarCamp(Long id) {
        return campeonatoRepository.findById(id).
                orElseThrow(() ->
                        new RuntimeException
                                ("Campeonato não encontrado!"));
    }

    // =========================
    // CRUD
    // =========================

    public CampeonatoResponse salvar(CampeonatoRequest request) {

        if (campeonatoRepository.existsByNome(request.getNome())) {
            throw new RuntimeException("Campeonato já cadastrado.");
        }

        Campeonato camp = campeonatoMapper.toEntity(request);
        Campeonato campSalvo = campeonatoRepository.save(camp);
        return campeonatoMapper.toResponse(campSalvo);
    }

    public CampeonatoResponse buscar(Long id) {

        Campeonato camp = buscarCamp(id);

        return campeonatoMapper.toResponse(camp);
    }


    public List<CampeonatoResponse> listarTodos() {
        List<Campeonato> camp = campeonatoRepository.findAll();

        List<CampeonatoResponse> resposta =
                camp.stream()
                        .map(campeonatoMapper::toResponse)
                        .toList();

        return resposta;
    }

    public CampeonatoResponse updateCamp(Long campeonatoId, CampeonatoRequest request) {

        Campeonato campBd = buscarCamp(campeonatoId);

        campBd.alterarDados(

                request.getNome(),
                request.getTemporada(),
                request.getStatus()
        );

        if (campeonatoRepository.existsByNome(request.getNome())) {
            throw new RuntimeException("Campeonato já cadastrado.");
        }

        Campeonato campAtualizado = campeonatoRepository.save(campBd);
        return campeonatoMapper.toResponse(campAtualizado);
    }

    public void delete(Long campeonatoId) {

        Campeonato campBd = buscarCamp(campeonatoId);
        campeonatoRepository.delete(campBd);
    }

}
