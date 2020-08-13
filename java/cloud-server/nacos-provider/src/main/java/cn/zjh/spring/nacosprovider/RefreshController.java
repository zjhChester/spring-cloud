package cn.zjh.spring.nacosprovider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class RefreshController {
        @Value("${username}")
        private String username;

        @RequestMapping("/username")
        public String get() {
            return username;
        }
}
