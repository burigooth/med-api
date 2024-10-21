package com.med.api.medico;

public record DadosListagemMedico(String nome, String email, String crm, Especialidade especialidade) {
    // Construtor que recebe um objeto da classe Medico
    public DadosListagemMedico(Medico medico){
        this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
