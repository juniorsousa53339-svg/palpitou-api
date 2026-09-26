package br.com.palpitou.repository;


import br.com.palpitou.entity.Participacao;
import br.com.palpitou.enums.StatusPagamento;
import br.com.palpitou.enums.StatusParticipacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipacaoRepository extends JpaRepository<Participacao,Long> {

    boolean existsByBolaoIdAndUsuarioId(Long bolaoId , Long usuarioId);

    long countByStatus(StatusParticipacao status);

}
