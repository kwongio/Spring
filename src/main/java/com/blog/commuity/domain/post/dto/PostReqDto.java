package com.blog.commuity.domain.post.dto;


import com.blog.commuity.domain.post.entity.Post;
import com.blog.commuity.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
public class PostReqDto {


    @NotBlank
    private final String title;
    @NotBlank
    private final String content;
    private String imageUrl;
    private String imageOriginalName;
    private String mime;
    private String imageSaveName;


    public Post toEntity(User user) {
        return new Post(title, content, user, imageUrl, imageOriginalName, mime, imageSaveName);
    }

    public void setImage(MultipartFile file, String path, String imageSaveName) {
        this.imageUrl = path;
        this.imageOriginalName = file.getOriginalFilename();
        this.mime = file.getContentType();
        this.imageSaveName = imageSaveName;
    }

}
