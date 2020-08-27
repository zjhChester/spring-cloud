package cn.zjh.spring.serverauth.auth.controller;

import cn.zjh.spring.serverauth.auth.dto.AutoDTO;
import cn.zjh.spring.serverauth.auth.dto.TokenDTO;
import cn.zjh.spring.serverauth.auth.utils.JwtUtil;
import com.baomidou.mybatisplus.extension.api.R;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
@CrossOrigin
@Slf4j
public class AuthController {
    private final static Logger logger = LoggerFactory.getLogger("accountSignServiceLog");
    @PostMapping("/login")
    public ResponseEntity<R<TokenDTO>> login(@Valid @RequestBody AutoDTO autoDTO, HttpServletRequest request) {
        //登录成功
        String accessToken,refreshToken;
        TokenDTO tokenDTO=null;
        try {
            //accessToken为2分钟
            accessToken = JwtUtil.sign(autoDTO.getUsername() + ""+autoDTO.getPassword(),1000*5);
            //refreshToken过期时间为七天
            refreshToken = JwtUtil.sign(autoDTO.getUsername() + ""+autoDTO.getPassword(),1000*60*60*24*7);
            //builder模式重构
            tokenDTO=TokenDTO.builder().accessToken(accessToken).refreshToken(refreshToken).build();
        } catch (Exception e) {
           log.error("api in /login has exception about"+e);
        }
        //异步存储登陆日志
        logger.info("\r\n登录日志 \t\t-->"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"\t\t-->ip:"+request.getRemoteHost()+"\t\t-->"+autoDTO.getUsername()+"已登入");
        return ResponseEntity.status(HttpStatus.OK).body(R.ok(tokenDTO));
    }
    @GetMapping("/status")
    public R<Boolean> status( @RequestHeader String token){
        boolean verify = JwtUtil.verify(token);
        return verify?R.ok(true):R.failed("验证失败");
    }

    @GetMapping("/auth")
    public R<TokenDTO> refreshToken(     @RequestHeader String accessToken,@RequestHeader String refreshToken){
        if(JwtUtil.verify(refreshToken)){
        String accessTokenUsername;
       try {

           accessTokenUsername = JwtUtil.getUsername(accessToken);
       }catch (Exception ex){
           //accessToken无效
           return R.failed("认证失败，token格式错误");
       }
            //对比accessToken 和 refreshToken的用户信息
            if(accessToken.equals(JwtUtil.getUsername(refreshToken))){
            accessToken = JwtUtil.sign(accessTokenUsername, 1000 * 5);
        TokenDTO tokenDTO = new TokenDTO(accessToken,refreshToken);
        return R.ok(tokenDTO);
            }else{
                return R.failed("非法参数！（accessInfo != refreshInfo）");
            }
        }else{
            //refreshToken 过期
            return R.failed("认证失败，请重新登录");
        }
    }

    @GetMapping("/getCookie")
    public void getCookie(HttpServletRequest request){
        Cookie[] cookies =  request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                    System.out.println(cookie.getValue());
            }
        }

    }

}
