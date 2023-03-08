package com.blog.commuity.domain.user.dto;


import com.blog.commuity.domain.user.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UserInfoResDto {
    private final String username;
    private final String email;
    private final String fullName;
    private final List<UserPostListDto> postList;
    private final LocalDateTime createAt;

    public UserInfoResDto(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.fullName = user.getFullName();
        this.postList = user.getPostList().stream().map(UserPostListDto::new).collect(Collectors.toList());
        this.createAt = user.getCreateAt();
    }
}
