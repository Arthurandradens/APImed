package med.voll.apimed.controller;

import med.voll.apimed.ApimedApplication;
import med.voll.apimed.dto.MedicoDTO;
import med.voll.apimed.dto.MedicoResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest(classes = ApimedApplication.class)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class MedicoControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    JacksonTester<MedicoResponseDTO> medicoResponseDTO;
    @Autowired
    JacksonTester<MedicoDTO> medicoDTO;

    @Test
    @DisplayName("Devolve 400 quando a informações são inválidas")
    void cenario_1() throws Exception {
        var response = mvc.perform(post("/medicos"))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    }

//    @Test
//    @DisplayName("Devolve 200 quando as informações são válidas")
//    void cenario_2() throws Exception {
//        var medicoResponse = new MedicoResponseDTO()
//        var response = mvc.perform(post("/medicos")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content())
//                .andReturn()
//                .getResponse();
//
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
//
//    }
}