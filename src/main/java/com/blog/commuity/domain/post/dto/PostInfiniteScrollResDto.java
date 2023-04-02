package com.blog.commuity.domain.post.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PostInfiniteScrollResDto {
    private final String next;
    private final String prev;
    private final List<PostResDto> contents;
}
