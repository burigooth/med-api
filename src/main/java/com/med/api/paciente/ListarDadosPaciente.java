package com.med.api.paciente;

public record ListarDadosPaciente(String nome, String email) {

    public ListarDadosPaciente(Paciente paciente) {
        this(paciente.getNome(), paciente.getEmail());
    }
}
