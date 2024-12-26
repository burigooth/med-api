package com.med.api.domain.consultas.validacoes;

import com.med.api.domain.consultas.ConsultaRepository;
import com.med.api.domain.consultas.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ValidadorPacienteJaComConsulta  implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {
        LocalDate dataConsulta = dados.data().toLocalDate();

        var pacienteComConsultaNoMesmoDia = repository.existsByPacienteIdAndDataBetween(
                dados.idPaciente(),
                dataConsulta.atStartOfDay(),
                dataConsulta.atTime(23, 59, 59)
        );

          if (pacienteComConsultaNoMesmoDia) {
                throw new RuntimeException("Paciente já possui consulta agendada nesse dia");
          }
    }
}