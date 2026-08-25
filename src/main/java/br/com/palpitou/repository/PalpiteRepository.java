package br.com.palpitou.repository;


import br.com.palpitou.entity.Palpite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PalpiteRepository extends JpaRepository<Palpite,Long> {

 boolean existsByUserIdAndJogoId(Long userId, Long jogoId);

    List<Palpite> findByJogoRodada(int rodada);

  List<Palpite> findByJogoRodadaAndUserId(int rodada, Long userId);
}
