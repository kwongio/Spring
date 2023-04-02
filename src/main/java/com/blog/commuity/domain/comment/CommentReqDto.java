package com.blog.commuity.domain.comment;


import com.blog.commuity.domain.post.entity.Post;
import com.blog.commuity.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentReqDto {

    @NotBlank
    private String content;

    public Comment toEntity(User user, Post post) {
        return new Comment(content, user, post);
    }


}
