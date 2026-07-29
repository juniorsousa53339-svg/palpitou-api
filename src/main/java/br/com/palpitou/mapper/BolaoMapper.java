package br.com.palpitou.mapper;

import br.com.palpitou.dto.BolaoRequest;
import br.com.palpitou.dto.BolaoResponse;
import br.com.palpitou.dto.PutResponseBolao;
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


        bolao.setNome(request.getNome());
        bolao.setValorInscricao(request.getValorInscricao());
        bolao.setDataInicio(request.getDataInicio());
        bolao.setDataFim(request.getDataFim());
        bolao.setStatus(request.getStatus());
        bolao.setCampeonato(campeonato);

        return bolao;
    }

    public BolaoResponse toResponse(Bolao bolao){

        BolaoResponse response = new BolaoResponse();

        response.setNome(bolao.getNome());
        response.setValorInscricao(bolao.getValorInscricao());
        response.setDataInicio(bolao.getDataInicio());
        response.setDataFim(bolao.getDataFim());
        response.setStatus(bolao.getStatus());
        return response;
    }

    public PutResponseBolao toPutResponseBolao(Bolao bolao){

        PutResponseBolao putResponseBolao = new PutResponseBolao();

        putResponseBolao.setNome(bolao.getNome());
        putResponseBolao.setValorInscricao(bolao.getValorInscricao());
        putResponseBolao.setDataInicio(bolao.getDataInicio());
        putResponseBolao.setDataFim(bolao.getDataFim());
        putResponseBolao.setStatus(bolao.getStatus());
        return putResponseBolao;
    }
}
