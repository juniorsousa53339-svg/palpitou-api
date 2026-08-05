package br.com.palpitou.service;



import br.com.palpitou.dto.PremiacaoRequest;
import br.com.palpitou.dto.PremiacaoResponse;
import br.com.palpitou.entity.Bolao;
import br.com.palpitou.entity.Participacao;
import br.com.palpitou.entity.Premiacao;
import br.com.palpitou.entity.User;
import br.com.palpitou.mapper.PremiacaoMapper;
import br.com.palpitou.repository.BolaoRepository;
import br.com.palpitou.repository.PremiacaoRepository;
import br.com.palpitou.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PremiacaoService {

    private final PremiacaoRepository premiacaoRepository;
    private final BolaoRepository bolaoRepository;
    private final UserRepository userRepository;
    private final PremiacaoMapper premiacaoMapper;

    // ========================
    // Métodos auxiliares
    // ========================

    private Premiacao buscarPremiacao(Long id) {
        return premiacaoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Premiação não encontrada!"));
    }

    private Bolao buscarBolao(Long id) {
        return bolaoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Bolão não encontrado!"));
    }

    private User buscarUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Usuario não encontrado!"));
    }

    // ========================
    // CRUD
    // ========================

    public PremiacaoResponse salvar(PremiacaoRequest request) {

        User user =
                buscarUser(request.getUserId());

        Bolao bolao =
                buscarBolao(request.getBolaoId());


        Premiacao premiacao =
                premiacaoMapper.toEntity(
                request,bolao,user
        );

        Premiacao premiacaoSalvo =
                premiacaoRepository.save(premiacao);

        return premiacaoMapper.toResponse(premiacaoSalvo);
    }

    public PremiacaoResponse buscar(Long id) {
    Premiacao premiacao = buscarPremiacao(id);
    return premiacaoMapper.toResponse(premiacao);
    }

    public List<PremiacaoResponse> listarTodos() {
        List<Premiacao> premiacoes =
                premiacaoRepository.findAll();

        List<PremiacaoResponse> response =
                premiacoes.stream().map(premiacaoMapper::toResponse)
                        .toList();
        return response;
    }

    public void delete(Long id) {
        Premiacao premiacao = buscarPremiacao(id);
        premiacaoRepository.delete(premiacao);
    }

    // =========================
    // Regras de negócio
    // =========================
}
