package br.com.palpitou.repository;


import br.com.palpitou.entity.Participacao;
import br.com.palpitou.enums.StatusParticipacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipacaoRepository extends JpaRepository<Participacao,Long> {

    boolean existsByBolaoIdAndUsuarioId(Long bolaoId , Long usuarioId);

    long countByStatus(StatusParticipacao status);

    long countByBolaoIdAndStatus(Long bolaoId, StatusParticipacao status);
}
