package com.blog.commuity.domain.post.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class PostInfiniteScrollResDto {


    private final String next;
    private final String prev;
    private final List<PostResDto> contents;


}
