package Service;

import Common.Message;
import Common.MessageType;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

//完成监护人的创建等功能
@SuppressWarnings({"all"})
public class GuardianClientService implements getStateById, MessageServiceInterface {
    private MessageService messageService = new MessageService();

    @Override
    public void getStateById(String senderName, String id) throws IOException {
        System.out.println("进入监护人菜单，正在进行user类转换->guardian");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_GET_STATE_BY_ID);
        message.setReceiver("服务器");
        message.setContent("guardian");
        message.setSendTime(simpleDateFormat.format(date));
        message.setSender(senderName + " " + id);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(ManageConnectServerThreads.getClientConnectServerThread(id).getSocket().getOutputStream());
        objectOutputStream.writeObject(message);
    }

    @Override
    public void sendMessageToOne(String senderName, String senderId, String personId, String personName, String content) throws IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        System.out.println("对：" + personName + " 的私聊\t\t" + simpleDateFormat.format(date));
        System.out.println("\t" + content);
        messageService.sendMessageToOne(senderName, senderId, personId, personName, content);
    }

    @Override
    public void chatClass(String personName, String personId, String classId, String content, String MessageType) throws IOException {
        messageService.chatClass(personName, personId, classId, content, MessageType);
    }

    @Override
    public void quitMessage(String senderName, String senderId) throws IOException {
        messageService.quitMessage(senderName, senderId);
    }

    public void chatGuardians(String guardianName, String guardianId, String classId, String content) throws IOException {
        chatClass(guardianName, guardianId, classId, content, MessageType.MESSAGE_TO_CLASS_GUARDIANS_MES);
    }

    //    @Override
    public void getClassGradeRank(String personName, String personId, String classId, String type) throws IOException {
        //messageService.getClassGradeRank(personName,personId,classId,type);
    }

    public void bindChild(String senderName, String senderId, String childName, String childId, String classId) throws IOException {
        messageService.bindUser(senderName, senderId, "child", childName, childId, classId);
    }
}
