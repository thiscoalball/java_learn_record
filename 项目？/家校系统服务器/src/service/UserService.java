package service;

import Common.Message;
import Common.MessageType;
import DAO.UserDAO;
import Server.ManageConnectionThreads;
import domain.User;

import java.sql.SQLException;

@SuppressWarnings({"all"})
public class UserService {
    private UserDAO userDAO = new UserDAO();

    //验证用户消息是否存在 密码是否正确
    public User checkPerson(String personId, String pwd) throws SQLException {
        String sql = "SELECT personId,`name`,state FROM USER WHERE personId=? AND pwd=MD5(?) ";
        return userDAO.querySingle(sql, User.class, personId, pwd);
    }

    //找一个用户发消息
    public Message getOneUserToSendMessage(Message message) throws SQLException {
        if (message.getReceiver().equals("null")) {
            return null;
        }
        String senderName = message.getSender().split(" ")[0];
        String senderId = message.getSender().split(" ")[1];
        String receiverName = message.getReceiver().split(" ")[0];
        String receiverId = message.getReceiver().split(" ")[1];

        String sql = "select * from user where personId=? and Name=?";
        User user = userDAO.querySingle(sql, User.class, receiverId, receiverName);

        System.out.println("用户：" + senderName + "  请求与 " + receiverName + " 进行通信\t\t" + message.getSendTime());
        if (message == null) {
            System.out.println("数据库查询不到该用户 已经将消息返回给用户" + "\t" + message.getSendTime());
            message.setContent("查找不到该用户");
            message.setReceiver(message.getSender());
            message.setSender("服务器");
            message.setSendTime(message.getSendTime());
            return message;
        }
        if (user == null) {
            //将数据包发回并提示 没有该用户（所以可以直接操作这个数据包发回去）
            System.out.println("数据库查询不到该用户 已经将消息返回给用户" + "\t" + message.getSendTime());
            message.setContent("查找不到该用户");
            message.setReceiver(message.getSender());
            message.setSender("服务器");
            message.setSendTime(message.getSendTime());
            return message;
        }
        if (ManageConnectionThreads.getServerConnectionThread(receiverId) == null) {
            //新建一个数据包发回并提示用户不在线，因为原数据包需要存入离线消息库
            System.out.println("用户：" + receiverName + "不在线 已将消息存入离线数据库");
            Message message1 = new Message();
            message1.setMesType(MessageType.MESSAGE_COMMON_MES);
            message1.setReceiver(message.getSender());
            message1.setContent("用户不在线 已将消息存入离线数据库" + " " + receiverName);
            message1.setSendTime(message.getSendTime());
            message1.setSender("服务器");
            return message1;
        }
        //这里就是在线的情况
        return message;
    }

    //转型
    public Message getStateById(User user, Message message) {

        if (user == null) {
            message.setSender("服务器");
            message.setReceiver(message.getSender());
            System.out.println("此人并无对应该岗位的职位\t\t\t" + message.getSendTime());
            message.setContent("很抱歉，查无您的身份消息，请确认。");
            return message;
        }
        System.out.println("正在为其执行user转型\t\t\t\t\t" + message.getSendTime());
        message.setSender("服务器");
        message.setReceiver(message.getSender());
        message.setUser(user);
        message.setContent("转型");
        return message;
    }

    //创建用户
    public Message createUser(Message message) throws SQLException {
        Message message1 = new Message();
        String state = "";
        String type = message.getContent().split(" ")[0];
        String name = message.getContent().split(" ")[1];
        String id = message.getContent().split(" ")[2];
        String pwd = message.getContent().split(" ")[3];
        //监护人此刻不具有classId 所以需要跳过 不然数组越界
        if (!type.equals("guardian")) {
            String classId = message.getContent().split(" ")[4];
        }

        if (type.equals("teacher")) {
            state = "教师";
        }
        if (type.equals("guardian")) {
            state = "监护人";
        }
        if (type.equals("student")) {
            state = "学生";
        }

        String sql = "insert into user values(null,?,?,md5(?),?)";
        int update = userDAO.update(sql, name, id, pwd, state);
        if (update < 1) {
            System.out.println("操作失败\t\t\t\t\t" + message.getSendTime());
            message1.setContent("创建用户失败");
            message1.setSender("服务器");
            message1.setSendTime(message.getSendTime());
            message1.setReceiver(name);
            message1.setMesType(MessageType.MESSAGE_COMMON_MES);
            return message1;
        } else {
            message1.setSender("服务器");
            message1.setSendTime(message.getSendTime());
            message1.setReceiver(name);
            message1.setMesType(MessageType.MESSAGE_COMMON_MES);
            message1.setContent("成功创建");
            return message1;
        }
    }

    //检测是否有账号一样的
    public Message idIsExist(Message message) throws SQLException {
        Message message1 = new Message();
        String name = message.getContent().split(" ")[1];
        String id = message.getContent().split(" ")[2];

        String sql = "select personId from user where personId = ?";
        User user = userDAO.querySingle(sql, User.class, message.getContent().split(" ")[2]);
        if (user != null) {
            message1.setSender("服务器");
            message1.setSendTime(message.getSendTime());
            message1.setReceiver(name);
            message1.setMesType(MessageType.MESSAGE_COMMON_MES);
            message1.setContent("存在账号一样的人 无法注册");
            System.out.println("存在账号一样的人 无法注册\t\t\t" + message.getSendTime());
            return message1;
        } else {
            System.out.println("用户成功增加 姓名:" + name + " 账号:" + id + "\t\t" + message.getSendTime());
            message1.setSender("服务器");
            message1.setSendTime(message.getSendTime());
            message1.setReceiver(name);
            message1.setMesType(MessageType.MESSAGE_COMMON_MES);
            message1.setContent("没问题");
            return message1;
        }

    }

    //根据name和id判断是否存在
    public User isExist(String name, String id) throws SQLException {
        String sql = "SELECT NAME , personId,state FROM `user` WHERE name= ? AND personId = ?";
        User user = userDAO.querySingle(sql, User.class, name, id);
        if (user == null) {
            return null;
        } else {
            return user;
        }
    }
}

