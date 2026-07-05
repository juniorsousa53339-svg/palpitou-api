package br.com.palpitou.repository;


import br.com.palpitou.entity.Palpite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PalpiteRepository extends JpaRepository<Palpite,Long> {
}
