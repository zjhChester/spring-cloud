package xyz.zjhwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.zjhwork.entity.Exception;
import xyz.zjhwork.entity.History;
import xyz.zjhwork.service.ExceptionService;
import xyz.zjhwork.service.OtherService;
import xyz.zjhwork.utils.SortUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HisController {
    @Autowired
    private OtherService otherService;
    @Autowired
    private ExceptionService exceptionService;
    //查询历史记录
    @ResponseBody
    @GetMapping("/findHistoryByUsername")
    public List<Exception> findHistoryByUsername(HttpServletRequest request){
        List<Exception> exceptions = otherService.findHistoryByUsername(request.getSession().getAttribute("loginUser").toString());

        for (Exception e:
                exceptions) {
            if(e.getTitle().trim().length()>=15){
                e.getTitle().trim().substring(0,15);
            }
        }
        return exceptions;
    }

}