package com.hassan.Security.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtConfig {

    private final String secretKey = generateSecretKey();


    public String generateSecretKey() {
        try {
            KeyGenerator kGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk = kGen.generateKey();
            return Base64.getEncoder().encodeToString(sk.getEncoded());

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


    public String generateToken(String email) {
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    private Key getKey() {
        byte[] key = Base64.getDecoder().decode(secretKey);
        return Keys.hmacShaKeyFor(key);
    }




    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build().parseClaimsJws(token).getBody();
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }


    public String extractUserName(String token) {
        // extract the username from jwt token
        return extractClaim(token, Claims::getSubject);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equalsIgnoreCase(userDetails.getUsername()) && !isTokenExpired(token));
    }



    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }


}
