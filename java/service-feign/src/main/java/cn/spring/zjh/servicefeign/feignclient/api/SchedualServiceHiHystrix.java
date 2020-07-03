package cn.spring.zjh.servicefeign.feignclient.api;

import org.springframework.stereotype.Component;

/**
 * Feign集成hystrix熔断器
 */
@Component
public class SchedualServiceHiHystrix implements SchedualServiceHi {
    @Override
    public String sayHiFromClientOne(String name) {
        return "eurekaClient error!sorry "+name;
    }

    @Override
    public String auth(String name) {
        return "eurekaClient error!sorry "+name;
    }

    @Override
    public String testAuth(String token) {
        return "eurekaClient error!";
    }
}
