package com.blog.commuity.domain.user.exception;

public class AlreadyExistUserException extends UserException {
    private static final String message = "이미 사용중인 아이디 또는 이메일이 있습니다.";

    public AlreadyExistUserException() {
        super(message);
    }

    public AlreadyExistUserException(Throwable cause) {
        super(message, cause);
    }
}
