package com.soo0.bulletin_board.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 에러 코드를 나타내는 enum 타입 클래스이다.
 */
@Getter
@AllArgsConstructor
public enum ErrorCode {
    /** 유효성 검증에 실패한 경우 발생하는 에러 코드 */
    INVALID_INPUT_VALUE(400, "COMMON-001", "유효성 검증에 실패한 경우"),

    /** 서버에서 처리할 수 없는 경우 발생하는 에러 코드 */
    INTERNAL_SERVER_ERROR(500, "COMMON-002", "서버에서 처리할 수 없는 경우"),

    /** 서버에서 처리할 수 없는 경우 발생하는 에러 코드 */
    DATA_MODIFICATION_ERROR(400, "COMMON-003", "데이터 생성/수정/삭제 작업이 실패한 경우"),


    /** 계정명이 중복된 경우 발생하는 에러 코드 */
    DUPLICATE_LOGIN_ID(400, "ACCOUNT-001", "계정명이 중복된 경우"),

    /** 인증에 실패한 경우 발생하는 에러 코드 */
    UNAUTHORIZED(401, "ACCOUNT-002", "인증에 실패한 경우"),

    /** 권한이 없는 경우 발생하는 에러 코드 */
    ACCESS_DENIED(403, "ACCOUNT-003", "권한이 없는 경우"),

    /** 계정을 찾을 수 없는 경우 발생하는 에러 코드 */
    ACCOUNT_NOT_FOUND(404, "ACCOUNT-004", "계정을 찾을 수 없는 경우"),

    /** 로그인에 실패한 경우 발생하는 에러 코드 */
    LOGIN_FAILED(401, "ACCOUNT-005", "로그인에 실패한 경우"),


    /** 게시판을 찾을 수 없는 경우 발생하는 에러 코드 */
    BOARD_NOT_FOUND(404, "BOARD-001", "게시판을 찾을 수 없는 경우"),

    /** 게시글을 찾을 수 없는 경우 발생하는 에러 코드 */
    POST_NOT_FOUND(404, "POST-001", "게시글을 찾을 수 없는 경우");

    /**
     * HTTP 상태 코드
     */
    private final int status;

    /**
     * 에러 코드 문자열
     */
    private final String code;

    /**
     * 에러 코드에 대한 설명
     */
    private final String description;
}
