package br.com.palpitou.dto.putResponse;

import br.com.palpitou.enums.StatusPagamento;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PutResponsePagamento {
    private StatusPagamento status;
}
