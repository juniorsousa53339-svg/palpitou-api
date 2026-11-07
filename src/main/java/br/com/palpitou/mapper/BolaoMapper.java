package br.com.palpitou.mapper;

import br.com.palpitou.dto.request.BolaoRequest;
import br.com.palpitou.dto.response.BolaoResponse;
import br.com.palpitou.dto.putResponse.PutResponseBolao;
import br.com.palpitou.entity.Bolao;
import br.com.palpitou.entity.Campeonato;
import org.springframework.stereotype.Component;

@Component
public class BolaoMapper {

    public Bolao toEntity(

            BolaoRequest request,
            Campeonato campeonato
    ){

        Bolao bolao = new Bolao();


        bolao.setNome(request.nome());
        bolao.setValorInscricao(request.valorInscricao());
        bolao.setDataInicio(request.dataInicio());
        bolao.setDataFim(request.dataFim());
        bolao.setStatus(request.status());
        bolao.setCampeonato(campeonato);

        return bolao;
    }

    public BolaoResponse toResponse(Bolao bolao){
        return new BolaoResponse(
            bolao.getNome(),
            bolao.getValorInscricao(),
            bolao.getDataInicio(),
            bolao.getDataFim(),
            bolao.getStatus()
        );
    }

    public PutResponseBolao toPutResponseBolao(Bolao bolao){
        return new PutResponseBolao(
            bolao.getNome(),
            bolao.getValorInscricao(),
            bolao.getDataInicio(),
            bolao.getDataFim(),
            bolao.getStatus()
        );
    }
}
