package com.blog.commuity.global.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.blog.commuity.domain.user.entity.Role;
import com.blog.commuity.domain.user.entity.User;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@RequiredArgsConstructor
public class Jwt {
    public static final String SECRET = "시크릿키";
    public static final int EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 7; //일주일
    public static final String PREFIX = "Bearer ";

    public static String create(User user) {
        String jwtToken = JWT.create()
                .withSubject("bank")
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .withClaim("id", user.getId())
                .withClaim("role", user.getRole().name())
                .sign(Algorithm.HMAC512(SECRET));

        return PREFIX + jwtToken;

    }

    public static User verify(String token) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(SECRET)).build().verify(token);
        Long id = decodedJWT.getClaim("id").asLong();
        String role = decodedJWT.getClaim("role").asString();
        return new User(id, Role.valueOf(role));

    }
}
