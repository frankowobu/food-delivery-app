package com.usl.delivery_app.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.usl.delivery_app.security.SecurityConstant;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class JwtTokenUtil {

    public String generateToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstant.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SecurityConstant.SECRET_KEY.getBytes()));
    }

    public String getUsernameFromToken(String token) {
        return null;
    }

    public boolean validateToken(String token) {
        return false;
    }
}
