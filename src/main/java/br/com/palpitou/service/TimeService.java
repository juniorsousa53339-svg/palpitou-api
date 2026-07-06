package br.com.palpitou.service;

import br.com.palpitou.repository.TimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimeService {

    private final TimeRepository timeRepository;
}
