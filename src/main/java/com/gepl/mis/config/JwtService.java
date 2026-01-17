package com.gepl.mis.config;

import com.gepl.mis.auth.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {
    private final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);


    @Value("${jwt.expiration}")
    private long expiration;

    public String generateToken(User user){
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("role",user.getRole().name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration ))
                .signWith(key)
                .compact();

    }
}
