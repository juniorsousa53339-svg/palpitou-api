package br.com.palpitou.service;


import br.com.palpitou.repository.JogoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JogoService {
    private final JogoRepository jogoRepository;
}
