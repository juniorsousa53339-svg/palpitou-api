package br.com.palpitou.entity;


import br.com.palpitou.enums.StatusParticipacao;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter @Setter
@Table(name = "partipacoes")
public class Participacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private StatusParticipacao status;

    @NotNull
    private LocalDate dataInscricao;

    private int pontos;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User usuario;

    @OneToOne(cascade = CascadeType.ALL)
    private Pagamento pagamento;

    @ManyToOne
    @JoinColumn(name = "bolao_id")
    private Bolao bolao;

    public void alterarDados(
            int pontos,
            StatusParticipacao status
    ) {
        this.pontos = pontos;
        this.status = status;
    }

}
