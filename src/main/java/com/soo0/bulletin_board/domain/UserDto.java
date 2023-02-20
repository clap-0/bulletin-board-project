package com.soo0.bulletin_board.domain;

import lombok.*;

import java.util.Date;

@Getter @Setter
public class UserDto {
    private Integer userId;
    private String email;
    private String password;
    private String userName;
    private Date registerDate;

    public UserDto(String email, String password, String userName) {
        this.email = email;
        this.password = password;
        this.userName = userName;
    }
}
