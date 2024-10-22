package com.med.api.paciente;

public record ListarDadosPaciente(Long id, String nome, String email) {

    public ListarDadosPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail());
    }
}
