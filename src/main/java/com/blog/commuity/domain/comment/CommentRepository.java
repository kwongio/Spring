package com.blog.commuity.domain.comment;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {


    @EntityGraph(attributePaths = {"user", "post"})
    List<Comment> findAll();


    @EntityGraph(attributePaths = {"user", "post"})
    @Query(value = "select c from Comment c where c.post.id = :postId")
    List<Comment> findByPostId(@Param("postId") Long postId);
}
