package com.jason.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Integer stu_id;
    private String stu_num;
    private String stu_name;
    private String stu_gender;
    private Integer stu_age;
}
