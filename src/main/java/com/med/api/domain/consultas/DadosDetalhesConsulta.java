package com.med.api.domain.consultas;

import java.time.LocalDateTime;

public record DadosDetalhesConsulta(
        Long id,
        Long idMedico,
        Long idPaciente,
        LocalDateTime data) {

    public DadosDetalhesConsulta(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());
    }
}
