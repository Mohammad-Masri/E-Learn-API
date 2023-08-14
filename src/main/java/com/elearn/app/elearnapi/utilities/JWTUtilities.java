package com.elearn.app.elearnapi.utilities;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.elearn.app.elearnapi.modules.User.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtilities {

    private static String SECRET_KEY;
    private static long EXPIRATION_TIME;

    @Value("${jwt.secret-key}")
    public void setSecretKey(String secretKey) {
        SECRET_KEY = secretKey;
    }

    @Value("${jwt.expiration-time}")
    public void setExpirationTime(long expirationTime) {
        EXPIRATION_TIME = expirationTime;
    }

    public static String getSecretKey() {
        return SECRET_KEY;
    }

    public static long getExpirationTime() {
        return EXPIRATION_TIME;
    }

    public static String generateJwtToken(User user) {
        Date expirationDate = new Date(System.currentTimeMillis() + getExpirationTime());

        return Jwts.builder()
                .setSubject(user.getId())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, getSecretKey())
                .compact();
    }
}
