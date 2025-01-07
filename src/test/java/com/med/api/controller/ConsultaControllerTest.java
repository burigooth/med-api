package com.med.api.controller;

import com.med.api.domain.consultas.AgendaService;
import com.med.api.domain.consultas.DadosAgendamentoConsulta;
import com.med.api.domain.consultas.DadosDetalhesConsulta;
import com.med.api.domain.medico.Especialidade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest // Para testar um controller
@AutoConfigureMockMvc // Para inserir um MockMvc
@AutoConfigureJsonTesters // Para inserir os JacksonTesters
class ConsultaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosAgendamentoConsulta> dadosJson;

    @Autowired
    private JacksonTester<DadosDetalhesConsulta> dadosResponseJson;

    @MockBean // Para simular um serviço
    private AgendaService agendaService;

    @Test
    @DisplayName("Deve devolver cód HTTP 400 quando tentar agendar consulta com dados inválidos.")
    @WithMockUser // Para simular um usuário autenticado
    void agendarCenario1() throws Exception {
        var response = mvc.perform(post("/consultas"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deve devolver cód HTTP 200 quando tentar agendar consulta com dados válidos.")
    @WithMockUser // Para simular um usuário autenticado
    void agendarCenario2() throws Exception {
        var data = LocalDateTime.now().plusHours(1);
        var esp = Especialidade.CARDIOLOGIA;
        var dadosResponse = new DadosDetalhesConsulta(null,1l, 3l, data);
        // Simula o retorno do serviço
        when(agendaService.agendar(any())).thenReturn(dadosResponse);

        var response = mvc.perform(
                post("/consultas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosJson.write(
                                new DadosAgendamentoConsulta(1l, 3l, data, esp)
                        ).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = dadosResponseJson.write(
                dadosResponse
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}