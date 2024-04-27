package com.todoApi.TodoApi.Jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Value("${env.secret_key}")
    private String secret_key;

    public String generateToken(String email) {
        return generateToken(email, new HashMap<>());
    }

    public String generateToken(String email, Map<String, Object> extraClaims) {
        return "Bearer " + Jwts.builder()
                .addClaims(extraClaims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 604800000))
                .setSubject(email)
                .signWith(getSingKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    public String getSubjet(String token) {
        return extrackClaim(token).getSubject();
    }

    public Boolean isValidToken(UserDetails userDetails, String token) {
        return (getSubjet(token).equals(userDetails.getUsername()) && tokenExpired(token));
    }

    private Claims extrackClaim(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSingKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean tokenExpired(String token) {
        return !extrackClaim(token).getExpiration().before(new Date());
    }

    private Key getSingKey() {
        byte[] bytes = Decoders.BASE64.decode(secret_key);
        return Keys.hmacShaKeyFor(bytes);
    }
}
