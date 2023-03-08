package com.blog.commuity.global.jwt;

import com.blog.commuity.domain.user.entity.Role;
import com.blog.commuity.domain.user.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class JwtTest {

    @Test
    void create() {
        User user = new User(1L, Role.CUSTOMER);
        String token = Jwt.create(user);
        System.out.println(token);
        assertTrue(token.startsWith(Jwt.PREFIX));
    }

    @Test
    void verify() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJiYW5rIiwicm9sZSI6IkNVU1RPTUVSIiwiaWQiOjEsImV4cCI6MTY3ODE3MzA1OH0.1fF2LnUrQRE7CuXDKcAUlJDMxfrbr0BB_OnxWcmWY8sxNfuRPiLVpzWBugy88mws671jhuxsYFW6AYJvqYwMxA";
        User user = Jwt.verify(token);
        System.out.println(user.getId());
        System.out.println(user.getRole());
        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user.getRole()).isEqualTo(Role.CUSTOMER);


    }
}