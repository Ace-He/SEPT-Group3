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
public class DrugControllerTest {

    @Inject
    private MockMvc mockMvc;

    @Test
    public void prescribe_success() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/drug/prescribe/3")).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print()).andReturn();
    }

    @Test
    public void prescribe_fail() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/drug/prescribe")).
                andExpect(MockMvcResultMatchers.status().is(404)).
                andDo(MockMvcResultHandlers.print()).andReturn();
    }


    @Test
    public void removeDrugs_success() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/drug/remove/1")).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print()).andReturn();
    }


    @Test
    public void removeDrugs_fail() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/drug/remove")).
                andExpect(MockMvcResultMatchers.status().is(404)).
                andDo(MockMvcResultHandlers.print()).andReturn();
    }


    @Test
    public void getDrugList_fail() throws Exception{     //need jwt token
        mockMvc.perform(MockMvcRequestBuilders.get("/drug/list")).
                andExpect(MockMvcResultMatchers.status().is(400)).
                andDo(MockMvcResultHandlers.print()).andReturn();
    }

    @Test
    public void getDrug_fail() throws Exception{     //need jwt token
        mockMvc.perform(MockMvcRequestBuilders.get("/drug/getDrugList")).
                andExpect(MockMvcResultMatchers.status().is(400)).
                andDo(MockMvcResultHandlers.print()).andReturn();
    }



}
