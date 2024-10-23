package com.med.api.domain.paciente;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizaPacientes(
        @NotNull
        Long id,
        String nome,
        String telefone
){

}
