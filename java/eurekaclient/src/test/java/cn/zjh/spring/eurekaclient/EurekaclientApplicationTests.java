package cn.zjh.spring.eurekaclient;

import cn.zjh.spring.eurekaclient.auth.utils.JwtUtil;
import com.auth0.jwt.JWT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EurekaclientApplicationTests {

    @Test
    void contextLoads() {
//        System.out.println(JwtUtil.sign("zjh","1"));
    }

    @Test
    void ver(){

        System.out.println(JwtUtil.verify("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6InpqaCIsImV4cCI6MTU5Mzc2MDkxNywidXNlcklkIjoiMSJ9.ZlSZtDiTSg_PYrflD2Z1eFqPMmkD4P6emxNBH0xMbNQ"));
    }

}
