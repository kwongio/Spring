package com.blog.commuity.domain.post.dto;


import com.blog.commuity.domain.comment.ParentCommentResDto;
import com.blog.commuity.domain.post.entity.Post;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PostWithCommentsResDto implements Serializable {
    private final Long id;
    private final String title;
    private final String content;
    private final Long views;
    private final PostWriteUserDto user;
    private final LocalDateTime createAt;
    private final LocalDateTime updateAt;

    private final List<ParentCommentResDto> comments;

    public PostWithCommentsResDto(Post post, List<ParentCommentResDto> commentResDto) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.views = post.getViews();
        this.user = new PostWriteUserDto(post.getUser());
        this.createAt = post.getCreateAt();
        this.updateAt = post.getUpdateAt();
        this.comments = commentResDto;
    }

}