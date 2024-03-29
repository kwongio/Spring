package com.blog.commuity.domain.user.repository;

import com.blog.commuity.domain.user.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


import java.util.Optional;

import static com.blog.commuity.domain.post.entity.QPost.post;
import static com.blog.commuity.domain.user.entity.QUser.user;

@RequiredArgsConstructor
@Repository
public class UserQueryRepositoryImpl implements UserQueryRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<User> findByUserId(Long id) {
        return Optional.ofNullable(jpaQueryFactory.selectFrom(user).join(user.postList, post).fetchJoin().where(user.id.eq(id)).fetchOne());

    }
}
