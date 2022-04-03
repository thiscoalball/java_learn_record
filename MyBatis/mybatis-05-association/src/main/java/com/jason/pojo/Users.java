package com.jason.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Users {
    private Integer userId;
    private String name;
    private String pwd;
    private String realName;
    private String userImg;

    private UsersDetails usersDetails;
}
