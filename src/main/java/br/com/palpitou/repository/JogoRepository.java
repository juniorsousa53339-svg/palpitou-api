package br.com.palpitou.repository;

import br.com.palpitou.entity.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JogoRepository extends JpaRepository<Jogo,Long> {
}
