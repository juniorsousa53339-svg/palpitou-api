package br.com.palpitou.service;

import br.com.palpitou.dto.CampeonatoRequest;
import br.com.palpitou.dto.CampeonatoResponse;
import br.com.palpitou.entity.Campeonato;
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

    public CampeonatoResponse salvar(CampeonatoRequest request) {

        if (campeonatoRepository.existsByNome(request.getNome())) {
            throw new RuntimeException("Campeonato já cadastrado.");
        }

        Campeonato camp = campeonatoMapper.toEntity(request);
        Campeonato campSalvo = campeonatoRepository.save(camp);
        return campeonatoMapper.toResponse(campSalvo);
    }

    public CampeonatoResponse buscarPorId(Long id) {

        Campeonato camp =
                campeonatoRepository.findById(id).
                        orElseThrow(() ->
                                new RuntimeException
                                        ("Campeonato não encontrado!"));

        return campeonatoMapper.toResponse(camp);
    }


    public List<CampeonatoResponse> buscarTodos() {
        List<Campeonato> camp = campeonatoRepository.findAll();

        List<CampeonatoResponse> resposta =
                camp.stream()
                        .map(campeonatoMapper::toResponse)
                        .toList();

        return resposta;
    }

    public CampeonatoResponse updateCamp(Long campeonatoId, CampeonatoRequest request) {

        Campeonato campBd =
                campeonatoRepository.findById(campeonatoId)
                        .orElseThrow(()
                                -> new RuntimeException("Campeonato não encontrado!"));

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

    public void deleteCampeonato(Long campeonatoId) {

        Campeonato campBd =
                campeonatoRepository.findById(campeonatoId)
                        .orElseThrow(()
                                -> new RuntimeException
                                ("Campeonato não encontrado")
                        );

        campeonatoRepository.delete(campBd);
    }

}
