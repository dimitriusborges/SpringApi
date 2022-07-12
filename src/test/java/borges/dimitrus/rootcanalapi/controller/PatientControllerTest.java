package borges.dimitrus.rootcanalapi.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final String mockPatient = "{\"id\":1,\"name\":\"New Patient\",\"birthdate\":\"1970-01-01\"}";

    @BeforeEach
    public void insertPatient() throws Exception {

        mockMvc.perform(post("/patients")
                        .content(this.mockPatient)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void insertAndGetPatient() throws Exception {

        MvcResult mvcResult = mockMvc.perform(get("/patients/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();

        assertEquals(mvcResult.getResponse().getContentAsString(), this.mockPatient);


    }

    @Test
    public void updatePatient() throws Exception{

        final String patientNewData = "{\"id\":1,\"name\":\"Updated Patient\",\"birthdate\":\"1970-02-02\"}";

        mockMvc.perform(put("/patients/1")
                        .content(patientNewData)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isAccepted());


        MvcResult mvcResult = mockMvc.perform(get("/patients/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();

        assertEquals(mvcResult.getResponse().getContentAsString(), patientNewData);
    }

    @Test
    public void deletePatient() throws Exception{

       this.insertPatient();

        mockMvc.perform(delete("/patients/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isAccepted());

        MvcResult mvcResult = mockMvc.perform(get("/patients/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent()).andReturn();

        assert(mvcResult.getResponse().getContentAsString().isEmpty());
    }


}