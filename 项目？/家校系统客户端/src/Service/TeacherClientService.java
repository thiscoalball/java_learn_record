package Service;

import Common.Message;
import Common.MessageType;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings({"all"})
//完成教师的创建等功能
public class TeacherClientService implements MessageServiceInterface, getStateById {

    private MessageService messageService = new MessageService();

    //获取班级成绩的id
    public void getGradesByClass(String senderName, String classId, String senderId) throws IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_GET_CLASS_GRADE);
        message.setSender(senderName + " " + senderId);
        message.setReceiver("服务器");
        message.setContent(classId);
        message.setSendTime(simpleDateFormat.format(date));
        System.out.println("你发起了对 " + classId + " 班级的成绩查询\t\t" + simpleDateFormat.format(date));
        //从管理线程类中取出对应的线程对象发送信息
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(ManageConnectServerThreads.getClientConnectServerThread(senderId).getSocket().getOutputStream());
        objectOutputStream.writeObject(message);
    }

    //获取一个学生成绩 用该生的名字和id
    public void getOneGradeByNameAndId(String senderName, String senderId, String studentName, String studentId) throws IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_GET_ONE_GRADE);
        message.setSender(senderName + " " + senderId);
        message.setContent(studentName + " " + studentId);
        message.setReceiver("服务器");
        message.setSendTime(simpleDateFormat.format(date));
        System.out.println("姓名为: " + studentName + " 学号为:" + studentId + " 的成绩查询\t\t" + simpleDateFormat.format(date));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(ManageConnectServerThreads.getClientConnectServerThread(senderId).getSocket().getOutputStream());
        objectOutputStream.writeObject(message);
    }

    //私聊函数
    @Override
    public void sendMessageToOne(String senderName, String senderId, String personId, String personName, String content) throws IOException {
        //代理模式的运用 虽然没有做其他处理 但是做到了解耦
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        System.out.println("对：" + personName + " 账号为：" + personId + " 的私聊\t\t" + simpleDateFormat.format(date));
        System.out.println("\t" + content);
        messageService.sendMessageToOne(senderName, senderId, personId, personName, content);
    }

    @Override
    public void chatClass(String personName, String personId, String classId, String content, String MessageType) throws IOException {
        messageService.chatClass(personName, personId, classId, content, MessageType);
    }

    @Override
    public void quitMessage(String senderName, String senderId) throws IOException {
        messageService.quitMessage(senderName,senderId);
    }

    //    @Override
    public void getClassGradeRank(String personName, String personId, String classId, String type) throws IOException {
        // messageService.getClassGradeRank(personName,personId,classId,type);
    }

    //转型函数
    @Override
    public void getStateById(String senderName, String id) throws IOException {
        System.out.println("进入教师菜单，正在进行user类转换->teacher");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_GET_STATE_BY_ID);
        message.setReceiver("服务器");
        message.setContent("teacher");
        message.setSendTime(simpleDateFormat.format(date));
        message.setSender(senderName + " " + id);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(ManageConnectServerThreads.getClientConnectServerThread(id).getSocket().getOutputStream());
        objectOutputStream.writeObject(message);
    }


    //封装班级聊天函数 变成和班级学生聊天
    public void chatWithClassStudents(String teacherName, String teacherId, String classId, String content) throws IOException {
        chatClass(teacherName, teacherId, classId, content, MessageType.MESSAGE_TO_CLASS_STUDENT_MES);
    }

    //封装班级聊天函数 变成和班级家长聊天
    public void chatWithGuardians(String teacherName, String teacherId, String classId, String content) throws IOException {
        chatClass(teacherName, teacherId, classId, content, MessageType.MESSAGE_TO_CLASS_GUARDIANS_MES);
    }

    //发送成绩给学生和他家长
    public void sendOneGradeToOne(String teacherName, String teacherId, String studentName, String studentId) throws IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_SEND_ONE_SCORE);
        message.setSender(teacherName + " " + teacherId);
        message.setContent(studentName + " " + studentId);
        message.setReceiver(studentName + " " + studentId);
        message.setSendTime(simpleDateFormat.format(date));
        System.out.println("发送 : " + studentName + " :" + studentId + " 的成绩\t\t" + simpleDateFormat.format(date));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(ManageConnectServerThreads.getClientConnectServerThread(teacherId).getSocket().getOutputStream());
        objectOutputStream.writeObject(message);
    }

    //给全班学生和家长发成绩
    public void sendGradesToAll(String teacherName, String teacherId, String classId) throws IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_SEND_ALL_SCORE);
        message.setSender(teacherName + " " + teacherId);
        message.setReceiver(classId);
        message.setContent(classId);
        message.setSendTime(simpleDateFormat.format(date));
        System.out.println("发送 " + classId + " 给全班\t\t" + simpleDateFormat.format(date));
        //从管理线程类中取出对应的线程对象发送信息
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(ManageConnectServerThreads.getClientConnectServerThread(teacherId).getSocket().getOutputStream());
        objectOutputStream.writeObject(message);
    }

    //获取班级消息
    public void getClassInfo(String teacherName, String teacherId, String classId) throws IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        System.out.println(teacherName + " 请求获取 " + classId + " 班级详情\t\t" + simpleDateFormat.format(date));
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_GET_CLASS_INFO);
        message.setSender(teacherName + " " + teacherId);
        message.setReceiver("服务器");
        message.setContent(classId);
        message.setSendTime(simpleDateFormat.format(date));

        //从管理线程类中取出对应的线程对象发送信息
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(ManageConnectServerThreads.getClientConnectServerThread(teacherId).getSocket().getOutputStream());
        objectOutputStream.writeObject(message);
    }
}
