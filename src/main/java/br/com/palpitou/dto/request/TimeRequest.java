package br.com.palpitou.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TimeRequest(
    @NotBlank(message = "O nome é obrigatório")
    String nome,

    @NotBlank(message = "O escudo é obrigatório")
    String escudo,

    @NotBlank(message = "A sigla é obrigatória")
    @Size(min = 2, max = 4, message = "A sigla deve ter entre 2 e 4 caracteres")
    String sigla
) {}
