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

@Transactional
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Post getPost(Long id) {
        return postRepository.findById(id).orElseThrow(PostNotFoundException::new);
    }

    public Page<PostRespDto> getPostList(Pageable page) {
        return postRepository.findAll(page).map(PostRespDto::new);
    }

    public Post register(PostReqDto postReqDto, Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        Post post = postReqDto.toEntity(user);
        return postRepository.save(post);
    }
}
