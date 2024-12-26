package com.med.api.controller;

import com.med.api.domain.consultas.AgendaService;
import com.med.api.domain.consultas.DadosAgendamentoConsulta;
import com.med.api.domain.consultas.DadosCancelamentoConsulta;
import com.med.api.domain.consultas.DadosDetalhesConsulta;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @Autowired
    private AgendaService agendaService;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados){
        var dto = agendaService.agendar(dados);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados) {
        agendaService.cancelar(dados);
        return ResponseEntity.noContent().build();
    }

}
