package br.com.palpitou.entity;

import br.com.palpitou.enums.StatusGlobal;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "campeonatos")
@NoArgsConstructor
@Getter @Setter
public class Campeonato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank
    private String nome;

    @Column(nullable = false)
    @NotNull
    private int temporada;

    @Enumerated(EnumType.STRING)
    private StatusGlobal status;

    @OneToMany(mappedBy = "campeonato")
    private List<Bolao> boloes = new ArrayList<>();

    @OneToMany(mappedBy = "campeonato")
    private List<Jogo> jogos = new ArrayList<>();

    public void alterarDados(
            String nome,
            int temporada,
            StatusGlobal status
    ) {

        this.nome = nome;
        this.temporada = temporada;
        this.status = status;
    }
}

