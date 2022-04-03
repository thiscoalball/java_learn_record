package com.jason.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student {
    private String studentId;
    private String studentName;
    private int studentAge;
    private int classId;


    private Clazz clazz;
    //学生所选择的课程
    private List<Course> courses;
}
