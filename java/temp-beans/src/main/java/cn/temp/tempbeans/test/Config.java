package cn.temp.tempbeans.test;

import org.springframework.context.annotation.Configuration;
@Configuration
public class Config {
//    @Bean
    public UtilBeans utilBeans(){
        return new UtilBeans();
    }
}
