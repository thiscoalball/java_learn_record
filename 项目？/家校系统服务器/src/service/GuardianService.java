package service;

import Common.Message;
import Common.MessageType;
import DAO.GuardianDAO;
import domain.Guardian;
import domain.Student;
import domain.User;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class GuardianService implements getStateById {
    private GuardianDAO guardianDAO = new GuardianDAO();
    private Vector<Message> messages = null;

    //和班级家长聊天
    public Vector<Message> chatWithClassGuardians(Message message) throws SQLException {
        String sql = "SELECT * FROM guardian WHERE classId = ?";
        messages = new Vector<>();
        String teacherName = message.getSender().split(" ")[0];
        System.out.println(teacherName + "向 " + message.getReceiver() + " 班级的家长发起会话");
        Message message1 = null;
        String classId = message.getReceiver();
        List<Guardian> guardians = guardianDAO.queryMulti(sql, Guardian.class, classId);
        Iterator<Guardian> iterator = guardians.iterator();
        while (iterator.hasNext()) {
            message1 = new Message();
            Guardian guardian = iterator.next();
            message1.setReceiver(guardian.getName() + " " + guardian.getClassId());
            message1.setSendTime(message.getSendTime());
            message1.setContent(message.getContent());
            message1.setMesType(MessageType.MESSAGE_TO_CLASS_GUARDIANS_MES);
            message1.setSender(message.getSender());
            messages.add(message1);
        }
        return messages;
    }

    public Message createGuardian(Message message) throws SQLException {
        Message message1 = new Message();
        String name = message.getContent().split(" ")[1];
        String id = message.getContent().split(" ")[2];
        String pwd = message.getContent().split(" ")[3];
        String sql = "insert into guardian values(null,?,?,md5(?),'','','')";
        int update = guardianDAO.update(sql, name, id, pwd);
        if (update < 1) {
            System.out.println("操作失败\t\t\t\t\t" + message.getSendTime());
            message1.setSender("服务器");
            message1.setContent("操作失败");
            message1.setSendTime(message.getSendTime());
            message1.setReceiver(name);
            message1.setMesType(MessageType.MESSAGE_COMMON_MES);
            return message1;
        } else {
            System.out.println("监护人成功增加 姓名:" + name + " 账号:" + id + "\t\t" + message.getSendTime());
            message1.setSender("服务器");
            message1.setSendTime(message.getSendTime());
            message1.setReceiver(name + " " + id);
            message1.setMesType(MessageType.MESSAGE_COMMON_MES);
            message1.setContent("成功创建");
            return message;
        }
    }

    public Message bindChild(Message message) throws SQLException {
        Message message1 = new Message();

        String[] info = message.getContent().split(" ");
        String senderName = message.getSender().split(" ")[0];
        String senderId = message.getSender().split(" ")[1];

        String childName = info[1];
        String childId = info[2];
        String classId = info[3];
        System.out.println(childName);
        System.out.println(childId);
        System.out.println(classId);
        String sql = "UPDATE guardian SET childName=?,childId=?,classId=? WHERE NAME=? AND personId = ?";
        System.out.println("用户:"+senderName+" 请求绑定用户:"+childName+"\t\t"+message.getSendTime());
        int update = guardianDAO.update(sql, childName, childId, classId, senderName, senderId);
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
        Guardian guardian = guardianDAO.querySingle("select * from guardian where name=? and personId = ?", Guardian.class, name, personId);
        return guardian;
    }
}
