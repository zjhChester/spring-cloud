package cn.zjh.spring.serverauth.auth.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Slf4j
@Component
public class JwtUtil {
    /**
     *  过期时长
     */
//    private static final long EXPIRE_TIME=30 *1000;

    /**
     * 私钥，使用它生成token，最好进行下加密
     */
    private static  String TOKEN_SECRET="poadh981gndo1xdobvIT!*(GEOB!({sH)SH!SH!GDadad";

    private static final String EXP = "exp";
    private static final String PAYLOAD = "payload";


    public static String sign(String useName,long exprieTime){
        try{
            Date date=new Date(System.currentTimeMillis()+exprieTime);
            //私钥及加密算法
            Algorithm algorithm=Algorithm.HMAC256(TOKEN_SECRET);
            //设置头部信息
            Map<String,Object> header=new HashMap<>();
            header.put("typ","JWT");
            header.put("alg","HS256");
            //附带username和userid信息,存储到token中，生成签名
            return JWT.create()
                    .withHeader(header)
                    //存储自己想要留存给客户端浏览器的内容
                    .withClaim("username",useName)
                    .withExpiresAt(date)
                    .sign(algorithm);


        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //解密，传入一个加密后的token字符串和解密后的类型
    public static boolean verify(String token){

        try {
            Algorithm algorithm=Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier =JWT.require(algorithm).build();
            //此方法若token验证失败会抛错的，所以直接return true没问题
            verifier.verify(token);
            return true;
        }catch (Exception e){
            log.info("token验证失效");
            return false;
        }

    }


    /**
     * 获取token中信息 userName
     * @param token
     * @return
     */
    public static String getUsername(String token) {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
    }



}
