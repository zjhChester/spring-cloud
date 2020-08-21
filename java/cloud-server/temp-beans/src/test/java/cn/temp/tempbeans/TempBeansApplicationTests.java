package cn.temp.tempbeans;

import cn.temp.tempbeans.dao.ExceptionMapper;
import cn.temp.tempbeans.dao.MsgMapper;
import cn.temp.tempbeans.dao.UserMapper;
import cn.temp.tempbeans.pojo.Exception;
import cn.temp.tempbeans.pojo.Msg;
import cn.temp.tempbeans.pojo.ObjectKey;
import cn.temp.tempbeans.pojo.User;
import cn.temp.tempbeans.test.BeansTest;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@RunWith(SpringRunner.class)
@SpringBootTest

class TempBeansApplicationTests {
    @Autowired
    private BeansTest beansTest;
    @Test
    void contextLoads() {
        beansTest.test();
    }

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MsgMapper msgMapper;
    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
//        Assert.assertEquals(5, userList.size());
//        List<User> userList = userMapper.findAll("(delete from user where username = tempUsername)");
        userList.forEach(System.out::println);
//        userList.forEach(item-> System.out.println(item));
    }

    @Test
    public void testInsert() {
        System.out.println(("----- insert method test ------"));
        User user = new User();
        user.setId(3);
        user.setUsername("tempUsername");
        user.setPassword("1231241412e1231");
        userMapper.insert(user);
        testSelect();
    }

    @Test
    public void testDelete(){
        System.out.println(("----- delete method test ------"));
        userMapper.deleteById(3L);
        testSelect();
    }
    @Test
    @Transactional
    public void testUpdate(){
        System.out.println(("----- Update method test ------"));
        User user = new User();
        user.setId(3);
        user.setPassword("123");
        userMapper.updateById(user);
        int a = 1/0;
        testSelect();
    }

    @Test
    public void selectWrapperTest(){
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        QueryWrapper<User> eq = queryWrapper.like("username", "");
        List<User> users = userMapper.selectList(eq);
        users.forEach( System.out::println);
    }

    /**
     * 返回集为列表
     */
    @Test
    public void selectBatchIdsTest(){
        List<Long> longs = new LinkedList<>();
        longs.add(1L);
        longs.add(2L);
        userMapper.selectBatchIds(longs).forEach(System.out::println);
    }

    /**
     * put的值会覆盖掉上一条记录
     */
    @Test
    public void selectByMapTest(){
        Map<String,Object> map = new HashMap<>();
        map.put("username","zjh");
        map.put("id","1");
        userMapper.selectByMap(map).forEach(System.out::println);

    }

    @Test
    public void selectByPageTest(){
        QueryWrapper<Msg> queryWrapper = new QueryWrapper<>();
        Page<Msg> msgPage = msgMapper.selectPage(new Page<>(2, 2), queryWrapper.orderByDesc("id"));
        msgPage.getRecords().forEach(item->System.out.println(item.getContent()));
    }

    @Test
    public void testData(){
        int a=1;
        int b=0;
       b=a==1?1:2;
        System.out.println(b);
    }

    @Value("${zjh.name}")
     String name;
    @Test
    public void readTextFromYml(){
        System.out.println(this.name);
    }


    @Test
    public void testUUID(){
        System.out.println(new Date());

    }

    @Autowired
    private ExceptionMapper exceptionMapper;
    @Test
    public void getPage(){
        IPage<Exception> page = exceptionMapper.selectPage( new Page<>(0,5),new QueryWrapper<Exception>().likeLeft("title","java"));
        System.out.println(page.getRecords().size());
    }
    @Test
    public void testHashMapSource(){
        HashMap<ObjectKey,Integer> hashMap = new HashMap<>();
        ObjectKey o1 = ObjectKey.builder().about("10").space("10").count(10).build();
        ObjectKey o2 = ObjectKey.builder().about("10").space("10").count(10).build();
        ObjectKey o3 = ObjectKey.builder().about("11").space("11").count(11).build();
        ObjectKey o4 = ObjectKey.builder().about("12").space("12").count(12).build();
        hashMap.put(o1,1);
        hashMap.put(o2,2);
        hashMap.put(o3,3);
        hashMap.put(o4,4);
        System.out.println(hashMap);
    }


}
