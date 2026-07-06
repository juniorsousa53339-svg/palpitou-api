package br.com.palpitou.service;



import br.com.palpitou.repository.ParticipacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParticipacaoService {

    private final ParticipacaoRepository participacaoRepository;
}
