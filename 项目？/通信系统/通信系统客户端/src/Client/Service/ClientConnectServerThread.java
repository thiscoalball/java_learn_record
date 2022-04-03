package Client.Service;

import qqCommon.Message;
import qqCommon.MessageType;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;


public class ClientConnectServerThread extends Thread {
    //带上Socket
    private Socket socket;
    public static String address = "C:\\Users\\czy\\Desktop\\Java学习\\项目？" +
            "\\通信系统\\接收的文件\\";
    public ClientConnectServerThread(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        //因为要持续和服务器通信 需要while循环
        while (true) {
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) ois.readObject();
                //判断接收的message类型然后做业务处理

                //如果读取的是 返回的在线服务列表
                if (message.getMesType().equals(MessageType.MESSAGE_RET_ONLINE_FRIEND)) {
                    //取出在线列表消息
                    String[] onlineUser = message.getContent().split(" ");
                    System.out.println("-------------当前在线用户列表如下-------------");
                    for (int i = 0; i < onlineUser.length; i++) {
                        System.out.println("用户： " + onlineUser[i]);
                    }
                    //如果是私聊消息
                } else if (message.getMesType().equals(MessageType.MESSAGE_COMON_MES)) {
                    System.out.println("用户 " + message.getSender() + " 对你说:" + message.getContent());
                    System.out.println("\t\t\t\t\t\t" + message.getSendTime());

                    //如果是群发消息
                } else if (message.getMesType().equals(MessageType.MESSAGE_TOALL_MES)) {
                    System.out.println("用户 " + message.getSender() + " 对大家说：" + message.getContent());
                    System.out.println("\t\t\t\t\t\t" + message.getSendTime());

                    //如果是文件消息
                } else if (message.getMesType().equals(MessageType.MESSAGE_FILE_MES)) {
                    System.out.println("用户 "+message.getSender() +" 给你发送了文件： "+ message.getSrc());
                    System.out.println("\t\t\t\t\t\t" + message.getSendTime());

                    //这里可以修改保存本地文件的位置
                    FileOutputStream fileOutputStream = new FileOutputStream
                            (address + message.getDest());
                    fileOutputStream.write(message.getFileBytes());
                    fileOutputStream.close();
                    System.out.println("保存文件成功");

                } else {
                    System.out.println("暂不处理");
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}