package com.blog.commuity.domain.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Getter
@AllArgsConstructor
public class ChildrenCommentResDto {
    private final String content;

    public ChildrenCommentResDto(Comment comment) {
        this.content = comment.getContent();
    }
}
