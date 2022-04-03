package com.jason.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//这些注解分别代表了 get/set/toString 有参构造 无参构造
public class User {
    private int id;
    private String name;
    private String pwd;

}
