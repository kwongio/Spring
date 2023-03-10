package com.blog.commuity.domain.user.dto;

import com.blog.commuity.domain.user.entity.Role;
import com.blog.commuity.domain.user.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class UserRespDto {

    private final Long id;
    private final String username;
    private final String password;
    private final String email;
    private final String fullName;
    private final Role role;
    private final LocalDateTime createAt;
    private final LocalDateTime updateAt;

    public UserRespDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.fullName = user.getFullName();
        this.role = user.getRole();
        this.createAt = user.getCreateAt();
        this.updateAt = user.getUpdateAt();
    }
}
