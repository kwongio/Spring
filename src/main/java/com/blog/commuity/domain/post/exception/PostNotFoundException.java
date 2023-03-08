package com.blog.commuity.domain.post.exception;

public class PostNotFoundException extends PostException {

    private final static String message = "없는 포스트입니다.";

    public PostNotFoundException() {
        super(message);
    }

    public PostNotFoundException(Throwable cause) {
        super(message, cause);
    }
}
