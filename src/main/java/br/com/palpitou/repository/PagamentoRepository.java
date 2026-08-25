package br.com.palpitou.repository;


import br.com.palpitou.entity.Pagamento;
import br.com.palpitou.enums.StatusPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento,Long> {


}
