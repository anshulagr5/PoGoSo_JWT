package com.anshul.pogoso_jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

public class CustomJWTGenerator {
    private final String secretKey;

    public CustomJWTGenerator(String secretKey) {
        if (secretKey == null || secretKey.isEmpty()) {
            throw new IllegalArgumentException("Secret key cannot be Null or Empty");
        }
        this.secretKey = secretKey;
    }

    public String generateJWT(String payload) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + 3600 * 1000); // 1 hour expiration

        JwtBuilder jwtBuilder = Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(expiration)
                .claim("University", "KNIT")
                .setSubject(payload)
                .signWith(SignatureAlgorithm.HS256, secretKey);

        return jwtBuilder.compact();
    }


    //Function to refresh the JWT when expired
    public String refreshJWT(String oldJwt) {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(oldJwt);

            Claims body = claimsJws.getBody();

            // To check if the token has expired
            if (!isTokenExpired(body.getExpiration())) {
                throw new IllegalArgumentException("Token not expired yet");
            }

            // To extend expiration time for another 1 hour
            Date newExpirationTime = new Date(System.currentTimeMillis() + 3600000);

            // Build a new token with the same payload and updated expiration time
            return Jwts.builder()
                    .setSubject(body.getSubject())
                    .claim("University", body.get("University"))
                    .setExpiration(newExpirationTime)
                    .signWith(SignatureAlgorithm.HS256, secretKey)
                    .compact();

        } catch (JwtException | IllegalArgumentException e) {
            // Exception in case Invalid token
            throw new IllegalArgumentException("Invalid token for refresh");
        }
    }

    //Function to check whether the token is expired or not
    private boolean isTokenExpired(Date expiration) {
        Date now = new Date();
        return expiration.before(now);
    }
}