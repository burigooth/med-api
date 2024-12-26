package com.med.api.domain.consultas.validacoes;

import com.med.api.domain.consultas.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamento  implements ValidadorAgendamentoDeConsulta {

    public void validar(DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data();
        var domingo = dataConsulta.getDayOfWeek() == DayOfWeek.SUNDAY;
        var antesDas7 = dataConsulta.getHour() < 7;
        var depoisDas18 = dataConsulta.getHour() > 18;

        if(domingo || antesDas7 || depoisDas18){
            throw new RuntimeException("Horário de funcionamento: Segunda a Sábado, das 07:00 às 18:00");
        }
    }

}
