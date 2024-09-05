package med.voll.apimed.controller;

import med.voll.apimed.ApimedApplication;
import med.voll.apimed.dto.AgendamentoDTO;
import med.voll.apimed.dto.ConsultaResponseDTO;
import med.voll.apimed.model.Especialidade;
import med.voll.apimed.service.AgendaDeConsultasService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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

@SpringBootTest(classes = ApimedApplication.class)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class ConsultaControllerTest {
    @Autowired
    MockMvc mvc;
    @Autowired
    private JacksonTester<AgendamentoDTO> agendamentoDTOJson;

    @Autowired
    private JacksonTester<ConsultaResponseDTO> consultaResponseDTOJson;

    @MockBean
    private AgendaDeConsultasService agendaDeConsulta;

    @Test
    @DisplayName("Devolve código http 400 quando informações inválidas")
    @WithMockUser
    void agendar_cenario1() throws Exception {
        var response = mvc.perform(post("/consultas"))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Devolve código http 200 quando informações válidas")
    @WithMockUser
    void agendar_cenario2() throws Exception {

        var data = LocalDateTime.now().plusHours(1);
        var especiaidade = Especialidade.CARDIOLOGIA;

        var dadosDetalhamento = new ConsultaResponseDTO(null,2L,3L,data);
        when(agendaDeConsulta.agendar(any())).thenReturn(dadosDetalhamento);

        var response = mvc.perform(post("/consultas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(agendamentoDTOJson.write(
                                new AgendamentoDTO(2L, 3L,data,especiaidade)
                        ).getJson())
                )
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = consultaResponseDTOJson.write(
                dadosDetalhamento
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

}