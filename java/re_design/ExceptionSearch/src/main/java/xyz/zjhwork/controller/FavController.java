package xyz.zjhwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.zjhwork.entity.Exception;
import xyz.zjhwork.entity.Favorite;
import xyz.zjhwork.service.ExceptionService;
import xyz.zjhwork.service.OtherService;
import xyz.zjhwork.utils.DateUtils;
import xyz.zjhwork.utils.SortUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 收藏的业务
 */
@Controller
public class FavController {
    @Autowired
    private OtherService otherService;
    @Autowired
    private ExceptionService exceptionService;
    //查询当前文章自己是否收藏
    @ResponseBody
    @GetMapping("/isFavByUsernameAndExceptionId")
    public int isFavByUsernameAndExceptionId(Integer id, HttpServletRequest request){
        if(id != null){
            Favorite favorite = new Favorite();
            favorite.setExceptionId(id);
            favorite.setUserId(request.getSession().getAttribute("loginUser")+"");
            int favByUsernameAndExceptionId = otherService.isFavByUsernameAndExceptionId(favorite);
            if(favByUsernameAndExceptionId!=0){
                return 1;
            }else
            {
                return 0;
            }
        }
        return 0;
    }
    //查询自己的收藏
    @ResponseBody
    @GetMapping("/findFavByUsername")
    public List<Exception> findFavByUsername(HttpServletRequest request){
        List<Exception> exceptions = otherService.findFavByUsername(request.getSession().getAttribute("loginUser").toString());
        return exceptions;
    }
    //取消收藏
    @ResponseBody
    @PostMapping("/deleteFavFromFavByUsernameAndExceptionId")
    public int deleteFavFromFavByUsernameAndExceptionId(Integer id, HttpServletRequest request){
      if(id!=null){
          Favorite favorite = new Favorite();
          favorite.setExceptionId(id);
          favorite.setUserId(request.getSession().getAttribute("loginUser").toString());
          int i = otherService.deleteFavFromFavByUsernameAndExceptionId(favorite);
          if(i != 0){
              return 1;
          }else{
              return 0;
          }
      }
      return 0;
    }
    //添加收藏
    @ResponseBody
    @PostMapping("/addFavByUsernameAndExceptionId")
    public int addFavByUsernameAndExceptionId(Integer id, HttpServletRequest request){
        if(id!=null){
            Favorite favorite = new Favorite();
            favorite.setExceptionId(id);
            favorite.setUserId(request.getSession().getAttribute("loginUser").toString());
            //避免事务延迟
            int favByUsernameAndExceptionId = otherService.isFavByUsernameAndExceptionId(favorite);
            if(favByUsernameAndExceptionId==0){
                favorite.setRemark("");
                favorite.setTime(DateUtils.getFormat(new Date()));
                int i = otherService.addFavByUsernameAndExceptionId(favorite);
                if(i != 0){
                    return 1;
                }else{
                    return 0;
                }
            }else{
                return -1;
            }
        }
        return 0;
    }
}
