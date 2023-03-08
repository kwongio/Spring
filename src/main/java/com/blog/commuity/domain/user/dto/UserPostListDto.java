package com.blog.commuity.domain.user.dto;

import com.blog.commuity.domain.post.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserPostListDto {

    private final Long id;
    private final String title;
    private final String content;
    private final Long views;
    private final LocalDateTime createAt;
    private final LocalDateTime updateAt;

    public UserPostListDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.views = post.getViews();
        this.createAt = post.getCreateAt();
        this.updateAt = post.getUpdateAt();
    }
}
