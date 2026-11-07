package br.com.palpitou.mapper;

import br.com.palpitou.dto.request.TimeRequest;
import br.com.palpitou.dto.response.TimeResponse;
import br.com.palpitou.entity.Time;
import org.springframework.stereotype.Component;

@Component
public class TimeMapper {

    public Time toEntity(TimeRequest request) {
        Time time = new Time();
        time.setNome(request.nome());
        time.setEscudo(request.escudo());
        time.setSigla(request.sigla());
        return time;
    }

    public TimeResponse toResponse(Time time) {
        return new TimeResponse(
            time.getId(),
            time.getNome(),
            time.getEscudo(),
            time.getSigla()
        );
    }
}
