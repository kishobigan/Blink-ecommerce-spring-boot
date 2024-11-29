package com.example.blink.security;

import com.example.blink.repositories.UserRepository;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;


@Component
public class JwtUtil {
    private static final String SECRET = "ythfdsjkalpqowieudjfcnmxzncbvxbzn";
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    public static SecretKey getSecretKey() {
        return SECRET_KEY;
    }

    private final int JWT_EXPIRE = 3600 * 1000;

    private UserRepository userRepository;

    public JwtUtil(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String generateToken(String userId) {

        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + JWT_EXPIRE))
                .signWith(getSecretKey())
                .compact();
    }

    public Long extractUserId(String token) {
        String userId = Jwts.parser()
                .setSigningKey(getSecretKey())
                .build()
                .parseEncryptedClaims(token)
                .getBody()
                .getSubject();
        return Long.parseLong(userId);
    }

    public boolean validateToken(String token) {
        try{
            Jwts.parser()
                    .setSigningKey(getSecretKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        }catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
