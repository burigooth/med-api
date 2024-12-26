package com.med.api.domain.consultas.validacoes;

import com.med.api.domain.consultas.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsulta {

    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();
        var dataAtual = LocalDateTime.now();

        if(dataConsulta.isBefore(dataAtual.plusMinutes(30))){
            throw new RuntimeException("A consulta deve ser agendada com antecedência de 30 minutos");
        }
    }
}
