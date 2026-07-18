package br.com.palpitou.service;




import br.com.palpitou.dto.JogoRequest;
import br.com.palpitou.dto.JogoResponse;
import br.com.palpitou.entity.Jogo;
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

    public JogoResponse salvar(JogoRequest request) {

        Jogo jogo = jogoMapper.toEntity(request);
        Jogo jogoSalvo = jogoRepository.save(jogo);
        return jogoMapper.toResponse(jogoSalvo);
    }

    public JogoResponse buscar(Long id) {

        Jogo jogo =
                jogoRepository.findById(id).
                        orElseThrow(() ->
                                new RuntimeException
                                        ("Jogo não encontrado!"));

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

    public void delete(Long id) {

        Jogo jogoBd =
                jogoRepository.findById(id)
                        .orElseThrow(()
                                -> new RuntimeException
                                ("Campeonato não encontrado")
                        );

        jogoRepository.delete(jogoBd);
    }
}
