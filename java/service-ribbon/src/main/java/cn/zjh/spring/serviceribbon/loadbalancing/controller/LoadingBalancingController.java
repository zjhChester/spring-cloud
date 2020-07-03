package cn.zjh.spring.serviceribbon.loadbalancing.controller;

import cn.zjh.spring.serviceribbon.loadbalancing.service.LoadingBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoadingBalancingController {
    @Autowired
    private LoadingBalanceService loadingBalanceService;

    @GetMapping("/sendHttp")
    public String sendHttp(String name){
        return loadingBalanceService.hiService(name);
    }
}
