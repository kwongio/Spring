package com.blog.commuity.domain.post.application;

import com.blog.commuity.domain.post.dto.PostReqDto;
import com.blog.commuity.domain.post.dto.PostRespDto;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Transactional
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final static String FILEPATH = "C:\\study-project\\Next\\public\\images\\";

    public PostRespDto getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        return new PostRespDto(post);
    }

    public Page<PostRespDto> getPostList(Pageable page) {
        return postRepository.findAll(page).map(PostRespDto::new);
    }

    public PostRespDto register(PostReqDto postReqDto, MultipartFile multipartFile, Long id) throws IOException {
        String ext = multipartFile.getContentType().split("/")[1];
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        String imageSaveName = UUID.randomUUID() + "." + ext;
        String path = FILEPATH + imageSaveName;
        multipartFile.transferTo(new File(path));
        postReqDto.setImage(multipartFile, path , imageSaveName);
        Post post = postRepository.save(postReqDto.toEntity(user));
        return new PostRespDto(post);
    }

    public void remove(Long id, Long userId) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        if (post.getUser().getId().equals(userId)) {
            postRepository.delete(post);
        }
        throw new AccessDeniedException("권한없음");

    }

    public PostRespDto edit(Long id, PostReqDto postReqDto, Long userId) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        if (post.getUser().getId().equals(userId)) {
            post.edit(postReqDto);
            return new PostRespDto(post);
        }
        throw new AccessDeniedException("권한없음");
    }
}
