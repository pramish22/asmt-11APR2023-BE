package com.citytech.assessment.security;

import com.citytech.assessment.user.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenProvider {

    private static final String SECRET_KEY = "secretKey";

    @Autowired
    private UserService userService;

    public String generateJWTToken(String userId, long expireAfterMsec) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expireAfterMsec);

        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }
}
