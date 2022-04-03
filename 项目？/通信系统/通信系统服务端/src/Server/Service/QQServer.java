package Server.Service;

import qqCommon.Message;
import qqCommon.MessageType;
import qqCommon.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

//这里是服务器监听9999端口
public class QQServer {

    private ServerSocket ss = null;
    private Message message = new Message();
    private Socket socket = null;
    //创建一个集合(模拟数据库):如果是这些用户登录 就是合法的
    private static ConcurrentHashMap<String, User> validUser = new ConcurrentHashMap<>();

    //创建数据库存放离线的消息消息
    private static ConcurrentHashMap<String, ArrayList<Message>> offLineDb = new ConcurrentHashMap<>();

    static {
        validUser.put("100", new User("100", "123123"));
        validUser.put("200", new User("200", "123123"));
        validUser.put("300", new User("300", "123123"));
        validUser.put("张三", new User("张三", "123123"));
        validUser.put("李四", new User("李四", "123123"));
        validUser.put("王五", new User("王五", "123123"));
    }

    public static ConcurrentHashMap<String, ArrayList<Message>> getOffLineDb() {
        return offLineDb;
    }

    public static void setOffLineDb(String userId, ArrayList<Message> messageList) {

        offLineDb.put(userId, messageList);
    }



    //验证用户是否有效
    private boolean checkUser(String userId, String pwd) throws IOException {
        User user = validUser.get(userId);
        if (user == null) {
            System.out.println("id不存在");
            return false;
        }
        if (!user.getPwd().equals(pwd)) {
            System.out.println("密码错误");
            return false;
        }
        return true;
    }

    public QQServer() throws IOException, ClassNotFoundException {
        //端口可以写在配置文件中
        System.out.println("服务器在9999端口监听");
        ss = new ServerSocket(9999);
        new Thread(new SendNewsToAll()).start();

        //监听是循环的 不止监听一个对象
        while (true) {
            SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            socket = ss.accept();
            //得到socket关联的对象输入流
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            //因为是第一次读取 所以读取的一定是一个登录User对象
            User u = (User) ois.readObject();

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            if (checkUser(u.getUserId(), u.getPwd())) {
                //如果登录验证通过
                message.setMesType(MessageType.MESSAGE_LOGING_SUCCEED);
                //回复一个成功的消息对象
                oos.writeObject(message);
                //创建一个线程和客户端保持通信
                ServerConnectClientThread serverConnectClientThread = new ServerConnectClientThread(socket, u.getUserId());
                //开启这个线程
                serverConnectClientThread.start();
                //将线程对象放到集合中进行管理
                ManageConnectClientThreads.addConnectClientThread(u.getUserId(), serverConnectClientThread);

                //当用户登录成功时 判断离线消息库中是否有属于该用户的消息
                if (QQServer.getOffLineDb().get(u.getUserId()) != null) {
                    ArrayList<Message> messages = QQServer.getOffLineDb().get(u.getUserId());
                    //有消息 利用迭代器遍历
                    Iterator<Message> iterator = messages.iterator();
                    while (iterator.hasNext()) {
                        Message message =  iterator.next();
                        //发现消息包中对应的receiverId和 当前登录的 u 的 id是相同的 证明这个消息就是要发给u的
                        if(message.getReceiver().equals(u.getUserId())) {
                            oos = new ObjectOutputStream(serverConnectClientThread.getSocket().getOutputStream());
                            oos.writeObject(message);
                        }
                    }
                    QQServer.getOffLineDb().remove(u.getUserId());
                }

            } else {//登陆失败
                System.out.println("用户id：" + u.getUserId() + "密码为：" + u.getPwd() + " 的用户验证失败");
                message.setMesType(MessageType.MESSAGE_LOGING_FALSE);
                oos.writeObject(message);
                socket.close();
            }
        }
    }
}
