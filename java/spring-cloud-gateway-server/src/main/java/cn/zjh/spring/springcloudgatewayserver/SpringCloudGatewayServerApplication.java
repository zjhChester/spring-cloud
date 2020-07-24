package cn.zjh.spring.springcloudgatewayserver;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudGatewayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudGatewayServerApplication.class, args);
    }
    @RestController
    public class ProviderController {
        @GetMapping("/hi")
        @SentinelResource(value="gateway-sentinel")
        public String hi(@RequestParam(value = "name",defaultValue = "forezp",required = false)String name){
            return "hi "+name;
        }
        // 限流与阻塞处理
        public void exceptionHandler(String str, BlockException ex) {
            System.out.println("blockHandler：" + str + ex);
        }

    }

}
