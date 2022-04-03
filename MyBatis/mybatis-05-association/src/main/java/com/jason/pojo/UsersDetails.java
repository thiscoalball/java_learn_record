package com.jason.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UsersDetails {
    private Integer detailId;
    private String userAddr;
    private String userTel;
    private String userDesc;
    private Integer uid;

}
