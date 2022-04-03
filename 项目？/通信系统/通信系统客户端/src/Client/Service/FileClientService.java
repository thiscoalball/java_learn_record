package Client.Service;


import qqCommon.Message;
import qqCommon.MessageType;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

//该类完成文件传输的服务
public class FileClientService {

    public void sendFileToOne(String src ,String dest, String senderId, String receiverId) throws IOException {

        SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_FILE_MES);
        message.setSender(senderId);
        message.setReceiver(receiverId);
        message.setSrc(src);
        message.setDest(dest);
        message.setSendTime(simpleDateFormat.format(date));

        FileInputStream fileInputStream = null;
        byte[] fileBytes = new byte[(int) (new File(src).length())];
        fileInputStream = new FileInputStream(src);
        //将src文件读入到一个字节数组当中并传入自己的消息里
        fileInputStream.read(fileBytes);
        message.setFileBytes(fileBytes);

        fileInputStream.close();
        System.out.println("你向用户 " + receiverId + " 发送文件");

        //发送文件
        ObjectOutputStream oos = new ObjectOutputStream(ManageConnectServerThread.getClientConnectServerThread(senderId).getSocket().getOutputStream());
        oos.writeObject(message);
    }
}
