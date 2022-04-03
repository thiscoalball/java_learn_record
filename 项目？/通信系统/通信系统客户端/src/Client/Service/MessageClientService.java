package Client.Service;

import qqCommon.Message;
import qqCommon.MessageType;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

//该类对象提供和消息相关的服务方法
public class MessageClientService {

    public void setMessageToOne(String senderId,String content,String receiverId) throws IOException {
        SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_COMON_MES);
        message.setSender(senderId);
        message.setReceiver(receiverId);
        message.setContent(content);
        message.setSendTime(simpleDateFormat.format(date));

        System.out.println("你对 " + receiverId + " 说：" + content + "\t\t\t" + simpleDateFormat.format(date) );
        //发送给服务端
        ObjectOutputStream oos = new ObjectOutputStream(ManageConnectServerThread.getClientConnectServerThread(senderId).getSocket().getOutputStream());
        oos.writeObject(message);
    }

    public void sendMessageToAll(String content,String senderId) throws IOException {
        SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_TOALL_MES);
        message.setSender(senderId);
        message.setContent(content);
        message.setSendTime(simpleDateFormat.format(date));

        System.out.println("你对大家说：" + content + "\t\t\t" + simpleDateFormat.format(date));
        //发送给服务端
        ObjectOutputStream oos = new ObjectOutputStream(ManageConnectServerThread.getClientConnectServerThread(senderId).getSocket().getOutputStream());
        oos.writeObject(message);
    }
}
