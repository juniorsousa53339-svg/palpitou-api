package br.com.palpitou.repository;


import br.com.palpitou.entity.Campeonato;
import br.com.palpitou.enums.StatusGlobal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampeonatoRepository extends JpaRepository<Campeonato,Long> {
    boolean existsByNome(String nome);



}
