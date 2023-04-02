package com.blog.commuity.domain.post.application;

import com.blog.commuity.domain.comment.CommentResDto;
import com.blog.commuity.domain.comment.CommentService;
import com.blog.commuity.domain.post.dto.PostReqDto;
import com.blog.commuity.domain.post.dto.PostResDto;
import com.blog.commuity.domain.post.dto.PostWithCommentsResDto;
import com.blog.commuity.domain.post.entity.Post;
import com.blog.commuity.domain.post.exception.PostNotFoundException;
import com.blog.commuity.domain.post.repository.PostRepository;
import com.blog.commuity.domain.user.entity.User;
import com.blog.commuity.domain.user.exception.UserNotFoundException;
import com.blog.commuity.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentService commentService;
    private final static String FILEPATH = "C:\\study-project\\Next\\public\\images\\";

    public PostWithCommentsResDto getPostWithComments(Long id) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        List<CommentResDto> comment = commentService.getCommentByPostId(id);
        return new PostWithCommentsResDto(post, comment);
    }

    public Page<PostResDto> getPostList(Pageable page) {
        return postRepository.findAll(page).map(PostResDto::new);
    }

    public Long register(PostReqDto postReqDto, Long userId) throws IOException {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
//        imageSave(postReqDto, multipartFile);
        Post post = postRepository.save(postReqDto.toEntity(user));
        return post.getId();
    }


    public void remove(Long id, Long userId) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        if (!post.getUser().getId().equals(userId)) throw new AccessDeniedException("권한없음");
        postRepository.delete(post);

    }

    public void edit(Long id, PostReqDto postReqDto, Long userId) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        if (!post.getUser().getId().equals(userId)) throw new AccessDeniedException("권한없음");
        post.edit(postReqDto);

    }


//    private static void imageSave(PostReqDto postReqDto, MultipartFile multipartFile) throws IOException {
//        String ext = multipartFile.getContentType().split("/")[1];
//        String imageSaveName = UUID.randomUUID() + "." + ext;
//        String path = FILEPATH + imageSaveName;
//        multipartFile.transferTo(new File(path));
//        postReqDto.setImage(multipartFile, path, imageSaveName);
//    }

}
