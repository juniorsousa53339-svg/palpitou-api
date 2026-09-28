package br.com.palpitou.repository;


import br.com.palpitou.entity.Pagamento;
import br.com.palpitou.enums.StatusPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface PagamentoRepository extends JpaRepository<Pagamento,Long> {

    long countByStatus(StatusPagamento status);

    BigDecimal contfaturamento(StatusPagamento status);
}
