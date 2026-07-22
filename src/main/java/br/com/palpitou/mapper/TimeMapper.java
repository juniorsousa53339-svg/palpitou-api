package br.com.palpitou.mapper;


import br.com.palpitou.dto.TimeRequest;
import br.com.palpitou.dto.TimeResponse;
import br.com.palpitou.entity.Time;
import org.springframework.stereotype.Component;

@Component
public class TimeMapper {

    public Time toEntity(TimeRequest request) {

        Time time = new Time();

        time.setNome(request.getNome());
        time.setEscudo(request.getEscudo());
        time.setSigla(request.getSigla());
        return time;
    }

    public TimeResponse toResponse(Time time) {
        TimeResponse timeResponse = new TimeResponse();

        timeResponse.setNome(time.getNome());
        timeResponse.setEscudo(time.getEscudo());
        timeResponse.setSigla(time.getSigla());
        timeResponse.setId(time.getId());
        return timeResponse;
    }
}
