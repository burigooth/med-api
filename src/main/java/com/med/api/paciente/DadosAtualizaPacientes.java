package com.med.api.paciente;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizaPacientes(
        @NotNull
        Long id,
        String nome,
        String telefone
){

}
