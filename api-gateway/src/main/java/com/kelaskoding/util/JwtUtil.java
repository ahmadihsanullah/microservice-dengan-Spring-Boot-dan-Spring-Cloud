package com.kelaskoding.util;
import java.security.Key;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    public static final String SECRET = "yC2rMJfQHRMiAOUf5ZsR2YZ+sn3Y2JRhUtHkyoWhK0Km0YrD6XVaROkIoE8rRUSppdODssm3GPYMQYrjOBDJyQ=="; 
    

    public Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }

    public String validateToken(String token){
        final Claims claims = extractAllClaims(token);
        return "Token is Valid";
    }

}

