package com.xxxx.server.config.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: yeb
 * @description:
 * @author: 作者
 * @create: 2021-09-13 09:04
 */
@Component
public class JwtTokenUtil {
    //token对应用户名
    private  static  final String CLATM_KEY_USERNAME="sub";
    //token生成时间
    private  static  final String CLATM_KEY_CREATED="created";

    @Value("${jwt.secret}")
    private String secret;
    @Value(("${jwt.expiration}"))
    private Long expiration;
    //1
    // 根据用户信息生成token

    public  String generateToken(UserDetails userDetails){
        Map<String,Object> claims=new HashMap<>();
        claims.put(CLATM_KEY_USERNAME,userDetails.getUsername());
        claims.put(CLATM_KEY_CREATED,new Date());
        return generateToken(claims);
    }
    // 根据荷载claimss生成jwt token

    private String generateToken(Map<String,Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512,secret)   //Base64-encoded key bytes may only be specified for HMAC signatures. 不是ES512
                .compact();
    }
    /*
      生成失效时间
     */

    private  Date  generateExpirationDate(){
        return  new Date(System.currentTimeMillis()+expiration*1000);
    }
    //2
    //token中获取登录用户名

   public String getUserNameFromToken(String token){
        String username;
       try {
           Claims claims=getClaimsFromToken(token);
           username=claims.getSubject();
       } catch (Exception e) {
           username=null;
       }
       return username;
   }
   //从token中获取荷载claims
    private Claims getClaimsFromToken(String token){
        Claims claims=null;
        try {
            claims=Jwts.parser()
                  .setSigningKey(secret)
                  .parseClaimsJws(token)
                  .getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
         return  claims;
    }
    //3
    //token是否失效

    public  boolean validateToken(String token,UserDetails userDetails){
        String username=getUserNameFromToken(token);
        return username.equals(userDetails.getUsername())&&!isTokenExpired(token);
    }
    private boolean isTokenExpired(String token){
        Date expireDate=getExpireDateFromToken(token);
        return expireDate.before(new Date());
    }
    //获取失效时间

    private Date getExpireDateFromToken(String token) {
     Claims claims=getClaimsFromToken(token);
     Date expiration=claims.getExpiration();
     return expiration;
    }

    //4   token是否被刷新 后刷新token

    public boolean canRefresh(String token)
    {
        return !isTokenExpired(token);
    }
    public  String refreshToken(String token){
       Claims claims=getClaimsFromToken(token);
       claims.put(CLATM_KEY_CREATED,new Date());
       return generateToken(claims);
    }

}
