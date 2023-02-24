package com.soo0.bulletin_board.domain.vo;

import lombok.NoArgsConstructor;

import java.util.Date;

public interface User {
    Integer getUserId();
    String getEmail();
    String getPassword();

    void setPassword(String password);
    String getUserName();
    Date getRegisterDate();
}
