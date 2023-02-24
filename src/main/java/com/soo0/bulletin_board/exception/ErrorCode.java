package com.soo0.bulletin_board.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INVALID_INPUT_VALUE(400, "COMMON-001", "유효성 검증에 실패한 경우"),
    INTERNAL_SERVER_ERROR(500, "COMMON-002", "서버에서 처리할 수 없는 경우"),

    DUPLICATE_LOGIN_ID(400, "ACCOUNT-001", "계정명이 중복된 경우"),
    UNAUTHORIZED(401, "ACCOUNT-002", "인증에 실패한 경우"),
    ACCOUNT_NOT_FOUND(404, "ACCOUNT-003", "게정을 찾을 수 없는 경우");

    private final int status;
    private final String code;
    private final String description;
}
