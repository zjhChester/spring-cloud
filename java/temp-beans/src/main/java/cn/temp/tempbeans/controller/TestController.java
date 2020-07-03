package cn.temp.tempbeans.controller;
import cn.temp.tempbeans.dao.UserMapper;
import cn.temp.tempbeans.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.List;

@RestController
@RequestMapping("/user")
@Api(tags = "测试MyBatis-plus动态CRUD")
public class TestController {
    @Autowired
    private UserMapper userMapper;

    @ApiOperation(value = "获取用户列表", notes = "用户列表")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
//    @ApiImplicitParam(name = "user", value = "用户信息", required = false, dataType = "User")
    public R< List<User>> getUser(User user) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        if(user.getUsername()!=null){
            userQueryWrapper.like("username", user.getUsername());
        }
        if(user.getId()!=null){
            userQueryWrapper.like("id", user.getId());
        }
        if(user.getPassword()!=null){
            userQueryWrapper.like("password", user.getPassword());
        }
        return  R.ok(userMapper.selectList(userQueryWrapper));
    }

    @ApiOperation(value = "新增用户", notes = "添加用户")
    @Transactional
    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public boolean addUser(@RequestBody User user) {
        return  userMapper.insert(user)==1;
    }

    @ApiOperation(value = "删除用户", notes = "删除用户")
    @Transactional
    @RequestMapping(value = "/user",method = RequestMethod.DELETE)
    public boolean deleteUser( User user) {
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        if(user.getUsername()!=null){
            userUpdateWrapper.eq("username", user.getUsername());
        }
        if(user.getId()!=null){
            userUpdateWrapper.eq("id", user.getId());
        }
        if(user.getPassword()!=null){
            userUpdateWrapper.eq("password", user.getPassword());
        }
        return  userMapper.delete(userUpdateWrapper)!=0;
    }

    @ApiOperation(value = "更新用户", notes = "更新用户")
    @Transactional
    @RequestMapping(value = "/user",method = RequestMethod.PUT)
    public boolean updateUser(@RequestBody User user) {
        return  userMapper.updateById(user)==1;
    }
}
