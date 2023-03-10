package com.blog.commuity.domain.post.dto;


import com.blog.commuity.domain.post.entity.Post;
import com.blog.commuity.domain.user.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostRespDto {

    private final Long id;
    private final String title;
    private final String content;
    private final Long views;
    private final PostWriteUserDto user;
    private final LocalDateTime createAt;
    private final LocalDateTime updateAt;

    public PostRespDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.views = post.getViews();
        this.user = new PostWriteUserDto(post.getUser());
        this.createAt = post.getCreateAt();
        this.updateAt = post.getUpdateAt();
    }


}