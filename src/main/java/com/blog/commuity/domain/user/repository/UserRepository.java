package com.blog.commuity.domain.user.repository;

import com.blog.commuity.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByUsernameAndEmail(String username, String email);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    @Query("select u from User u join fetch u.postList p where u.id = :userId")
    Optional<User> findUser(Long userId);




}
