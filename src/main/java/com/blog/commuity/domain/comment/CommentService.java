package com.blog.commuity.domain.comment;

import com.blog.commuity.domain.post.entity.Post;
import com.blog.commuity.domain.post.exception.PostNotFoundException;
import com.blog.commuity.domain.post.repository.PostRepository;
import com.blog.commuity.domain.user.entity.User;
import com.blog.commuity.domain.user.exception.UserNotFoundException;
import com.blog.commuity.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;


    public CommentResDto addComment(CommentReqDto commentReqDto, Long postId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        Comment comment = commentRepository.save(commentReqDto.toEntity(user, post));
        return new CommentResDto(comment);
    }

    public void addReplyComment(CommentReqDto commentReqDto, Long postId, Long userId, Long commentId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        Comment parentComment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        Comment replyComment = new Comment(commentReqDto, user, post, parentComment);
        commentRepository.save(replyComment);
    }


    public List<Comment> getCommentList() {
        return commentRepository.findAll();
    }

    public List<CommentResDto> getCommentByPostId(Long postId) {
        return commentRepository.findByPostId(postId).stream().map(CommentResDto::new).collect(Collectors.toList());
    }
}
