package com.blog.commuity.domain.post.repository;

import com.blog.commuity.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @EntityGraph(attributePaths = "user")
    Page<Post> findAll(Pageable pageable);


    @EntityGraph(attributePaths = "user")
    Optional<Post> findById(Long id);

}
