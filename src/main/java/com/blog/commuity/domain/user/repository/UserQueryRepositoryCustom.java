package com.blog.commuity.domain.user.repository;

import com.blog.commuity.domain.user.entity.User;

import java.util.Optional;

public interface UserQueryRepositoryCustom {

    Optional<User> findByUserId(Long id);
}
