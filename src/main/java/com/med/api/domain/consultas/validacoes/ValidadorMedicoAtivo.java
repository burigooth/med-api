package com.med.api.domain.consultas.validacoes;

import com.med.api.domain.consultas.DadosAgendamentoConsulta;
import com.med.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo  implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private MedicoRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {
        if(dados.idMedico() == null){
            return;
        }

        if(!repository.findById(dados.idMedico()).get().getAtivo()){
            throw new RuntimeException("Médico inativo");
        }
    }
}
