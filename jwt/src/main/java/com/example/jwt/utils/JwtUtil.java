package com.example.jwt.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.jwt.constants.Constants;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author: liubq
 * @create: 2020/10/13 09:29
 * @description:
 **/

@Slf4j
public class JwtUtil {

    /**
     * 密钥
     */
    private static final String TOKEN_SECRET = "asset_token";

    /**
     * 发行者
     */
    private static final String ISSUER = "asset";


    /**
     * 签名生成
     *
     * @param username
     * @return
     */
    public static String sign(String username) {
        Date expiresAt = new Date(System.currentTimeMillis() + Constants.LOGIN_EXPIRE * 1000);
        String token = JWT.create()
                .withIssuer(ISSUER)
                // 非敏感信息
                .withClaim("username", username)
                .withExpiresAt(expiresAt)
                // 使用HMAC256加密算法。
                .sign(Algorithm.HMAC256(TOKEN_SECRET));
        log.info("token generated: username={}, expire={}, token={}", username, expiresAt, token);
        return token;
    }


    /**
     * 签名验证 返回 username
     *
     * @param token
     * @return
     */
    public static String verify(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer(ISSUER).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            String username = decodedJWT.getClaims().get("username").asString();
            Date expiresAt = decodedJWT.getExpiresAt();
            log.info("token verify success: username={}, expire={}, token={}", username, expiresAt, token);
            return username;
        } catch (Exception e) {
            log.error("Exception: e={}", e);
            throw new RuntimeException("verify failed, please login again");
        }
    }
}
