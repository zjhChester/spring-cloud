package cn.zjh.spring.timerscheduled;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableDiscoveryClient
public class TimerScheduledApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimerScheduledApplication.class, args);
    }

}
