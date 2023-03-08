package com.blog.commuity.domain.user.dto;

import com.blog.commuity.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
public class JoinReqDto {

    @NotBlank
    private final String username;
    @NotBlank
    private final String password;
    @NotBlank
    @Email
    private final String email;
    @NotBlank
    private final String fullName;

    public JoinReqDto(String username, String password, String email, String fullName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
    }

    public User toEntity(BCryptPasswordEncoder encode) {
        return new User(username, encode.encode(password), email, fullName);
    }
}