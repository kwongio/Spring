package com.blog.commuity.domain.user.dto;

import com.blog.commuity.domain.user.entity.User;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class LoginResDto {
    private final Long id;
    private final String username;
    private final String createAt;

    public LoginResDto(User user) {
        id = user.getId();
        username = user.getUsername();
        createAt = user.getCreateAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
