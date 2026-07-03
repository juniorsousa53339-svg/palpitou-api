package br.com.palpitou.entity;


import br.com.palpitou.enums.StatusGlobal;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "jogos")
@Getter @Setter
@NoArgsConstructor
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate data;

    @NotNull
    private LocalDateTime dataHora;

    @NotNull
    private int rodada;

    @NotNull
    private int golsMandante;

    @NotNull
    private int golsVisitante;

    @Enumerated(EnumType.STRING)
    private StatusGlobal Status;

}
