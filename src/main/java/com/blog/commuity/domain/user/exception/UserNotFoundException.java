package com.blog.commuity.domain.user.exception;

public class UserNotFoundException extends UserException {
    private final static String message = "존재하지 않는 아이디입니다.";

    public UserNotFoundException() {
        super(message);
    }

    public UserNotFoundException(Throwable cause) {
        super(message, cause);
    }
}
