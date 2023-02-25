package com.soo0.bulletin_board.exception;

import java.util.NoSuchElementException;

public class BoardNotFoundException extends NoSuchElementException implements ErrorCodeSupport {
    private final ErrorCode errorCode;

    public BoardNotFoundException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    @Override
    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
