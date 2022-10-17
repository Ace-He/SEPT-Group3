package com.Group3;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.inject.Inject;

@SpringBootTest
@AutoConfigureMockMvc
public class AppointmentControllerTest {

    @Inject
    private MockMvc mockMvc;


    @Test
    public void addAppointment_success() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/appointment/add")).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print()).andReturn();
    }

    @Test
    public void addAppointment_fail() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/appointmentAdd")).
                andExpect(MockMvcResultMatchers.status().is(404)).
                andDo(MockMvcResultHandlers.print()).andReturn();
    }

    @Test
    public void cancelAppointment_success() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/appointment/cancel")).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print()).andReturn();
    }

    @Test
    public void cancelAppointment_fail() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/appointment/delete")).
                andExpect(MockMvcResultMatchers.status().is(404)).
                andDo(MockMvcResultHandlers.print()).andReturn();
    }



}
