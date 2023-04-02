package com.blog.commuity.domain.post.dto;

import com.blog.commuity.domain.user.entity.User;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
public class PostWriteUserDto implements Serializable {

    private final String username;
    private final String email;
    private final String fullName;
    private final LocalDateTime createAt;

    public PostWriteUserDto(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.fullName = user.getFullName();
        this.createAt = user.getCreateAt();
    }
}
