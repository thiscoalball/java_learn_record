package service;

import Common.Message;
import domain.User;

import java.sql.SQLException;

public class GetStateByIdFactory {
    static TeacherService teacherService = new TeacherService();
    static StudentService studentService = new StudentService();
    static GuardianService guardianService = new GuardianService();
    public static User getStateById(Message message) throws SQLException {
        User user = new User();
        //解包
        String[] info = message.getSender().split(" ");
        switch (message.getContent()) {
            case "teacher":
                return teacherService.getStateById(info[0], info[1]);
            case "student":
                return studentService.getStateById(info[0], info[1]);
            case "guardian":
                return guardianService.getStateById(info[0], info[1]);
        }
        return null;
    }
}
