package br.com.palpitou.dto.request;

import br.com.palpitou.enums.StatusGlobal;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;

public record CampeonatoRequest(
    @NotBlank(message = "O nome é obrigatório")
    String nome,

    @Min(value = 2000, message = "A temporada deve ser válida")
    int temporada,

    @NotNull(message = "O status é obrigatório")
    StatusGlobal status
) {}
