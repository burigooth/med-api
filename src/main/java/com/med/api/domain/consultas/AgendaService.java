package com.med.api.domain.consultas;

import com.med.api.domain.consultas.validacoes.ValidadorAgendamentoDeConsulta;
import com.med.api.domain.medico.Medico;
import com.med.api.domain.medico.MedicoRepository;
import com.med.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaService {

    @Autowired
    private ConsultaRepository repository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    // Injetando a minha interface dos validadores
    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadores;

    public DadosDetalhesConsulta agendar(DadosAgendamentoConsulta dados){
        if(!pacienteRepository.existsById(dados.idPaciente())){
            throw new RuntimeException("Paciente não encontrado");
        }

        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())){
            throw new RuntimeException("Médico não encontrado");
        }

        // Fazendo um foreach para percorrer todos os meus validadores e chamar o método validar
        validadores.forEach(v -> v.validar(dados));

        var medico = escolherMedico(dados);
        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var consulta = new Consulta(null, medico, paciente, dados.data(), null);
        if(medico == null){
            throw new RuntimeException("Não existe médico disponível nessa data");
        }
        repository.save(consulta);

        return new DadosDetalhesConsulta(consulta);

    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if(dados.idMedico() != null){
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if(dados.especialidade() == null){
            throw new RuntimeException("Especialidade é obrigatória");
        }

        return medicoRepository.findRandomByEspecialidade(dados.especialidade(), dados.data());
    }

    public void cancelar(DadosCancelamentoConsulta dados) {
        if (!repository.existsById(dados.idConsulta())) {
            throw new RuntimeException("Id da consulta informado não existe!");
        }

        var consulta = repository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());
    }
}
