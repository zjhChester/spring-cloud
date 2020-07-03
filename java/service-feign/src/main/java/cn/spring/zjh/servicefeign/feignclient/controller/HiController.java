package cn.spring.zjh.servicefeign.feignclient.controller;

import cn.spring.zjh.servicefeign.feignclient.AuthDTO;
import cn.spring.zjh.servicefeign.feignclient.api.SchedualServiceHi;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class HiController {

    @Autowired
    SchedualServiceHi schedualServiceHi;
    @RequestMapping(value = "/sendHttp",method = RequestMethod.GET)
    public String sendHttp(String name){
        return schedualServiceHi.sayHiFromClientOne(name);
    }


    @PostMapping("/getAuth")
    public String getAuth(String name){
        return schedualServiceHi.auth(name);
    }
    @PostMapping("/testAuth")
    public String testAuth(@RequestHeader String token){
        return schedualServiceHi.testAuth(token);
    }

}
