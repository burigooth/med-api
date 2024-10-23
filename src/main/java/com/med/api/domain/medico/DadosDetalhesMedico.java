package com.med.api.domain.medico;

import com.med.api.domain.endereco.Endereco;

public record DadosDetalhesMedico(Long id, String nome, String email, String crm, String telefone, Especialidade especialidade, Endereco endereco) {

    public DadosDetalhesMedico(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());
    }
}
