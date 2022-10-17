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
public class GpControllerTest {

    @Inject
    private MockMvc mockMvc;


    @Test
    public void listGp_fail() throws Exception{   // needs jwt token
        mockMvc.perform(MockMvcRequestBuilders.get("/gp/listGp")).
                andExpect(MockMvcResultMatchers.status().is(400)).
                andDo(MockMvcResultHandlers.print()).andReturn();
    }

    @Test
    public void addAppointment_fail() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/appointmentAdd")).
                andExpect(MockMvcResultMatchers.status().is(404)).
                andDo(MockMvcResultHandlers.print()).andReturn();
    }
}
