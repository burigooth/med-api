package com.med.api.domain.consultas.validacoes;

import com.med.api.domain.consultas.DadosAgendamentoConsulta;

public interface ValidadorAgendamentoDeConsulta {
    void validar(DadosAgendamentoConsulta dados);
}
