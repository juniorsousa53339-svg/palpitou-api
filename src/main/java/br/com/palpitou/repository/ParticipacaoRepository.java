package br.com.palpitou.repository;

import br.com.palpitou.entity.Participacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipacaoRepository extends JpaRepository<Participacao,Long> {

    boolean existsByBolaoIdAndUserId(Long idBolao , Long idUser);
}
