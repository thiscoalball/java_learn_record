package service;

import Common.Message;
import Common.MessageType;
import DAO.StudentDAO;
import domain.Student;
import domain.User;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

@SuppressWarnings({"all"})
public class StudentService implements getStateById {

    private StudentDAO studentDAO = new StudentDAO();
    private AllGradesService allGradesService = new AllGradesService();
    private Vector<Message> messages = null;
    private Message message1 = null;
    private Student student = null;

    //和全班同学聊天
    public Vector<Message> chatWithClassStudent(Message message) throws SQLException {
        String classId = message.getReceiver();
        String sql = "select * from student where classId=?";
        List<Student> students = studentDAO.queryMulti(sql, Student.class, classId);
        String teacherName = message.getSender().split(" ")[0];
        System.out.println(teacherName + " 发起了对班级 " + message.getReceiver() + " 学生的群聊");
        Iterator<Student> iterator = students.iterator();
        messages = new Vector<>();
        while (iterator.hasNext()) {
            message1 = new Message();
            student = iterator.next();
            message1.setContent(message.getContent());
            message1.setMesType(MessageType.MESSAGE_TO_CLASS_STUDENT_MES);
            message1.setReceiver(student.getName() + " " + student.getPersonId());
            message1.setSender(message.getSender());
            message1.setSendTime(message.getSendTime());
            messages.add(message1);
        }
        return messages;
    }

    //利用classId返回guardianId 提供给guardianService使用
    public Vector<String> getGuardianIdByClassId(String classId) throws SQLException {
        Vector<String> guardianIds = new Vector<>();
        String sql = "select guardianId from student where classId = ?";
        List<Student> students = studentDAO.queryMulti(sql, Student.class, classId);
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            String guardianId = iterator.next().getGuardianId();
            guardianIds.add(guardianId);
        }
        return guardianIds;
    }

    //根据学生id返回监护人姓名+监护人id
    public String getGuardianIdAndNameByStudentId(String studentId) throws SQLException {
        String sql = "select guardianId , guardianName from student where personId = ?";
        Student student = studentDAO.querySingle(sql, Student.class, studentId);
        if (student == null) {
            return "null";
        }
        String guardianId = student.getGuardianId();
        String guardianName = student.getGuardianName();
        return guardianName + " " + guardianId;
    }

    //根据学生的id 返回guardianIds + 学生id的消息集
    public Vector<Message> getGuardianIdAndStudentIdByStudentId(Message message) throws SQLException {
        System.out.println(message.getReceiver());
        System.out.println("\n开始发送 " + message.getReceiver().split(" ")[0] + " 的成绩给他和她监护人" + message.getSendTime());
        Message oneGradeByIdAndName = allGradesService.getOneGradeByIdAndName(message);
        Vector<String> Ids = new Vector<>();
        String studentId = message.getReceiver().split(" ")[1];
        //获取姓名+id存入列表
        Ids.add(message.getReceiver());
        String guardianNameAndId = getGuardianIdAndNameByStudentId(studentId);
        Ids.add(guardianNameAndId);
        Iterator<String> iterator = Ids.iterator();
        messages = new Vector<>();
        while (iterator.hasNext()) {
            message1 = new Message();
            message1.setSendTime(message.getSendTime());
            message1.setSender(message.getSender());
            message1.setContent(oneGradeByIdAndName.getContent());
            message1.setReceiver(iterator.next());
            message1.setMesType(MessageType.MESSAGE_GET_ONE_GRADE);
            messages.add(message1);
        }
        return messages;
    }

    public boolean isExist(String name, String id) throws SQLException {
        String sql = "select * from student where personId=? and name =?";
        Object o = studentDAO.queryScalar(sql, id, name);
        if (o == null) {
            return false;
        } else {
            return true;
        }
    }

    public Message getClassInfo(Message message) throws SQLException {
        String content = "";
        String classId = message.getContent();
        String sql = "select name,personId,guardianName,guardianId from student where classId = ?";
        System.out.println(message.getSender().split(" ")[0] + " 请求获取 " + classId + " 班详情\t\t" + message.getSendTime());
        Iterator<Student> iterator = studentDAO.queryMulti(sql, Student.class, classId).iterator();
        while (iterator.hasNext()) {
            Student student =  iterator.next();
            content += student.getName()+"\t\t\t"+
                    student.getPersonId()+"\t\t"+
                    student.getGuardianName()+"\t\t\t"+
                    student.getGuardianId()+"\t\t\n";
        }
        message.setContent(content);
        message.setSender(message.getReceiver());
        message.setReceiver(message.getSender());
        return message;
    }

    public Message createStudent(Message message) throws SQLException {
        Message message1 = new Message();
        String name = message.getContent().split(" ")[1];
        String id = message.getContent().split(" ")[2];
        String pwd = message.getContent().split(" ")[3];
        String classId = message.getContent().split(" ")[4];
        String sql = "insert into student values(null,?,?,md5(?),?,'','')";
        int update = studentDAO.update(sql, name, id, pwd, classId);
        if (update < 1) {
            System.out.println("操作失败\t\t\t\t\t" + message.getSendTime());
            message1.setSender("服务器");
            message1.setContent("操作失败");
            message1.setSendTime(message.getSendTime());
            message1.setReceiver(name);
            message1.setMesType(MessageType.MESSAGE_COMMON_MES);
            return message1;
        } else{
            System.out.println("学生成功增加 姓名:"+name+" 账号:"+id+" 班级:"+classId+"\t\t"+message.getSendTime());
            message1.setSender("服务器");
            message1.setSendTime(message.getSendTime());
            message1.setReceiver(name);
            message1.setMesType(MessageType.MESSAGE_COMMON_MES);
            message1.setContent("成功创建");
            return message;
        }
    }

    public Message bindGuardian(Message message) throws SQLException {
        Message message1 = new Message();

        String[] info = message.getContent().split(" ");
        String senderName = message.getSender().split(" ")[0];
        String senderId = message.getSender().split(" ")[1];

        String guardianName = info[1]; 
        String guardianId = info[2];
        String sql = "UPDATE student SET guardianName=?,guardianId=? WHERE NAME=? AND personId = ?";

        int update = studentDAO.update(sql, guardianName, guardianId, senderName, senderId);
        System.out.println("用户:"+senderName+" 请求绑定用户:"+guardianName+"\t\t"+message.getSendTime());
        if (update > 0) {
            message1.setSender("服务器");
            message1.setSendTime(message.getSendTime());
            message1.setReceiver(senderName + " " + senderId);
            message1.setMesType(MessageType.MESSAGE_COMMON_MES);
            message1.setContent("绑定成功");
            return message1;
        } else {
            message1.setSender("服务器");
            message1.setSendTime(message.getSendTime());
            message1.setReceiver(senderName + " " + senderId);
            message1.setMesType(MessageType.MESSAGE_COMMON_MES);
            message1.setContent("绑定失败");
            return message1;
        }
    }
    @Override
    public User getStateById(String name, String personId) throws SQLException {
        Student student = studentDAO.querySingle("select * from student where name=? and personId = ?", Student.class, name, personId);
        return student;
    }

}
