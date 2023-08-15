package com.elearn.app.elearnapi.utilities;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.elearn.app.elearnapi.modules.User.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;

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

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("role", user.getRole());

        Date expirationDate = new Date(System.currentTimeMillis() + getExpirationTime());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, getSecretKey())
                .compact();
    }

    public static boolean isTokenValid(String JWTToken) {
        if (JWTToken == null)
            return false;

        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(JWTToken);
            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public static Claims getClaimsFromToken(String JWTToken) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(JWTToken)
                .getBody();

        return claims;
    }

    public static String getJWTFromRequest(HttpServletRequest request) {
        final String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }

        return null;
    }
}
