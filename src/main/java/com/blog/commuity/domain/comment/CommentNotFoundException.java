package com.blog.commuity.domain.comment;

public class CommentNotFoundException extends RuntimeException {
    private final static String message = "존재하지 않는 댓글입니다";

    public CommentNotFoundException() {
        super(message);
    }

    public CommentNotFoundException(Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
