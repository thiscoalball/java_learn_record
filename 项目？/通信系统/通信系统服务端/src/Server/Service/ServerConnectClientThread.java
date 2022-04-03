package Server.Service;

import qqCommon.Message;
import qqCommon.MessageType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

//该类的对象和某个客户端保持通信
public class ServerConnectClientThread extends Thread {
    private Socket socket;
    private String userId;//连接到这个服务端的用户id
    ArrayList<Message> messages = new ArrayList<>();

    public ServerConnectClientThread(Socket socket, String userId) {
        this.socket = socket;
        this.userId = userId;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        System.out.println("服务器与客户端：" + userId + " 成功建立连接" + "\t\t\t" + simpleDateFormat.format(date));
        while (true) {
            date = new Date();

            try {

                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) ois.readObject();//因为发过来的数据是Message类型
                //根据message的类型做响应的业务处理
                //如果客户端要求的是拉取在线用户列表
                if (message.getMesType().equals(MessageType.MESSAGE_GET_ONLINE_FRIEND)) {
                    System.out.println("用户：" + message.getSender() + " 请求在线用户列表" + "\t\t\t\t" + simpleDateFormat.format(date));

                    String onlineUser = ManageConnectClientThreads.getOnlineUser();

                    Message message1 = new Message();
                    message1.setMesType(MessageType.MESSAGE_RET_ONLINE_FRIEND);
                    message1.setContent(onlineUser);
                    message1.setSender(message.getSender());

                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(message1);

                    //如果客户端发的是私聊消息包
                } else if (message.getMesType().equals(MessageType.MESSAGE_COMON_MES)) {
                    //根据message获取recevierId 得到对应的线程
                    ServerConnectClientThread serverConnectClientThread = ManageConnectClientThreads.getServerConnectClientThread(message.getReceiver());
                    //检测该用户是否在线
                    if (serverConnectClientThread != null) {
                        //得到线程中对应的socket进行转发
                        ObjectOutputStream oos = new ObjectOutputStream(serverConnectClientThread.getSocket().getOutputStream());
                        oos.writeObject(message);//如果用户不存在可以把消息保存到数据库实现离线留言
                        System.out.println("用户:" + message.getSender() + " 发消息给用户:" + message.getReceiver() + "\t\t\t\t" + message.getSendTime());
                        //不存在的话将当前消息存入离线集合
                    } else {
                        //如果用户不在线
                        System.out.println(message.getReceiver() + " 不在线 已将消息存入离线消息 \t\t\t" + message.getSendTime());
                        messages.add(message);
                        QQServer.setOffLineDb(message.getReceiver(), messages);
                    }
                    //如果客户端发送的是群聊消息包
                } else if (message.getMesType().equals(MessageType.MESSAGE_TOALL_MES)) {
                    //遍历集合 排除自己 然后取出集合里的线程 取出线程的 socket然后发送
                    HashMap<String, ServerConnectClientThread> hm = ManageConnectClientThreads.getHm();
                    System.out.println("用户 " + message.getSender() + " 群发了一条消息\t\t\t\t" + message.getSendTime());
                    Iterator<String> iterator = hm.keySet().iterator();
                    while (iterator.hasNext()) {
                        String onLineId = iterator.next();
                        //排除发消息的用户
                        if (!onLineId.equals(message.getSender())) {
                            ObjectOutputStream oos = new ObjectOutputStream(hm.get(onLineId).getSocket().getOutputStream());
                            oos.writeObject(message);
                        }
                    }

                    //如果客户端发送的是传输文件消息包
                } else if (message.getMesType().equals(MessageType.MESSAGE_FILE_MES)) {
                    //根据message获取recevierId 得到对应的线程
                    ServerConnectClientThread serverConnectClientThread = ManageConnectClientThreads.getServerConnectClientThread(message.getReceiver());
                    //得到线程中对应的socket进行转发
                    if (serverConnectClientThread != null) {
                        ObjectOutputStream oos = new ObjectOutputStream(serverConnectClientThread.getSocket().getOutputStream());
                        oos.writeObject(message);
                        System.out.println("用户:" + message.getSender() + " 发文件给用户:" + message.getReceiver() + "\t\t\t\t" + message.getSendTime());
                    } else {
                        System.out.println(message.getReceiver() + " 不在线 已将消息存入离线消息 \t\t\t" + message.getSendTime());
                        messages.add(message);
                        QQServer.setOffLineDb(message.getReceiver(), messages);
                    }
                }
                //如果客户端发送的是退出消息包
                else if (message.getMesType().equals(MessageType.MESSAGE_CLIENT_EXIT)) {
                    System.out.println("用户:" + message.getSender() + " 退出系统\t\t\t\t\t\t" + message.getSendTime());
                    //将客户端对应的线程从集合中删除
                    ManageConnectClientThreads.removeServerConnectClientThread(message.getSender());
                    socket.close();
                    break;
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
}

