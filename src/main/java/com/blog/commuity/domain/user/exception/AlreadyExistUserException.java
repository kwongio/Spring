package com.blog.commuity.domain.user.exception;

public class AlreadyExistUserException extends UserException {
    private static final String message = "이미 존재하는 회원이 있습니다.";

    public AlreadyExistUserException() {
        super(message);
    }

    public AlreadyExistUserException(Throwable cause) {
        super(message, cause);
    }
}
