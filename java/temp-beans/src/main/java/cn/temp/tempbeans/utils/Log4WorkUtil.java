package cn.temp.tempbeans.utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Log4WorkUtil {
    static Map<Integer,Integer>  map = new HashMap<>();

    public static void main(String[] args) {
        String fileName = "day";
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
        String[] arrTimes = dateFormat.format(new Date()).split("-");
        int thisYear = Integer.parseInt(arrTimes[0]);
        int thisMonth = Integer.parseInt(arrTimes[1]);
        int thisDay = Integer.parseInt(arrTimes[2]);
        //每月数量
        map.put(1,31);
        map.put(2, ((thisYear%4==0&&thisYear%100!=0)||thisYear%400==0)?29:28);
        map.put(3,31);
        map.put(4,30);
        map.put(5,31);
        map.put(6,30);
        map.put(7,31);
        map.put(8,31);
        map.put(9,30);
        map.put(10,31);
        map.put(11,30);
        map.put(12,31);
        //2020-6-16 day01
        int days=0;
        if(thisYear-2020==0){
            days = 15+thisDay;
            days+=getDaysFromMonth(thisMonth,6);
        }else{
            //年份算的天数
            days += getDaysFromYear(thisYear);
            //今年剩余天数
            days += 15+31+getDaysFromMonth(12,6);
            //明年算的天数
            days+=thisDay+getDaysFromMonth(thisMonth,0);
        }
        fileName += days+"_";
        fileName += thisMonth/10==0?"0"+thisMonth+thisDay:thisMonth+thisDay;
        fileName+=".txt";
        File file = new File("E:\\workplace\\my_notes\\"+fileName);
        OutputStreamWriter os=null;
        if(!file.exists()){
            file.mkdirs();
        }
        try {
            os = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
            os.write("# "+thisYear+fileName+"工作日志   \r\n\r\n## 任务\r\n\r\n## 详细");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(os!=null)
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static  int getDaysFromMonth(int endMonth,int startMonth){
        int res=0;
        for (int i=endMonth-1;i>startMonth;i--){
            res+=map.get(i);
        }
        return res;
    }

    static int getDaysFromYear(int year){
    int res = 0;
        for (int i=year-1;i>2020;i--){
            if((i%4==0&&i%100!=0)||i%400==0)
            res += 366;
            else
            res +=365;
        }
        return res;
    }

}
