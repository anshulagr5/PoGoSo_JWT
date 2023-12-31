package com.anshul.pogoso_jwt;

import java.util.Date;
public class JWTTest {

    public static void main(String[] args) {

        // Assumed secret key
        String secretKey = "8fe0ba0281a9a66f2344f6d3cba9332dbf69df32209759b6a06a8dc195b6622c";

        CustomJWTGenerator jwtGenerator = new CustomJWTGenerator(secretKey);

        // Created a JWT with a sample payload
        String generatedJWT = jwtGenerator.generateJWT("SamplePayload");
        // To print the generated JWT
        System.out.println("Generated JWT: " + generatedJWT);

        CustomJWTVerifier jwtVerifier = new CustomJWTVerifier(secretKey);

        boolean isValid = jwtVerifier.verifyJWT(generatedJWT);

        // To print whether the JWT is valid or not
        if (isValid) {
            System.out.println("JWT is valid.");
        } else {
            System.out.println("JWT is not valid.");
        }


        // To refresh the token
        Date expiration = jwtVerifier.getExpirationTime(generatedJWT);
        Date now = new Date();
        if (expiration != null && expiration.before(now)) {

            String refreshedJWT = jwtGenerator.refreshJWT(generatedJWT);
            System.out.println("Refreshed JWT: " + refreshedJWT);

        } else {
            System.out.println("JWT is not expired yet.");
        }

    }
}


