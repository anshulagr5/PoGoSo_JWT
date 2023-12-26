package com.anshul.pogoso_jwt;

import io.jsonwebtoken.*;

import java.util.Date;

public class CustomJWTVerifier {
    private final String secretKey;

    public CustomJWTVerifier(String secretKey) {
        this.secretKey = secretKey;
    }

    public boolean verifyJWT(String jwt) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(jwt)
                    .getBody();

            // Check custom claim
            if (!"KNIT".equals(claims.get("university", String.class))) {
                return false;
            }

            // Check expiration
            Date now = new Date();
            return !claims.getExpiration().before(now);

        } catch (JwtException e) {
            // Invalid signature or other exceptions
            return false;
        }
    }
}

