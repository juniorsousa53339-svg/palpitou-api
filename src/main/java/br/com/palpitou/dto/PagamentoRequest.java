package br.com.palpitou.dto;


import br.com.palpitou.entity.Bolao;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter @Setter
public class PagamentoRequest {

    private BigDecimal valor;
    private String nomePagadorPix;
    private String comprovante;
    private Long userId;
    private Long bolaoId;
}
