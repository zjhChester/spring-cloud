package cn.zjh.spring.serverauth;

import cn.zjh.spring.serverauth.auth.config.TestAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class ServerAutoApplicationTests {

//    @Test
//    void test() {
//
//        AutoDTO autoDTO = new AutoDTO();
//        autoDTO.setUsername("1");
//        autoDTO.setPassword("2");
//        AutoDTO autoDTO1 = new AutoDTO().setUsername("1").setPassword("2");
//        AutoDTO autoDTO2 = AutoDTO.builder().username("zjh").password("123").build();
//        System.out.println(autoDTO);
//    }


//  private final static Logger logger = LoggerFactory.getLogger("accountSignServiceLog");

    @Test
    @TestAnnotation
     void test(){
//        System.out.println(1111);
        log.info("1");
    }
}
