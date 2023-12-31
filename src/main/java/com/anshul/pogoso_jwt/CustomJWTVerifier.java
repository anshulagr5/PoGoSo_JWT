package com.anshul.pogoso_jwt;

import io.jsonwebtoken.*;
import java.util.Date;

public class CustomJWTVerifier {
    private final String secretKey;

    //Constructor
    public CustomJWTVerifier(String secretKey) {
        if (secretKey == null || secretKey.isEmpty()) {
            throw new IllegalArgumentException("Secret key cannot be Null or Empty");
        }
        this.secretKey = secretKey;
    }

    //Function to verify JWT Integrity
    public boolean verifyJWT(String jwt) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(jwt)
                    .getBody();

            // To check the custom claim
            if (!"KNIT".equals(claims.get("University", String.class))) {
                return false;
            }

            // To check the expiration
            Date now = new Date();
            return !claims.getExpiration().before(now);

        } catch (JwtException e) {
            // Return false in case of Invalid signature
            return false;
        }
    }

    public Date getExpirationTime(String jwt) {
        try
        {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(jwt);

            Claims body = claimsJws.getBody();

            return body.getExpiration();
        } catch (JwtException | IllegalArgumentException e) {
            // Return in case of Invalid token
            return null;
        }
    }
}

