package com.blog.commuity.domain.post.presentation;


import com.blog.commuity.domain.post.dto.PostReqDto;
import com.blog.commuity.domain.post.application.PostService;
import com.blog.commuity.domain.post.dto.PostResDto;
import com.blog.commuity.domain.post.dto.PostWithCommentsResDto;
import com.blog.commuity.domain.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @Operation(summary = "post 하나만 가져오기")
    @GetMapping("/post/{id}")
    public ResponseEntity<?> getPost(@PathVariable Long id) {
        PostWithCommentsResDto postWithComments = postService.getPostWithComments(id);
        return ResponseEntity.ok(postWithComments);
    }

    @Operation(summary = "post 리스트 가져오기")
    @GetMapping("/posts")
    public ResponseEntity<?> getPostList(@PageableDefault(direction = Sort.Direction.DESC, sort = "id") Pageable page) {
        return ResponseEntity.ok(postService.getPostList(page));
    }


    @Operation(summary = "post 등록하기")
    @PostMapping("/post/create")
    public ResponseEntity<?> register(@RequestBody @Valid PostReqDto postReqDto, @AuthenticationPrincipal User user) throws IOException {
        Long id = postService.register(postReqDto, user.getId());
        return ResponseEntity.ok(id);
    }


    @Operation(summary = "post 삭제하기")
    @DeleteMapping("/post/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id, @AuthenticationPrincipal User user) {
        System.out.println(id);
        postService.remove(id, user.getId());
        return ResponseEntity.ok(id);
    }


    @Operation(summary = "post 수정하기")
    @PostMapping("/post/{id}")
    public ResponseEntity<?> editPost(@PathVariable Long id, @RequestBody @Valid PostReqDto postReqDto, @AuthenticationPrincipal User user) {
        postService.edit(id, postReqDto, user.getId());
        return ResponseEntity.ok(id);
    }
}
