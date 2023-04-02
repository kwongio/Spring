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
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;


    public ParentCommentResDto addComment(CommentReqDto commentReqDto, Long postId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        Comment comment = commentRepository.save(commentReqDto.toEntity(user, post));
        return new ParentCommentResDto(comment);
    }

    public void addReplyComment(CommentReqDto commentReqDto, Long postId, Long userId, Long commentId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        Comment parentComment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        parentComment.addChildComment(commentReqDto.toEntity(user, post));
        commentRepository.save(parentComment);
    }


    public List<ParentCommentResDto> getCommentByPostId(Long postId) {
        List<Comment> commentList = commentRepository.findByPostId(postId);
        List<ParentCommentResDto> result = new ArrayList<>();
        for (Comment comment : commentList) {
            if (comment.getParentComment() == null) {
                ParentCommentResDto commentResDto = new ParentCommentResDto(comment);
                commentResDto.setChildComment(getChildComments(comment));
                result.add(commentResDto);
            }

        }
        return result;
    }

    private List<ChildrenCommentResDto> getChildComments(Comment comment) {
        List<ChildrenCommentResDto> childComments = new ArrayList<>();
        for (Comment child : comment.getChildComments()) {
            ChildrenCommentResDto childCommentDto = new ChildrenCommentResDto(child);
            childComments.add(childCommentDto);
        }
        return childComments;
    }
}
