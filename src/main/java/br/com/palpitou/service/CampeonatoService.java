package br.com.palpitou.service;

import br.com.palpitou.repository.CampeonatoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CampeonatoService {

    private final CampeonatoRepository campeonatoRepository;
}
