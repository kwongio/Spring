package com.blog.commuity.domain.post.entity;


import com.blog.commuity.domain.post.dto.PostReqDto;
import com.blog.commuity.domain.user.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Getter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @Lob
    private String content;

    @Column(nullable = false)
    private Long views;
    @ManyToOne(fetch = LAZY)
    @JsonIgnoreProperties("postList")
    private User user;
//    @OneToMany(mappedBy = "post")
//    private List<Comment> commentList = new ArrayList<>();
    @Column(nullable = false)
    @CreatedDate
    private LocalDateTime createAt;
    @Column(nullable = false)
    @LastModifiedDate
    private LocalDateTime updateAt;


    public Post(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.views = 0L;
        this.user = user;
    }

    public void edit(PostReqDto postReqDto) {
        this.title = postReqDto.getTitle();
        this.content = postReqDto.getContent();
    }
}
