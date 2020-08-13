package cn.temp.tempbeans;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("cn.temp.tempbeans.dao")
@EnableSwagger2
public class TempBeansApplication extends Exception {

    public static void main(String[] args) {
        SpringApplication.run(TempBeansApplication.class, args);
    }

}
