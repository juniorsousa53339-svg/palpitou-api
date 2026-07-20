package br.com.palpitou.service;

import br.com.palpitou.dto.TimeRequest;
import br.com.palpitou.dto.TimeResponse;
import br.com.palpitou.entity.Time;
import br.com.palpitou.mapper.TimeMapper;
import br.com.palpitou.repository.TimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeService {

    private final TimeRepository timeRepository;
    private final TimeMapper timeMapper;


    // =========================
    // Métodos auxiliares
    // =========================

    private Time buscarTime(Long id) {
        return timeRepository.findById(id).
                orElseThrow(() ->
                        new RuntimeException
                                ("Time não encontrado!"));
    }

    private void validName(TimeRequest request) {
        if (timeRepository.existsByNome(request.getNome())) {
            throw new RuntimeException("Time já cadastrado.");
        }
    }

    // =========================
    // CRUD
    // =========================

    public TimeResponse salvar(TimeRequest request) {

        validName(request);

        Time time = timeMapper.toEntity(request);

        Time timeSalvo = timeRepository.save(time);

        return timeMapper.toResponse(timeSalvo);
    }

    public TimeResponse buscar(Long id) {
        Time time = buscarTime(id);
        return timeMapper.toResponse(time);
    }

    public List<TimeResponse> listarTodos() {

        List<Time> times =
                timeRepository.findAll();

        List<TimeResponse> resposta =
                times.stream()
                        .map(timeMapper::toResponse)
                        .toList();

        return resposta;

    }

    public  TimeResponse atualizar(Long id, TimeRequest request) {

        Time timeBd = buscarTime(id);

        timeBd.altDados(

                request.getNome(),
                request.getEscudo(),
                request.getSigla()
        );

        validName(request);

        Time timeAtualizado = timeRepository.save(timeBd);
        return timeMapper.toResponse(timeAtualizado);
    }

    public void delete(Long id) {
        Time timeBd = buscarTime(id);
        timeRepository.delete(timeBd);
    }


}
