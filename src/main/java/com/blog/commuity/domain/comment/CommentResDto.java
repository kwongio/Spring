package com.blog.commuity.domain.comment;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data

public class CommentResDto {

    private final String content;

    private  List<CommentReqDto> commentReqDto = new ArrayList<>();
//    private final User user;
//    private final Post post;


    public CommentResDto(Comment comment, List<CommentReqDto> commentReqDto) {
        this.content = comment.getContent();
        this.commentReqDto = commentReqDto;
    }

    public CommentResDto(Comment comment) {
        this.content = comment.getContent();

    }
}
