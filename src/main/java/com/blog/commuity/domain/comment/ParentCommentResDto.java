package com.blog.commuity.domain.comment;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ParentCommentResDto {

    private final String content;
    private  List<ChildrenCommentResDto> childComment = new ArrayList<>();

    public ParentCommentResDto(Comment comment, List<ChildrenCommentResDto> commentReqDto) {
        this.content = comment.getContent();
        this.childComment = commentReqDto;
    }

    public ParentCommentResDto(Comment comment) {
        this.content = comment.getContent();

    }
}
