package Service;

import Common.Message;
import Common.MessageType;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

//单独处理和大家共有的有关的消息 转发之类的
@SuppressWarnings({"all"})
public class MessageService implements MessageServiceInterface {

    @Override
    public void sendMessageToOne(String senderName, String senderId, String personId, String personName, String content) throws IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        Message message = new Message();

        message.setMesType(MessageType.MESSAGE_COMMON_MES);
        message.setSender(senderName + " " + senderId);
        //将接收对象的具体消息封装在接收者的数据包用空格切割
        message.setReceiver(personName + " " + personId);
        message.setContent(content);
        message.setSendTime(simpleDateFormat.format(date));

        ObjectOutputStream oos = new ObjectOutputStream(ManageConnectServerThreads.getClientConnectServerThread(senderId).getSocket().getOutputStream());
        oos.writeObject(message);
    }

    //和班级聊天函数
    @Override
    public void chatClass(String teacherName, String teacherId, String classId, String content, String MessageType) throws IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        System.out.println("对 " + classId + " 班级发起的对话\t\t" + simpleDateFormat.format(date));
        Message message = new Message();

        message.setReceiver(classId);
        message.setMesType(MessageType);
        message.setSendTime(simpleDateFormat.format(date));
        message.setSender(teacherName + " " + teacherId);
        message.setContent(content);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(ManageConnectServerThreads.getClientConnectServerThread(teacherId).getSocket().getOutputStream());
        objectOutputStream.writeObject(message);
    }

    @Override
    public void quitMessage(String senderName, String senderId) throws IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_CLIENT_EXIT);
        message.setSender(senderName + " " + senderId);//指定哪个客户端退出
        message.setReceiver("服务器");
        message.setContent("");
        message.setSendTime(simpleDateFormat.format(date));

        //名字实在是太长了裂开来
        ObjectOutputStream oos = new ObjectOutputStream(ManageConnectServerThreads.getClientConnectServerThread(senderId).getSocket().getOutputStream());
        oos.writeObject(message);
        System.out.println("你在 " + simpleDateFormat.format(date) + " 下线了");
        System.exit(0);
    }

    //根据id获得成绩
    public void getClassGradeRank(String personName, String personId, String classId, String type, String studentName, String studentId) throws IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        if (type.equals("ClassTotalRank") || type.equals("ClassEnglishRank") || type.equals("ClassMathRank") || type.equals("ClassChineseRank")) {
            System.out.println("对 " + classId + "班级的排名查询\t\t" + simpleDateFormat.format(date));
        } else if (type.equals("OneClassTotalRank") || type.equals("OneClassEnglishRank") || type.equals("OneClassChineseRank") || type.equals("OneClassMathRank") ||
                type.equals("OneGradeTotalRank") || type.equals("OneGradeEnglishRank") || type.equals("OneGradeChineseRank") || type.equals("OneGradeMathRank")) {
            System.out.println("对 " + studentName + "的排名查询\t\t" + simpleDateFormat.format(date));
        } else {
            System.out.println("对年段的排名查询\t\t" + simpleDateFormat.format(date));
        }
        Message message = new Message();
        message.setReceiver("服务器");
        message.setMesType(MessageType.MESSAGE_GET_RANK);
        message.setSendTime(simpleDateFormat.format(date));
        message.setSender(personName + " " + personId);
        message.setContent(classId + " " + type + " " + studentName + " " + studentId);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(ManageConnectServerThreads.getClientConnectServerThread(personId).getSocket().getOutputStream());
        objectOutputStream.writeObject(message);
    }

    //绑定消息包 目前只有孩子和监护人用
    public void bindUser(String senderName, String senderId, String type, String personName, String personId, String classId) throws IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String content = type + " " + personName + " " + personId + " " + classId + " ";

        Message message = new Message();
        message.setContent(content);
        message.setReceiver("服务器");
        message.setSendTime(simpleDateFormat.format(date));
        message.setSender(senderName + " " + senderId);
        message.setMesType(MessageType.MESSAGE_BIND);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(ManageConnectServerThreads.getClientConnectServerThread(senderId).getSocket().getOutputStream());
        objectOutputStream.writeObject(message);
    }
}
