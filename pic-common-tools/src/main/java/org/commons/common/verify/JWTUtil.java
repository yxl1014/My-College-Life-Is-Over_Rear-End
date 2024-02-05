package org.commons.common.verify;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.internal.com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO 这个太老了 可以看一下最新的
 *
 * @author yxl17
 * @Package : org.commons.common.verify
 * @Create on : 2023/12/24 17:44
 **/

@Component
public class JWTUtil {

    @Value("${token_secret}")
    private String secret;


    @Value("${token_payload}")
    private String payload;

    @Value("${token_exp}")
    private String exp ;


    /**
     * 加密，传入一个对象和有效期
     *
     * @param object 对象
     * @param maxAge 有效时间
     * @param <T>    类型
     * @return 生成的token
     */
    public <T> String sign(T object, long maxAge) {
        try {
            final JWTSigner signer = new JWTSigner(secret);
            final Map<String, Object> claims = new HashMap<>();
            //通过jackson的api将对象转换成json
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(object);
            claims.put(payload, jsonString);
            claims.put(exp, System.currentTimeMillis() + maxAge);
            return signer.sign(claims);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 解密，传入一个加密后的token字符串和解密后的类型
     * @param jwt token
     * @param classT 对象的类型
     * @param <T> 对象的类型
     * @return 对象
     */
    public <T> T unSign(String jwt, Class<T> classT) {
        final JWTVerifier verifier = new JWTVerifier(secret);
        try {
            final Map<String, Object> claims = verifier.verify(jwt);
            if (claims.containsKey(exp) && claims.containsKey(payload)) {
                long expTime = (Long) claims.get(exp);
                long currentTimeMillis = System.currentTimeMillis();
                if (expTime > currentTimeMillis) {
                    String json = (String) claims.get(payload);
                    ObjectMapper objectMapper = new ObjectMapper();
                    return objectMapper.readValue(json, classT);
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

}
