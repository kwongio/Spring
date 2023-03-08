package com.blog.commuity.domain.user.dto;

import com.blog.commuity.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class JoinResDto {

    private final Long id;
    private final String username;
    private final String password;
    private final String email;
    private final String fullName;


    public JoinResDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.fullName = user.getFullName();
    }
}
