package cn.zjh.spring.timerscheduled.timemanager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TimerScheduleder {


    private final Logger log = LoggerFactory.getLogger(getClass());
    @Value("${plan.count}")
    private Integer count;

    @Value("${plan.status}")
    private Boolean status;

    @Scheduled(fixedRate = 1000*60*60*24,initialDelay = 5000)
    public void startTask(){
        Date date=new Date();
        SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
        String week = dateFm.format(date);
        if(("星期六".equals(week)||"星期天".equals(week)||"星期日".equals(week))||count==0||!status){
            log.info("今天是"+week+"，不做日志记录"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"其他原因可能是：1、执行次数："+count+"\t2、执行状态："+status);
        }else{
            for (int i = 0; i < count; i++) {
//                System.out.println("打印一次日志记录"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                log.info("打印一次日志记录"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),TimerScheduleder.class);
                new Log4WorkUtil().run();
            }
        }
    }
}
