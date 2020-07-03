package cn.temp.tempbeans.dao;

import cn.temp.tempbeans.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where username =${username}")
    List<User> findAll(@Param("username") String username);

}