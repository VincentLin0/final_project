package com.mastercs.demo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.mastercs.demo.bean.EnumRole;
import com.mastercs.demo.bean.Role;
import com.mastercs.demo.bean.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Author : lzy
 * @CreateTime : 2021/6/18
 * @Description :
 * https://blog.csdn.net/qq_43548590/article/details/118022733?spm=1001.2101.3001.6661.1&utm_medium=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-1-118022733-blog-105488557.pc_relevant_multi_platform_featuressortv2removedup&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-1-118022733-blog-105488557.pc_relevant_multi_platform_featuressortv2removedup&utm_relevant_index=1
 **/
@Slf4j
public class JwtUtils {
    /**
     * token 过期时间, 单位: 秒. 这个值表示 30 天
     */
    private static final long TOKEN_EXPIRED_TIME = 30 * 24 * 60 * 60;

    public static final String jwtId = "tokenId";

    /**
     * jwt 加密解密密钥(可自行填写)
     */
    private static final String JWT_SECRET = "1234567890";

    /**
     * 创建JWT
     */
    public static String createJWT(Map<String, Object> claims, Long time) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256; //指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
        Date now = new Date(System.currentTimeMillis());

        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();//生成JWT的时间
        //下面就是在为payload添加各种标准声明和私有声明了
        JwtBuilder builder = Jwts.builder() //这里其实就是new一个JwtBuilder，设置jwt的body
                .setClaims(claims)          //如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                .setId(jwtId)                  //设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
                .setIssuedAt(now)           //iat: jwt的签发时间
                .signWith(signatureAlgorithm, secretKey);//设置签名使用的签名算法和签名使用的秘钥
        if (time >= 0) {
            long expMillis = nowMillis + time;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);     //设置过期时间
        }
        return builder.compact();
    }


    /**
     * 验证jwt
     */
    public static Claims verifyJwt(String token) {
        //签名秘钥，和生成的签名的秘钥一模一样
        SecretKey key = generalKey();
        Claims claims;
        try {
            claims = Jwts.parser()  //得到DefaultJwtParser
                    .setSigningKey(key)         //设置签名的秘钥
                    .parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }//设置需要解析的jwt
        return claims;

    }

    /**
     * 由字符串生成加密key
     *
     * @return
     */
    public static SecretKey generalKey() {
        String stringKey = JWT_SECRET;
        byte[] encodedKey = Base64.getEncoder().encode(JWT_SECRET.getBytes());
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * 传入User实体类
     */
    public static String generateToken(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", user.getId());
        map.put("username", user.getUsername());
        map.put("roleType", user.getRoles());
/*        map.put("openId", openId);
        map.put("sub", openId);*/
        return createJWT(map, TOKEN_EXPIRED_TIME);
    }

    /**
     * 判断当前用户是否是管理员
     * @param request
     */
    public static void isAdmin(HttpServletRequest request) {
        String token = request.getHeader("token");
        Claims claims = verifyJwt(token);

        if(claims != null){
            Object roleType = claims.get("roleType");
            Set<Role> roleList = JSON.parseObject(JSON.toJSONString(roleType), new TypeReference<Set<Role>>() {
            });

            for (Role role : roleList) {
                String roles = "a.b.c";
                //普通用户
                if (EnumRole.ROLE_USER.equals(role.getName())) {
                    log.info("当前的角色是：" + role.getName());
                    log.error("普通用户不能访问");
                    //        throw  new Exception("错误");
                    break;
                }
                System.out.println("当前的角色是：" + role.getName());
            }
        }else{
            log.error("没有找到当前用户");
            //        throw  new Exception("错误");
        }
        System.out.println(request);
    }

    public static String getCurrentUsername(String token)
    {
        Claims claims = verifyJwt(token);
        return (String) claims.get("username");
    }


}

