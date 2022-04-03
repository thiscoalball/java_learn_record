package Service;

import Common.Message;
import Common.MessageType;
import domain.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings({"all"})
//完成用户的登录验证注册等功能
public class UserClientService {
    private User user = new User();
    private Socket socket = null;
    private String address = "192.168.43.180";//服务器的网络地址

    //验证用户是否合法
    public User checkUser(String personId, String pwd) throws IOException, ClassNotFoundException {
        user.setPersonId(personId);
        user.setPwd(pwd);
        socket = new Socket(address, 9090);
        //得到outputStream对象
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        //发送
        oos.writeObject(user);
        //读取服务器回复 回复的一定是消息包 所以直接强转
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Message message = null;
        message = (Message) ois.readObject();

        //进行解析 如果登录成功
        if (message.getMesType().equals(MessageType.MESSAGE_LOGGING_SUCCEED)) {
            //解析出消息包中携带的个人的具体信息准备返回
            User user2 = message.getUser();
            //创建线程对象
            ConnectServerThread connectServerThread = new ConnectServerThread(socket);
            //启动线程
            connectServerThread.start();
            //放入集合中进行管理
            ManageConnectServerThreads.addConnectServerThread(personId, connectServerThread);
            return user2;
        } else {
            socket.close();
            return null;
        }
    }

    //创建用户 这里搭建一个临时线程和服务器通信
    public void createUser(String type,String name,String id,String pwd, String classId) throws IOException, ClassNotFoundException {
        //-----------------------------发送通信请求部分---------------------------------------
        user.setPersonId("create");
        socket = new Socket(address, 9090);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        //发送
        oos.writeObject(user);
        //----------------------------------------------------------------------------------


        //----------------------------读取服务器回复部分---------------------------------------
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Message message = null;
        message = (Message) ois.readObject();
        //-----------------------------------------------------------------------------------

            //----------------------------对服务器回复进行处理--------------------------------------
        if(message.getContent().equals("SUCCESS")) {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            ConnectServerThread connectServerThread = new ConnectServerThread(socket);
            //启动线程
            connectServerThread.start();
            //放入集合中进行管理
            ManageConnectServerThreads.addConnectServerThread("create", connectServerThread);
            //-----------------------------------------------------------------------------------

            //-----------------------------将要创建的信息传给服务器-----------------------------------
            Message message1 = new Message();
            message1.setSendTime(simpleDateFormat.format(date));
            message1.setReceiver("服务器");
            message1.setSender("临时线程");
            message1.setContent(type + " " + name + " " + id + " " + pwd + " " + classId);
            message1.setMesType(MessageType.MESSAGE_CREATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(ManageConnectServerThreads.getClientConnectServerThread("create").getSocket().getOutputStream());
            objectOutputStream.writeObject(message1);
            //---------------------------------------------------------------------------------------
        }
    }
}
