package cn.temp.tempbeans.controller;

import cn.temp.tempbeans.dao.ExceptionMapper;
import cn.temp.tempbeans.pojo.Exception;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/exception")
@Api(tags = "测试MyBatis-plus下的exception")
public class ExceptionSearchSystemTestController {
    @Autowired
    private ExceptionMapper exceptionMapper;
    @GetMapping("/page")
    public R<List<Exception>> page(Integer pageIndex,Integer pageSize){
//        , new QueryWrapper<Exception>().like("exception.title", "java").orderByDesc("createTime")
        IPage<Exception> page = exceptionMapper.selectPage( new Page<>(pageIndex,pageSize),new QueryWrapper<Exception>().likeLeft("title","java"));
        System.out.println("current:"+page.getCurrent());
        System.out.println("orders:"+((Page<Exception>) page).getOrders());
        System.out.println("size:"+page.getSize());
        System.out.println("pages:"+page.getPages());
        System.out.println("total:"+page.getTotal());
        return  R.ok(page.getRecords());
    }
}
