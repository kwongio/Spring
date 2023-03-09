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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

@Transactional
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostRespDto getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        return new PostRespDto(post);
    }

    public Page<PostRespDto> getPostList(Pageable page) {
        return postRepository.findAll(page).map(PostRespDto::new);
    }

    public PostRespDto register(PostReqDto postReqDto, Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        Post post = postRepository.save(postReqDto.toEntity(user));
        return new PostRespDto(post, user);
    }

    public void remove(Long id) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        postRepository.delete(post);
    }

    public PostRespDto edit(Long id, @Valid PostReqDto postReqDto) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        post.edit(postReqDto);
        return new PostRespDto(post);

    }
}
