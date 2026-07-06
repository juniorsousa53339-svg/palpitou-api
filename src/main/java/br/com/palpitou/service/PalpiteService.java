package br.com.palpitou.service;

import br.com.palpitou.repository.PalpiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PalpiteService {

    private final PalpiteRepository palpiteRepository;
}
