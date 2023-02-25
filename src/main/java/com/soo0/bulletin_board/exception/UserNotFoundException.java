package com.soo0.bulletin_board.exception;

public class UserNotFoundException extends RuntimeException implements ErrorCodeSupport {
    private final ErrorCode errorCode;

    public UserNotFoundException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    @Override
    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
