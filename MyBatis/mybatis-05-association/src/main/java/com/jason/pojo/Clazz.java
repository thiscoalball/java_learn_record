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
public class Clazz {
    private int classId;
    private String className;
    private String classDesc;

    //存储当前班级下的学生消息
    private List<Student> studentList;
}
