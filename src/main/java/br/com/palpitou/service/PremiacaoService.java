package br.com.palpitou.service;



import br.com.palpitou.dto.PremiacaoResponse;
import br.com.palpitou.entity.Premiacao;
import br.com.palpitou.exception.ResourceNotFoundException;
import br.com.palpitou.mapper.PremiacaoMapper;
import br.com.palpitou.repository.PremiacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PremiacaoService {

    private final PremiacaoRepository premiacaoRepository;
    private final PremiacaoMapper premiacaoMapper;

    // ========================
    // Métodos auxiliares
    // ========================

    private Premiacao buscarPremiacao(Long id) {
        return premiacaoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Premiação não encontrada!"));
    }

    // ========================
    // CRUD
    // ========================

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
