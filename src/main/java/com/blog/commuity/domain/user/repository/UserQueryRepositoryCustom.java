package com.blog.commuity.domain.user.repository;

import com.blog.commuity.domain.user.entity.User;

public interface UserQueryRepositoryCustom {

    User findByUserId(Long id);
}
