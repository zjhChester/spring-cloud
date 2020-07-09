package cn.temp.tempbeans.utils;

import cn.temp.tempbeans.api.dto.ValidDTO;
import cn.temp.tempbeans.pojo.Msg;
import cn.temp.tempbeans.pojo.User;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/***
 * author:zjhChester
 * date:2020-07-01
 * environment: jdk1.8
 * 适用场景：适用于数据持久层pojo对象转换数据传输层DTO
 */
public class TypeConverter {


    public <T, S> T convert(S s, Class<T> clz) throws IllegalAccessException, InstantiationException {
        //获取输出对象的属性列表
        Field[] f1 = clz.getDeclaredFields();

        Map<String,Object> map = new HashMap<>();
        for (Field f : f1) {
            //将输出对象的属性名装入map
            map.put(f.getName(),null);
        }
        //获取输入对象的属性列表
        Field[] f2 = s.getClass().getDeclaredFields();
        for (Field f : f2) {
            //便利执行  匹配输入对象的属性名
          if(map.containsKey(f.getName())){
              //设置私有属性可见性
              f.setAccessible(true);
              try {
                  //属性一致则将输入对象的属性值存放到的map的以对应属性名为key的值中   覆盖原来的null
                  map.put(f.getName(),f.get(s));
              } catch (IllegalAccessException e) {
                  e.printStackTrace();
              }
          }
        }
        map.entrySet().forEach(item-> System.out.println(item.getKey()+":\t"+item.getValue()));
        //反射获取输出对象实例
        T t = clz.newInstance();
        for (Map.Entry<String,Object> e:map.entrySet() ) {
            //过滤map中的空值对象，即无匹配属性
            if(e.getValue()!=null){
                    for (Field f : f1) {
                        f.setAccessible(true);
                        //将符合 map的key==输出对象的属性名     并且map的value.getClass() == 输出对象的属性类型
                        System.out.println(map.get(f.getName()));
                        if(f.getName().equals(e.getKey())&&map.get(f.getName())!=null && f.getType().equals(map.get(f.getName()).getClass())){
                            //存放到输出对象的属性中
                            f.set(t,e.getValue());
                        }
                    }
            }
        }
        //返回输出对象
        return t;
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        User user = new User();
        user.setId(1);
        user.setUsername("zjh");
        String arr[] = new String[5];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i+"";
        }
        user.setArr(arr);

        Msg msg = new Msg();
        msg.setContent("1231231j23po1npodn1pon[131o23");
        ArrayList<Msg> msgs = new ArrayList<>();
        msgs.add(msg);
        user.setMsgs(msgs);
        ValidDTO convert = new TypeConverter().convert(user, ValidDTO.class);
        System.out.println(user);
        System.out.println(convert);
    }
}
