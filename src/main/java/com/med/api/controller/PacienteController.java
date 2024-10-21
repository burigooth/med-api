package com.med.api.controller;

import com.med.api.paciente.DadosCadastroPaciente;
import com.med.api.paciente.ListarDadosPaciente;
import com.med.api.paciente.Paciente;
import com.med.api.paciente.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    public void cadastrar(@RequestBody @Valid DadosCadastroPaciente dados){
        pacienteRepository.save(new Paciente(dados));
    }

    @GetMapping
    public Page<ListarDadosPaciente> listar(Pageable paginacao){
        return pacienteRepository.findAll(paginacao).map(ListarDadosPaciente::new);
    }
}
