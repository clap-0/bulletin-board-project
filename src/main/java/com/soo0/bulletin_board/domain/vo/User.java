package com.soo0.bulletin_board.domain.vo;

import lombok.NoArgsConstructor;

import java.util.Date;

public interface User {
    Integer getUserId();
    String getEmail();
    String getPassword();

    Boolean getIsAdmin();
    void setPassword(String password);
    String getUserName();

    Date getRegisterDate();

    void setUserId(Integer userId);

    void setEmail(String email);

    void setIsAdmin(Boolean isAdmin);

    void setUserName(String userName);

    void setRegisterDate(Date registerDate);
}
