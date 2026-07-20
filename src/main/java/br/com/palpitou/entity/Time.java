package br.com.palpitou.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter @Setter
@Table(name = "times")
public class Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String escudo;

    @NotBlank
    private String sigla;

    @OneToMany
    private List<Jogo> jogos = new ArrayList<>();

    public void altDados(

            String nome,
            String escudo,
            String sigla
    ) {
        this.nome = nome;
        this.escudo = escudo;
        this.sigla = sigla;

    }
}
