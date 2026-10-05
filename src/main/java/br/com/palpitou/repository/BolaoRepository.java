package br.com.palpitou.repository;

import br.com.palpitou.entity.Bolao;
import br.com.palpitou.enums.StatusParticipacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BolaoRepository extends JpaRepository<Bolao, Long> {


    @Query("""
                SELECT COUNT(p)
                FROM Bolao b
                JOIN b.participacoes p
                WHERE b.id = :bolaoId
                  AND p.status = :status
            """)
    long countByBolaoIdAndStatus(
            @Param("bolaoId") Long bolaoId,
            @Param("status") StatusParticipacao status
    );

}
