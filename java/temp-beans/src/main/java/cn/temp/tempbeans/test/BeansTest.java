package cn.temp.tempbeans.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
@Controller
public class BeansTest {
    @Autowired
    private UtilBeans utilBeans;

    public void test(){
        System.out.println(utilBeans);
    }

}
