package com.med.api.controller;

import com.med.api.domain.endereco.DadosEndereco;
import com.med.api.domain.medico.DadosCadastroMedico;
import com.med.api.domain.medico.DadosDetalhesMedico;
import com.med.api.domain.medico.Especialidade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class MedicoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosCadastroMedico> dadosJson;

    @Test
    @WithMockUser
    @DisplayName("Deve retornar status 201 ao cadastrar médicos.")
    void cadastrar() throws Exception {
        var endereco = new DadosEndereco("Rua Teste", "0", "88888888", "Teste", "300", "Teste", "TS");
        DadosCadastroMedico dados = new DadosCadastroMedico("Dr. House", "drhouse@test.com", "123456789", "333453", Especialidade.CARDIOLOGIA, endereco);

        var response = mvc.perform(
                post("/medicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosJson.write(dados).getJson())
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }
}