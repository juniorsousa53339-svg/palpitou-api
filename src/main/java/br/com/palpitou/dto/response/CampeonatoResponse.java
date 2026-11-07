package br.com.palpitou.dto.response;

import br.com.palpitou.enums.StatusGlobal;

public record CampeonatoResponse(
    Long id,
    String nome,
    int temporada,
    StatusGlobal status
) {}
