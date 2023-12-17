package org.example.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.example.model.entity.User;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

/**
 * @Author: eensh
 * @CreateDate: 2023/12/10
 */

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "sheen";
    private static final long EXPIRATION_TIME = 86400000; // 24小时，可以根据需求进行调整

    //生成JWT，接受一个用户名作为参数后返回生成的jwt字符串
    public static String generateToken(String userName) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + EXPIRATION_TIME);

        Key signingKey = new SecretKeySpec(SECRET_KEY.getBytes(), SignatureAlgorithm.HS256.getJcaName());

        JwtBuilder builder = Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(signingKey);

        return builder.compact();
    }

    //解析JWT
    public static Claims parseToken(String token) {
        Key signingKey = new SecretKeySpec(SECRET_KEY.getBytes(), SignatureAlgorithm.HS256.getJcaName());

        return Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}