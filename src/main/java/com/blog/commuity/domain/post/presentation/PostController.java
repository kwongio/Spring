package com.blog.commuity.domain.post.presentation;


import com.blog.commuity.domain.post.dto.PostReqDto;
import com.blog.commuity.domain.post.application.PostService;
import com.blog.commuity.domain.post.entity.Post;
import com.blog.commuity.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/post/{id}")
    public ResponseEntity<?> getPost(@PathVariable Long id) {
        Post post = postService.getPost(id);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/posts")
    public ResponseEntity<?> getPostList(Pageable page) {
        return ResponseEntity.ok(postService.getPostList(page));
    }

    @PostMapping("/post/create")
    public ResponseEntity<?> register(@RequestBody @Valid PostReqDto postReqDto, @AuthenticationPrincipal User user) {
        Post post = postService.register(postReqDto, user.getId());
        return ResponseEntity.ok(post);
    }


//    @PostMapping("/post/edit/{postId]")
//    public ResponseEntity<?> register(@RequestBody @Valid PostReqDto postReqDto, @AuthenticationPrincipal User user) {
//        Post post = postService.register(postReqDto, user.getId());
//        return ResponseEntity.ok(post);
//    }
}
