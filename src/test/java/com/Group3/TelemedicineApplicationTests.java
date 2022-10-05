package com.Group3;


import com.Group3.common.api.ApiResult;
import com.Group3.common.util.DateUtil;
import com.Group3.common.util.RedisUtils;
import com.Group3.controller.AuthController;
import com.Group3.controller.GPController;
import com.Group3.controller.PatientController;
import com.Group3.controller.VerificationCodeController;
import com.Group3.param.GPQueryParam;
import com.Group3.param.PatientParam;
import com.Group3.param.UserRegisterParam;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;



import javax.annotation.Resource;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

@SpringBootTest
@AutoConfigureMockMvc
class A1projectApplicationTests {


    @Resource
    AuthController authController;
    @Resource
    PatientController patientController;
    @Resource
    GPController GPController;
    @Resource
    VerificationCodeController verificationCodeController;
    @Autowired
    private RedisUtils redisUtils;
    @Resource
    private MockMvc mockMvc;

    @Test
    void registerSuccessTest() throws IOException {
        String email = "9@qq.com";
        verificationCodeController.image(email);
        String codeKey = "code_" + email;
        String code = (String) redisUtils.get(codeKey);
        System.out.println(code);
        UserRegisterParam user = new UserRegisterParam(code,"Ace2",email,"Male",69,"123456",1);
        ApiResult register = authController.register(user);
        System.out.println(register);
    }

    /**
     * 失败的情况，你写个已经存在的邮箱就行了
     * @throws IOException
     */
    @Test
    void registerFailTest() throws IOException {
        String email = "98@qq.com";
        verificationCodeController.image(email);
        String codeKey = "code_" + email;
        String code = (String) redisUtils.get(codeKey);
        System.out.println(code);
        UserRegisterParam param = new UserRegisterParam(code,"Ace1",email,"Male",69,"123456",1);
        ApiResult register = authController.register(param);

        System.out.println(register);
    }


    @Test
    void editStatusSuccessTest() throws IOException{   // The patient ID is existed
        Long pid = 11L;
        String status = "pain";
        PatientParam patient = new PatientParam(pid,status);

        ApiResult editStatus = patientController.editStatus(patient);
        System.out.println(editStatus);
    }

    @Test
    void editStatusFailTest() throws IOException{   // The patient ID is not existed
        Long pid = 19L;
        String status = "pain";
        PatientParam patient = new PatientParam(pid,status);

        ApiResult editStatus = patientController.editStatus(patient);
        System.out.println(editStatus);
    }

    @Test
    void editHealthInfoSuccessTest() throws IOException{   // The patient ID is existed

        PatientParam patient = new PatientParam(11L,185L,85L,"Mango","None");
        ApiResult editHealthInfo = patientController.editInfo(1,patient);

        System.out.println(editHealthInfo);
    }

    @Test
    void editHealthInfoFailTest() throws IOException{   // The patient ID is not existed

        PatientParam patient = new PatientParam(19L,185L,85L,"Mango","None");
        ApiResult editHealthInfo = patientController.editInfo(1,patient);

        System.out.println(editHealthInfo);
    }


    @Test
    void getGPByIdSuccessTest() throws IOException{   // The GP ID is existed
        Long gid = 7L;
        GPQueryParam GP = new GPQueryParam(gid);

        ApiResult getGP = GPController.listGp(GP);
        System.out.println(getGP);
    }

    @Test
    void getGPByIdFailTest() throws IOException{   // The GP ID is not existed
        Long gid = 19L;
        GPQueryParam GP = new GPQueryParam(gid);

        ApiResult getGP = GPController.listGp(GP);
        System.out.println(getGP);
    }


    @Test
    public void test() throws ParseException {
        System.out.println(new Date());
        System.out.println(DateUtil.addTime(new Date(), 30));
        System.out.println(DateUtil.subtractTime(new Date(), 30));
    }


}
