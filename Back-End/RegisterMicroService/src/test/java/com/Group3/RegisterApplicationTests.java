package com.Group3;

import com.mysql.cj.x.protobuf.Mysqlx;
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
class RegisterApplicationTests {

    @Inject
    private MockMvc mockMvc;

    @Test
    public void register_fail() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/register/user")).
                andExpect(MockMvcResultMatchers.status().is(404)).
                andDo(MockMvcResultHandlers.print()).andReturn();
    }

    @Test
    public void updateUserInfo_fail() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/get/UserInfo")).
                andExpect(MockMvcResultMatchers.status().is(404)).
                andDo(MockMvcResultHandlers.print()).andReturn();
    }

}
