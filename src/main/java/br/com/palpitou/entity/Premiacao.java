package br.com.palpitou.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "premiacoes")
public class Premiacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private int posicao;

    @NotNull
    private BigDecimal valor;

    @NotBlank
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "bolao_id")
    private Bolao bolao;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
