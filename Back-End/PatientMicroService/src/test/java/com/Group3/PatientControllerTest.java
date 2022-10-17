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
public class PatientControllerTest {

    @Inject
    private MockMvc mockMvc;

    @Test
    public void editHealthInfo_fail() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/patient/edit/info/")).
                andExpect(MockMvcResultMatchers.status().is(404)).
                andDo(MockMvcResultHandlers.print()).andReturn();
    }


    @Test
    public void editStatus_fail() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/patient/edit/status")).  // method should be post
                andExpect(MockMvcResultMatchers.status().is(405)).
                andDo(MockMvcResultHandlers.print()).andReturn();
    }


    @Test
    public void sendMsg_fail() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/patient/sendMsg/4")).
                andExpect(MockMvcResultMatchers.status().is(404)).
                andDo(MockMvcResultHandlers.print()).andReturn();
    }

}
