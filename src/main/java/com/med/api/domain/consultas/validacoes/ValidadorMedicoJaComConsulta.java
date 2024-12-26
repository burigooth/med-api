package com.med.api.domain.consultas.validacoes;

import com.med.api.domain.consultas.ConsultaRepository;
import com.med.api.domain.consultas.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoJaComConsulta  implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {
        var medicoComConsultaNoMesmoHorario = repository.existsByMedicoIdAndData(dados.idMedico(), dados.data().toLocalDate().atStartOfDay());

        if (medicoComConsultaNoMesmoHorario) {
            throw new RuntimeException("Médico já possui consulta agendada para o horário");
        }
    }
}
