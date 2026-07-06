package br.com.palpitou.service;

import br.com.palpitou.repository.BolaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BolaoService {

    private final BolaoRepository bolaoRepository;
}
