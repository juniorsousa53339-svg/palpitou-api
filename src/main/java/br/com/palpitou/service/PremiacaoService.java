package br.com.palpitou.service;



import br.com.palpitou.repository.PremiacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PremiacaoService {
    private final PremiacaoRepository premiacaoRepository;
}
