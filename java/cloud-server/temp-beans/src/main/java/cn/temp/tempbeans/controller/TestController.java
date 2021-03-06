package cn.temp.tempbeans.controller;
import cn.temp.tempbeans.dao.RolesMapper;
import cn.temp.tempbeans.dao.UserMapper;
import cn.temp.tempbeans.pojo.Exception;
import cn.temp.tempbeans.pojo.Roles;
import cn.temp.tempbeans.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Api(tags = "测试MyBatis-plus动态CRUD")
public class TestController {


    @Autowired
    private UserMapper userMapper;

    @GetMapping("/page")
    public R<List<User>> page(Integer currPage,Integer pageSize){
        Page<User> page = userMapper.selectPage(new Page<User>(currPage, pageSize), new QueryWrapper<User>().like("username", "z"));
        System.out.println("current:"+page.getCurrent());
        System.out.println("orders:"+((Page<User>) page).getOrders());
        System.out.println("size:"+page.getSize());
        System.out.println("pages:"+page.getPages());
        System.out.println("total:"+page.getTotal());
        return R.ok(page.getRecords());

    }



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

    @Autowired
    private RolesMapper rolesMapper;

    @ApiModelProperty(value = "nacos config",notes = "nacos配置文件")
    @GetMapping("/nacos")
    public R<List<Roles>> nacos_config(Roles roles){

        List<Roles> username = rolesMapper.selectList(new QueryWrapper<Roles>().like("username", roles.getUsername()));
        return R.ok(username);
    }
}
