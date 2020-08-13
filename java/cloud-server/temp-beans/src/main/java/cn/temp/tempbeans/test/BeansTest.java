package cn.temp.tempbeans.test;

import cn.temp.tempbeans.api.dto.ValidDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.*;

@Controller
public class BeansTest {
    @Autowired
    private UtilBeans utilBeans;

    public void test(){
        System.out.println(utilBeans);
    }


    public static void main(String[] args){
        Thread thread = new Thread();

        //实例化arrayList
        List<Integer> arrayList = new ArrayList<Integer>();
        //实例化linkList
        List<Integer> linkList = new LinkedList<Integer>();

        String str1 = "zjh";
        str1+="Chester";
        System.out.println(str1);

        //插入10万条数据
        long add1 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            arrayList.add(i);

        }
        long add2 = System.currentTimeMillis();
        System.out.println(add2-add1);
        for (int i = 0; i < 1000000; i++) {
            linkList.add(i);
        }
        long add3 = System.currentTimeMillis();
        System.out.println(add3-add2);
        int array = 0;
        //用for循环arrayList
        long arrayForStartTime = System.currentTimeMillis();
        for (int i = 0; i < arrayList.size(); i++) {
            array = arrayList.get(i);
        }
        long arrayForEndTime = System.currentTimeMillis();
        System.out.println("用for循环arrayList 10万次花费时间：" + (arrayForEndTime - arrayForStartTime) + "毫秒");

        //用foreach循环arrayList
        long arrayForeachStartTime = System.currentTimeMillis();
        for(Integer in : arrayList){
            array = in;
        }
        long arrayForeachEndTime = System.currentTimeMillis();
        System.out.println("用foreach循环arrayList 10万次花费时间：" + (arrayForeachEndTime - arrayForeachStartTime ) + "毫秒");

        //用for循环linkList
        long linkForStartTime = System.currentTimeMillis();
        int link = 0;
        for (int i = 0; i < linkList.size(); i++) {
            link = linkList.get(i);
        }
        long linkForEndTime = System.currentTimeMillis();
        System.out.println("用for循环linkList 10万次花费时间：" + (linkForEndTime - linkForStartTime) + "毫秒");

        //用froeach循环linkList
        long linkForeachStartTime = System.currentTimeMillis();
        for(Integer in : linkList){
            link = in;
        }
        long linkForeachEndTime = System.currentTimeMillis();
        System.out.println("用foreach循环linkList 10万次花费时间：" + (linkForeachEndTime - linkForeachStartTime ) + "毫秒");
    }







    private void testForEach(){
//        List<ValidDTO> list = new LinkedList<>();
        List<ValidDTO> list = new ArrayList<>();
        int count =0;
        for (int i = 0; i < 10000000; i++) {
            count++;
            ValidDTO validDTO = new ValidDTO();
            validDTO.setAge(1);
            validDTO.setArr(null);
            validDTO.setEmail("1");
            validDTO.setId(i);
            validDTO.setUsername("111");
            list.add(validDTO);
        }
        long start = System.currentTimeMillis();

//        for (ValidDTO validDTO : list) {
//            count++;
//        }

        for (int i = 0; i < list.size(); i++) {
            count++;
        }

        long end = System.currentTimeMillis();
        System.out.println(end-start);
        System.out.println("执行次数："+count);
    }


    private void testFor(){

        long start = System.nanoTime();
        for (int i = 0; i <100000000 ; i++) {
            for (int j = 0; j <100; j++) {
                int x=1;
                x++;
                int y= x;
            }
        }
        long end = System.nanoTime();
        System.out.println("第二次执行"+(end-start));


    }


    private void testString(){
        String str = "1";
        StringBuilder sb = new StringBuilder("2");
        StringBuffer sf = new StringBuffer("3");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
//            sb.append(new Random().nextInt());
            str +=new Random().nextInt();
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);
//        System.out.println(sb);



    }

}
