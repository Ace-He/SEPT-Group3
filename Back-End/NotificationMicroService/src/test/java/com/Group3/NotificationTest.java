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
public class NotificationTest {

    @Inject
    private MockMvc mockMvc;


    @Test
    public void getAppointmentNotifs_fail() throws Exception{   //needs jwt token
        mockMvc.perform(MockMvcRequestBuilders.get("/notification/getAppointment")).
                andExpect(MockMvcResultMatchers.status().is(404)).
                andDo(MockMvcResultHandlers.print()).andReturn();
    }

    @Test
    public void getDrugNotifs_fail() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/notification/getDrug")).
                andExpect(MockMvcResultMatchers.status().is(404)).
                andDo(MockMvcResultHandlers.print()).andReturn();
    }

    @Test
    public void deleteNotifications_success() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/notification/delById/1")).
                andExpect(MockMvcResultMatchers.status().is(405)).   //method should be post
                andDo(MockMvcResultHandlers.print()).andReturn();
    }
}
