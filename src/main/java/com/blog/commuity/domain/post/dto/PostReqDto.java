package com.blog.commuity.domain.post.dto;


import com.blog.commuity.domain.post.entity.Post;
import com.blog.commuity.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
public class PostReqDto {


    @NotBlank
    private final String title;
    @Lob
    @NotBlank
    private final String content;


    public Post toEntity(User user) {
        return new Post(title, content, user);
    }

}
