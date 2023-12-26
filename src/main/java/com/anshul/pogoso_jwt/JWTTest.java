package com.anshul.pogoso_jwt;

public class JWTTest {
    public static void main(String[] args) {
        // Replace "yourSecretKey" with an actual secret key
        String secretKey = "8fe0ba0281a9a66f2344f6d3cba9332dbf69df32209759b6a06a8dc195b6622c";

        // Part 1: CustomJWTGenerator
        CustomJWTGenerator jwtGenerator = new CustomJWTGenerator(secretKey);
        String generatedJWT = jwtGenerator.generateJWT("SamplePayload");

        System.out.println("Generated JWT: " + generatedJWT);

        // Part 2: CustomJWTVerifier
        CustomJWTVerifier jwtVerifier = new CustomJWTVerifier(secretKey);
        boolean isValid = jwtVerifier.verifyJWT(generatedJWT);

        if (isValid) {
            System.out.println("JWT is valid.");
        } else {
            System.out.println("JWT is not valid.");
        }
    }
}

