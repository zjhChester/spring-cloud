package cn.spring.zjh.servicefeign.feignclient.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@FeignClient(value = "eureka-client",fallback = SchedualServiceHiHystrix.class)
public interface SchedualServiceHi {
    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    String sayHiFromClientOne(@RequestParam(value = "name", required = false)  String name);

    @PostMapping("/getAuth")
     String auth(String name);





    @PostMapping("/testAuth")
     String testAuth(@RequestHeader("token") String token);
}

