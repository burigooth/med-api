package com.med.api.domain.medico;

import com.med.api.domain.endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "medicos")
@Entity(name = "Medico")
// Lombok
@Getter // Gera os getters
@NoArgsConstructor // Gera o construtor sem argumentos
@AllArgsConstructor // Gera o construtor com todos os argumentos
@EqualsAndHashCode(of = "id") // Gera os métodos equals e hashCode apenas considerando o atributo id
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String crm;
    private String telefone;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    private Boolean ativo;

    @Embedded // Embedded quer dizer que os atributos da classe Endereco serão mapeados na mesma tabela do Medico
    private Endereco endereco;

    public Medico(DadosCadastroMedico dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.crm = dados.crm();
        this.telefone = dados.telefone();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());

    }

    public void atualizarInfos(DadosAtualizaMedicos dados) {
        if(dados.nome() != null) {
            this.nome = dados.nome();
        }
        if(dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if(dados.endereco() != null) {
            this.endereco.atualizaInfos(dados.endereco());
        }
    }

    public void desativar() {
        this.ativo = false;
    }
}
