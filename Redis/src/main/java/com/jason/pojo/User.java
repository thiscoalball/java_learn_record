package com.jason.pojo;


import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class User implements Serializable{
    private int id;
    private String name;
    private String password;
    private String birthday;
}
