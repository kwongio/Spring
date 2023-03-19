package com.blog.commuity.global.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.blog.commuity.domain.user.entity.Role;
import com.blog.commuity.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.Date;

@RequiredArgsConstructor
@Slf4j
public class Jwt {
    public static final String ACCESS_TOKEN_SECRET_KEY = "시크릿키";
    public static final String REFRESH_TOKEN_SECRET_KEY = "시크릿키";
    public static final long ACCESS_TOKEN_EXPIRATION_TIME = Duration.ofSeconds(10).toMillis();
    public static final long REFRESH_TOKEN_EXPIRATION_TIME = Duration.ofDays(14).toMillis();
    public static final String PREFIX = "Bearer ";

    public static String createAccessToken(User user) {
        String jwtToken = JWT.create().withSubject("jwt").withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION_TIME)).withClaim("id", user.getId()).withClaim("role", user.getRole().name()).sign(Algorithm.HMAC512(ACCESS_TOKEN_SECRET_KEY));
        return PREFIX + jwtToken;

    }

    public static String createRefreshToken(User user) {
        return JWT.create().withSubject("jwt").withExpiresAt(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION_TIME)).withClaim("id", user.getId()).withClaim("role", user.getRole().name()).sign(Algorithm.HMAC512(REFRESH_TOKEN_SECRET_KEY));
    }

    public static User accessTokenVerify(String token) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(ACCESS_TOKEN_SECRET_KEY)).build().verify(token);
        Long id = decodedJWT.getClaim("id").asLong();
        String role = decodedJWT.getClaim("role").asString();
        return new User(id, Role.valueOf(role));
    }

    public static User refreshTokenVerify(String token) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(REFRESH_TOKEN_SECRET_KEY)).build().verify(token);
        Long id = decodedJWT.getClaim("id").asLong();
        String role = decodedJWT.getClaim("role").asString();
        return new User(id, Role.valueOf(role));

    }

}
