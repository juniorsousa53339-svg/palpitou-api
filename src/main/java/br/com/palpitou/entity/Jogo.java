package br.com.palpitou.entity;


import br.com.palpitou.enums.StatusGlobal;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "jogos")
@Getter @Setter
@NoArgsConstructor
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull
    private LocalDateTime dataHora;

    @NotNull
    private int rodada;

    @NotNull
    private int golsMandante;

    @NotNull
    private int golsVisitante;

    @Enumerated(EnumType.STRING)
    private StatusGlobal status;

    @ManyToOne
    @JoinColumn(name = "campeonato_id")
    private Campeonato campeonato;



    @OneToMany(mappedBy = "jogo")
    private List<Palpite> palpites = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "time_mandante_id")
    private Time timeMandante;


    @ManyToOne
    @JoinColumn(name = "time_visitante_id")
    private Time timeVisitante;


    public void alterarDados(

            LocalDateTime dataHora,
            int rodada,
            int golsMandante,
            int golsVisitante,
            StatusGlobal status
    ) {

        this.dataHora = dataHora;
        this.rodada = rodada;
        this.golsMandante = golsMandante;
        this.golsVisitante = golsVisitante;
        this.status = status;
    }

}
