package Service;

import Common.Message;
import Common.MessageType;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

//完成学生的创建等功能
public class StudentClientService implements MessageServiceInterface, getStateById {
    private MessageService messageService = new MessageService();

    public void getGrade(String studentName, String studentId) throws IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_GET_ONE_GRADE);
        message.setSender(studentName + " " + studentId);
        message.setContent(studentName + " " + studentId);
        message.setReceiver("服务器");
        message.setSendTime(simpleDateFormat.format(date));
        System.out.println("查询自己的成绩\t\t\t\t" + simpleDateFormat.format(date));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(ManageConnectServerThreads.getClientConnectServerThread(studentId).getSocket().getOutputStream());
        objectOutputStream.writeObject(message);

    }


    @Override
    public void sendMessageToOne(String senderName, String senderId, String personId, String personName, String content) throws IOException {
        //代理模式的运用 虽然没有做其他处理 但是做到了解耦
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        System.out.println("对姓名为：" + personName + " 账号为：" + personId + " 的私聊\t\t" + simpleDateFormat.format(date));
        System.out.println("\t" + content);
        messageService.sendMessageToOne(senderName, senderId, personId, personName, content);
    }

    public void chatWithClassmates(String personName, String personId, String classId, String content) throws IOException {
        chatClass(personName, personId, classId, content, MessageType.MESSAGE_TO_CLASS_STUDENT_MES);
    }

    //和班级聊天
    @Override
    public void chatClass(String personName, String personId, String classId, String content, String MessageType) throws IOException {
        messageService.chatClass(personName, personId, classId, content, MessageType);
    }

    //退出
    @Override
    public void quitMessage(String senderName, String senderId) throws IOException {
        messageService.quitMessage(senderName, senderId);
    }

    @Override
    public void getStateById(String senderName, String id) throws IOException {
        System.out.println("进入学生菜单，正在进行user类转换->student");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_GET_STATE_BY_ID);
        message.setReceiver("服务器");
        message.setContent("student");
        message.setSendTime(simpleDateFormat.format(date));
        message.setSender(senderName + " " + id);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(ManageConnectServerThreads.getClientConnectServerThread(id).getSocket().getOutputStream());
        objectOutputStream.writeObject(message);
    }

    public void bindGuardian(String senderName, String senderId, String guardianName, String guardianId) throws IOException {
        messageService.bindUser(senderName, senderId, "guardian", guardianName, guardianId, "");
    }
}
