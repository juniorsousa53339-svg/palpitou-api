package br.com.palpitou.entity;


import br.com.palpitou.enums.StatusPagamento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "pagamentos")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    private StatusPagamento status;

    @NotBlank
    private String nomePagadorPix;

    private String comprovante;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @OneToOne(mappedBy = "pagamento")
    private Participacao participacao;

    public void alterarDados(StatusPagamento status) {
        this.status = status;
    }
}
