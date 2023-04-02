package com.blog.commuity.domain.comment;


import com.blog.commuity.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comment/{postId}")
    public ResponseEntity<?> addComment(@Valid @RequestBody CommentReqDto commentReqDto, @PathVariable Long postId, @AuthenticationPrincipal User user) {
        ParentCommentResDto commentResDto = commentService.addComment(commentReqDto, postId, user.getId());
        return ResponseEntity.ok("등록완료");
    }


    @PostMapping("/comment/{postId}/{commentId}")
    public ResponseEntity<?> addReplyComment(@Valid @RequestBody CommentReqDto commentReqDto, @PathVariable Long postId, @AuthenticationPrincipal User user, @PathVariable Long commentId) {
        commentService.addReplyComment(commentReqDto, postId, user.getId(), commentId);
        return ResponseEntity.ok("등록완료");
    }




}
