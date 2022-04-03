package service;

import Common.Message;
import Common.MessageType;
import DAO.TeacherDAO;
import domain.Teacher;
import domain.User;

import java.sql.SQLException;

@SuppressWarnings({"all"})
public class TeacherService implements getStateById {
    private TeacherDAO teacherDAO = new TeacherDAO();

    @Override
    public User getStateById(String name, String personId) throws SQLException {
        Teacher teacher = teacherDAO.querySingle("select * from teacher where name=? and personId = ?", Teacher.class, name, personId);
        return teacher;
    }

    public Message createTeacher(Message message) throws SQLException {
        Message message1 = new Message();
        String name = message.getContent().split(" ")[1];
        String id = message.getContent().split(" ")[2];
        String pwd = message.getContent().split(" ")[3];
        String classId = message.getContent().split(" ")[4];
        String sql = "insert into teacher values(null,?,?,md5(?),?,'')";
        int update = teacherDAO.update(sql, name, id, pwd, classId);
        if (update < 1) {
            System.out.println("操作失败\t\t\t\t\t" + message.getSendTime());
            message1.setSender("服务器");
            message1.setContent("操作失败");
            message1.setSendTime(message.getSendTime());
            message1.setReceiver(name);
            message1.setMesType(MessageType.MESSAGE_COMMON_MES);
            return message1;
        } else{
            System.out.println("教师成功增加 姓名:"+name+" 账号:"+id+" 班级:"+classId+"\t\t"+message.getSendTime());
            message1.setSender("服务器");
            message1.setSendTime(message.getSendTime());
            message1.setReceiver(name);
            message1.setMesType(MessageType.MESSAGE_COMMON_MES);
            message1.setContent("成功创建");
            return message;
        }
    }
}
