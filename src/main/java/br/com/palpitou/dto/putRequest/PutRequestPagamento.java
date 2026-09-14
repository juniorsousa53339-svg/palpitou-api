package br.com.palpitou.dto.putRequest;

import br.com.palpitou.enums.StatusPagamento;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PutRequestPagamento {
    private StatusPagamento status;
}
