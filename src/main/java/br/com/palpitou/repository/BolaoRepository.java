package br.com.palpitou.repository;

import br.com.palpitou.entity.Bolao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface BolaoRepository  extends JpaRepository<Bolao,Long> {

}
