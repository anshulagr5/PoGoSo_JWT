package com.anshul.pogoso_jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class CustomJWTGenerator {
    private final String secretKey;

    public CustomJWTGenerator(String secretKey) {
        this.secretKey = secretKey;
    }

    public String generateJWT(String payload) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + 3600 * 1000); // 1 hour expiration

        JwtBuilder jwtBuilder = Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(expiration)
                .claim("university", "KNIT")
                .setSubject(payload)
                .signWith(SignatureAlgorithm.HS256, secretKey);

        return jwtBuilder.compact();
    }
}

