package cn.zjh.spring.eurekaclient.controller;

import cn.zjh.spring.eurekaclient.AuthDTO;
import cn.zjh.spring.eurekaclient.auth.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class CommonController {
    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(
            @PathVariable String applicationName) {
        return this.discoveryClient.getInstances(applicationName);
    }

    @Value("${server.port}")
    String port;
    @RequestMapping("/hi")
    public String home(@RequestParam String name) {
        return "hi "+name+",i am from port:" +port;
    }


    @PostMapping("/getAuth")
    public String auth(String name){
        return JwtUtil.sign(name)+"----hi,i am from port:" +port;
    }
    @PostMapping("/testAuth")
    public String testAuth(@RequestHeader("token") String token){
        boolean verify = JwtUtil.verify(token);
        return  verify?JwtUtil.sign(JwtUtil.getUsername(token))+"---hi,i am from port:" +port:"false"+"---hi,i am from port:" +port;
    }
}
