package service;

import Common.Message;

import java.sql.SQLException;


@SuppressWarnings({"all"})
public class CreateFactory {
    static TeacherService teacherService = new TeacherService();
    static StudentService studentService = new StudentService();
    static GuardianService guardianService = new GuardianService();
    public static Message createUserByType(Message message) throws SQLException {
        String type = message.getContent().split(" ")[0];
        switch (type){
            case "teacher":
                return teacherService.createTeacher(message);
            case "student":
                return studentService.createStudent(message);
            case "guardian":
                return guardianService.createGuardian(message);
        }
        return null;
    }
}
