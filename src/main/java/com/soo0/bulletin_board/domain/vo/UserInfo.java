package com.soo0.bulletin_board.domain.vo;

import lombok.*;

import java.util.Date;

@Getter @Setter
@NoArgsConstructor
@ToString
public class UserInfo implements User {
    private Integer userId;
    private String email;
    private String password;
    private String userName;
    private Date registerDate;
    private Boolean isAdmin;

    public UserInfo(String email, String password, String userName) {
        this.email = email;
        this.password = password;
        this.userName = userName;
    }
}
