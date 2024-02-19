package med.souza.api.controller;

import med.souza.api.domain.consultation.ConsultationDetailingData;
import med.souza.api.domain.consultation.ConsultationSaveData;
import med.souza.api.domain.consultation.ConsultationService;
import med.souza.api.domain.doctor.SpecialtyEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<ConsultationSaveData> consultationSaveDataJson;

    @Autowired
    private JacksonTester<ConsultationDetailingData> consultationDetailingDataJson;

    @MockBean
    private ConsultationService consultationService;

    @Test
    @DisplayName("Deveria retornar código HTTP 400 quando dados são inválidos")
    @WithMockUser
    void scheduleAppointment1() throws Exception {
        MockHttpServletResponse response = request(post("/consultations"));
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria retornar código HTTP 200 quando dados são válidos")
    @WithMockUser
    void scheduleAppointment2() throws Exception {
        //Given
        long idDoctor = 1L;
        long idPatient = 1L;
        LocalDateTime date = LocalDateTime.now().plusHours(2);
        SpecialtyEnum specialty = SpecialtyEnum.CARDIOLOGIA;

        ConsultationDetailingData consultationDetailingData = new ConsultationDetailingData(1L, idDoctor, idPatient, date);
        ConsultationSaveData consultationSaveData = new ConsultationSaveData(idDoctor, idPatient, date, specialty);

        Mockito.when(consultationService.toSchedule(Mockito.any())).thenReturn(consultationDetailingData);

        String jsonRequest = consultationSaveDataJson.write(consultationSaveData).getJson();
        String jsonResponse = consultationDetailingDataJson.write(consultationDetailingData).getJson();

        //When
        MockHttpServletResponse response = request(
                post("/consultations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest)
        );

        //Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonResponse);
    }

    private MockHttpServletResponse request(MockHttpServletRequestBuilder method) throws Exception {
        return mockMvc.perform(method).andReturn().getResponse();
    }
}