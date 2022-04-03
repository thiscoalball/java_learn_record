package Client.Service;

import qqCommon.Message;
import qqCommon.MessageType;
import qqCommon.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

//完成用户的登录验证注册等功能
public class UserClientService {
    //创建User对象 因为类里很常用到所以直接做成成员
    private User u = new User();
    //同理 常用 直接做
    private Socket socket = null;
    private String address = "192.168.43.180";//服务器的网络地址

    //验证该用户是否合法
    public boolean checkUser(String userId, String pwd) throws IOException, ClassNotFoundException {
        u.setUserId(userId);
        u.setPwd(pwd);
        //连接到服务器发送u对象   //192.168.43.180
        socket = new Socket(address, 9999);
        //得到outputStream对象
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(u);

        //读取服务器回复
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Message ms = (Message) ois.readObject();

        //如果服务器回复成功
        if (ms.getMesType().equals(MessageType.MESSAGE_LOGING_SUCCEED)) {
            //成功了后创建一个和服务器端保持通信的线程--->创建一个线程类ClientConnectServerThread
            ClientConnectServerThread clientConnectServerThread = new ClientConnectServerThread(socket);
            //启动客户端线程
            clientConnectServerThread.start();
            //放入集合中管理 因为后面要多用户发送
            ManageConnectServerThread.addConnectServerThread(userId, clientConnectServerThread);
            return true;
        } else {
            //登陆失败不能启动线程 将socket关闭
            socket.close();
            return false;
        }
    }

    //向服务器端请求在线用户列表
    public void onlineFriendList() throws IOException {
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_GET_ONLINE_FRIEND);
        message.setSender(u.getUserId());
        //从管理线程类里  得到线程类  从线程类  得到Socket  从Socket获得getOutputStream()
        ObjectOutputStream oos = new ObjectOutputStream
                (ManageConnectServerThread.getClientConnectServerThread
                        (u.getUserId()).getSocket().getOutputStream());
        oos.writeObject(message);
    }

    //编写方法退出客户端并给服务器发送退出系统的对象 让服务器关闭socket线程
    public void logout() throws IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_CLIENT_EXIT);
        message.setSender(u.getUserId());//指定哪个客户端退出
        message.setSendTime(simpleDateFormat.format(date));

        //名字实在是太长了裂开来
        ObjectOutputStream oos = new ObjectOutputStream
                (ManageConnectServerThread.getClientConnectServerThread
                        (u.getUserId()).getSocket().getOutputStream());

        oos.writeObject(message);
        System.out.println("你在 " + simpleDateFormat.format(date) + " 下线了");
        System.exit(0);
    }
}