package br.com.palpitou.entity;

import br.com.palpitou.enums.StatusGlobal;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "bolao")
@Getter
@Setter
@NoArgsConstructor
public class Bolao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    private BigDecimal valorInscricao;

    @Enumerated(EnumType.STRING)
    private StatusGlobal Status;

    @NotNull
    private LocalDateTime dataInicio;

    @NotNull
    private LocalDateTime dataFim;

}
