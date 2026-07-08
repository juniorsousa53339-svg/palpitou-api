package br.com.palpitou.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "palpites")
public class Palpite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int golsMandante;

    private int golsVisitante;

    @NotNull
    private int pontosObtidos;

    @ManyToOne
    @JoinColumn(name = "jogo_id")
    private Jogo jogo;

}
