package com.Group3;

import com.Group3.pojo.GP;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class A1projectApplicationTests {

    @Autowired
    private GP gp;

    @Test
    void contextLoads() {
        System.out.println(gp);
    }

}
