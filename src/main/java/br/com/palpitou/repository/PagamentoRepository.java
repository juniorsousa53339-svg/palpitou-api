package br.com.palpitou.repository;


import br.com.palpitou.entity.Pagamento;
import br.com.palpitou.enums.StatusPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

    long countByStatus(StatusPagamento status);


    @Query(
            "SELECT COALESCE(SUM(p.valor), 0)" +
                    " FROM Pagamento p" +
                    " WHERE p.status = :status"
    )
    BigDecimal sumFaturamento(
            @Param("status")
            StatusPagamento status
    );
}
