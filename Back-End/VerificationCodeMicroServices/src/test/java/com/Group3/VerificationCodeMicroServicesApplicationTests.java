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
class VerificationCodeMicroServicesApplicationTests {

    @Inject
    private MockMvc mockMvc;

    @Test
    public void getImage_success() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/verify/getImage/0@qq.com")).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print()).andReturn();
    }

    @Test
    public void getImage_fail() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/verify/getImage")).
                andExpect(MockMvcResultMatchers.status().is(404)).
                andDo(MockMvcResultHandlers.print()).andReturn();
    }

}
