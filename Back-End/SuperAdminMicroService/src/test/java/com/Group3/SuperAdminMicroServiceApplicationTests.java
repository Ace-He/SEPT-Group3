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
class SuperAdminMicroServiceApplicationTests {

    @Inject
    private MockMvc mockMvc;


    @Test
    public void getGpInfo_fail() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/get/gp")).   //request get method
                andExpect(MockMvcResultMatchers.status().is(405)).
                andDo(MockMvcResultHandlers.print()).andReturn();
    }

    @Test
    public void getPatient_fail() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/getPatient")).   //request get method
                andExpect(MockMvcResultMatchers.status().is(404)).
                andDo(MockMvcResultHandlers.print()).andReturn();
    }




}
