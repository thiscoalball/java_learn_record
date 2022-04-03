package Server;

import Common.Message;
import Common.MessageType;
import domain.User;
import service.UserService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
@SuppressWarnings({"all"})
//服务器监听9999端口
public class SchoolHomeServer {

    private ServerSocket serverSocket = null;
    private Message message = new Message();
    private Socket socket = null;

    private UserService userService = new UserService();

    //验证数据库中是否有该用户的消息 有的话 那么其密码是否对应
    private User checkUser(String id, String pwd) throws SQLException {
        //调用用户服务层的函数从用户表 前往数据库的用户表查找该消息
        User user = userService.checkPerson(id, pwd);
        //如果查找不到这个用户
        if (user == null) {
            return null;
        }
        return user;
    }

    public SchoolHomeServer() throws IOException, ClassNotFoundException, SQLException {
        System.out.println("服务器在9090端口监听");
        serverSocket = new ServerSocket(9090);

        //循环监听 不止监听一个对象
        while (true) {
            SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            socket = serverSocket.accept();
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            //第一次读取的一定是一个用户消息 所以直接进行强转
            User user = (User) ois.readObject();
            //读取到后开启一个输出流准备向客户端发送
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            //读取到的用户信息进入数据库中验证（因为读取到的只有账号密码，这里我们进去验证
            //如果找到的话就返回该账号的完整信息
            User user2 = checkUser(user.getPersonId(), user.getPwd());
            if(user.getPersonId().equals("create")){
                message.setContent("SUCCESS");
                oos.writeObject(message);
                ServerConnectionThread serverConnectionThread = new ServerConnectionThread(socket, user.getPersonId());
                //开启这个线程
                serverConnectionThread.start();
                //将线程对象放到线程集合之中进行管理
                ManageConnectionThreads.addConnectClientThread(user.getPersonId(), serverConnectionThread);
                return;
            }
            if (user2!=null) {
                //回复一个成功登录的消息包对象
                System.out.println(user2.getState()+":"+user2.getName() + "  账号:"+user2.getPersonId()+"  上线\t\t\t"+simpleDateFormat.format(date));
                message.setMesType(MessageType.MESSAGE_LOGGING_SUCCEED);
                //并将数据库里完整的用户信息封装到消息包中打入输出流
                message.setUser(user2);
                //将消息包打入输出流
                oos.writeObject(message);
                //创建一个线程和客户端保持通信
                ServerConnectionThread serverConnectionThread = new ServerConnectionThread(socket, user.getPersonId());
                //开启这个线程
                serverConnectionThread.start();
                //将线程对象放到线程集合之中进行管理
                ManageConnectionThreads.addConnectClientThread(user.getPersonId(), serverConnectionThread);

                //用户登录成功时验证是否有离线消息 这个先不做 先把小功能实现再做这个
                //-----------离线消息转发部分--------------
                //-                                    -
                //-                                    -
                //---------------------------------------
            } else {
                System.out.println("用户：" + user.getPersonId() + " 账号密码不匹配 登陆失败\t"+simpleDateFormat.format(date));
                //登陆失败后返回一个登陆失败的消息包给客户端
                message.setMesType(MessageType.MESSAGE_LOGGING_FALSE);
                message.setContent("登陆失败 请检测你的账号密码是否正确");
                message.setSendTime(simpleDateFormat.format(date));
                oos.writeObject(message);
                socket.close();
            }
        }
    }
}
