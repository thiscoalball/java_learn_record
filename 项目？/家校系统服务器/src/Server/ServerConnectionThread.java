package Server;

import Common.Message;
import Common.MessageType;
import domain.User;
import service.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

@SuppressWarnings({"all"})
//该类声明的对象和某个客户端保持通信的
public class ServerConnectionThread extends Thread {
    private Socket socket;
    private String personId;//连接到这个服务端的用户id

    private AllGradesService allGradesService = new AllGradesService();
    private UserService userService = new UserService();
    private StudentService studentService = new StudentService();
    private GuardianService guardianService = new GuardianService();
    private ObjectInputStream ois = null;
    private Vector<Message> messages = null;
    private Iterator<Message> iterator = null;
    private Message message1 = null;
    public ServerConnectionThread(Socket socket, String personId) {
        this.personId = personId;
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    //检查消息用户
    public void checkIsOnline(Message message) throws IOException {
        if (message == null) {
            return;
        }
        String[] s = message.getContent().split(" ");
        //当用户不在线时
        if (s[0].equals("用户不在线")) {
            message.setContent("用户 " + s[2] + " 不在线 已将数据存入离线消息库");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(message);
            //当查找不到用户时
        } else if (message.getContent().equals("查找不到该用户")) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(message);
            //正确情况
        } else {
            //解包
            String[] receiverInfo = message.getReceiver().split(" ");
            String receiverId = receiverInfo[1];
            OutputStream outputStream = ManageConnectionThreads.getServerConnectionThread(receiverId).getSocket().getOutputStream();
            new ObjectOutputStream(outputStream).writeObject(message);
            //得到在线用户列表 写到这里我突然发现 我的在线用户列表忘了做成数据库的表了 但是算了 改不动了，不想改了
        }
    }

    //遍历消息集合
    public void sendToAll(Vector<Message> messages) throws SQLException, IOException {
        iterator = messages.iterator();
        while (iterator.hasNext()) {
            Message message1 = iterator.next();
            Message getOneUserToSendMessage = userService.getOneUserToSendMessage(message1);
            checkIsOnline(getOneUserToSendMessage);
        }
    }

    //退出
    public void quit(Message message) throws IOException {
        String personName = message.getSender().split(" ")[0];
        String personId = message.getSender().split(" ")[1];
        System.out.println("姓名:" + personName + "  账号 " + personId + " 下线\t\t\t" + message.getSendTime());
        ManageConnectionThreads.removeServerConnectClientThread(personId);
        socket.close();
    }

    //创建用户
    public Message create(Message message) throws SQLException {
        //这里先检测user表中是否有账号一样的 有的话就不能创建
        Message message1 = new Message();
        Message message2 = userService.idIsExist(message);
        if(message2.getContent().equals("存在账号一样的人 无法注册")){
            return message2;
        }
        //1:对应的表部分
        //调用一个 创建工厂 实例化对应的teacher student guardian
        Message userByType = CreateFactory.createUserByType(message);
        //2:user表的部分
        Message user = userService.createUser(message);
        if(userByType!=null && user!=null){
            message1.setSender("服务器");
            message1.setContent("创建成功");
            message1.setSendTime(message.getSendTime());
            message1.setReceiver(user.getReceiver());
            message1.setMesType(MessageType.MESSAGE_COMMON_MES);
            return message1;
        } else {
            message1.setSender("服务器");
            message1.setContent("创建失败");
            message1.setSendTime(message.getSendTime());
            message1.setReceiver(user.getReceiver());
            message1.setMesType(MessageType.MESSAGE_COMMON_MES);
            return message1;
        }
    }

    public Message bindUser(Message message) throws SQLException {
        //接收到消息 先去user表判断有无存在
        Message message1 = new Message();
        String[] info = message.getContent().split(" ");
        String type = info[0];
        String userName = info[1];
        String userId = info[2];
        User user = userService.isExist(userName, userId);
        //如果不存在该用户
        if(user == null){
            message1.setReceiver(message.getSender());
            message1.setSender("服务器");
            message1.setContent("该用户不存在 无法绑定");
            message1.setSendTime(message.getSendTime());
            message1.setMesType(MessageType.MESSAGE_COMMON_MES);
            return message1;
        }
        //如果绑定的是孩子
        if(type.equals("child")){
            //检测一下这个user是不是孩子
            //如果不是
            if(!user.getState().equals("学生")){
                message1.setReceiver(message.getSender());
                message1.setSender("服务器");
                message1.setContent("该用户身份不是孩子 无法绑定");
                message1.setSendTime(message.getSendTime());
                message1.setMesType(MessageType.MESSAGE_COMMON_MES);
                return message1;
            }
            //这里身份就对了 进行绑定
            return guardianService.bindChild(message);
        }
        //如果绑定的是监护人
        if(type.equals("guardian")){
            //检测一下是不是监护人
            if(!user.getState().equals("监护人")){
                message1.setReceiver(message.getSender());
                message1.setSender("服务器");
                message1.setContent("该用户身份不是监护人 无法绑定");
                message1.setSendTime(message.getSendTime());
                message1.setMesType(MessageType.MESSAGE_COMMON_MES);
                return message1;
            }
            //身份正确进行绑定
            return studentService.bindGuardian(message);
        }
        return null;
    }
    @Override
    public void run() {
        //退出消息包标志位
        boolean breakWhile = false;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        System.out.println("服务器与客户端：" + personId + "  成功建立连接\t" + simpleDateFormat.format(date));
        while (true) {
            if (breakWhile) {
                break;
            }
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = new Date();

            try {
                ois = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) ois.readObject();//因为发过来的数据是Message类型
                ObjectOutputStream objectOutputStream = null;
                switch (message.getMesType()) {
                    //收到的消息包是 获取全班成绩
                    case MessageType.MESSAGE_GET_CLASS_GRADE:
                        Message allGradesByClassIdMessage = allGradesService.getAllGradesByClassId(message);
                        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                        objectOutputStream.writeObject(allGradesByClassIdMessage);
                        System.out.println();
                        break;
                    //收到的消息包是 获取指定学生成绩
                    case MessageType.MESSAGE_GET_ONE_GRADE:
                        Message oneGradeByIdAndNameMessage = allGradesService.getOneGradeByIdAndName(message);
                        oneGradeByIdAndNameMessage.setMesType(MessageType.MESSAGE_GET_ONE_GRADE);
                        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                        objectOutputStream.writeObject(oneGradeByIdAndNameMessage);
                        System.out.println();
                        break;
                    //收到的消息包是 正常单人聊天
                    case MessageType.MESSAGE_COMMON_MES:
                        Message getOneUserToSendMessage = userService.getOneUserToSendMessage(message);
                        checkIsOnline(getOneUserToSendMessage);
                        System.out.println();
                        break;
                    //收到的消息包是 根据职业转型
                    case MessageType.MESSAGE_GET_STATE_BY_ID:
                        //利用工厂模式实例化对象
                        User user = GetStateByIdFactory.getStateById(message);
                        Message stateById = userService.getStateById(user, message);
                        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                        objectOutputStream.writeObject(stateById);
                        System.out.println();
                        break;
                    //收到的消息包是 和全班学生聊天
                    case MessageType.MESSAGE_TO_CLASS_STUDENT_MES:
                        messages = studentService.chatWithClassStudent(message);
                        sendToAll(messages);
                        System.out.println();
                        break;
                    //收到的消息包是 和全班家长聊天
                    case MessageType.MESSAGE_TO_CLASS_GUARDIANS_MES:
                        messages = guardianService.chatWithClassGuardians(message);
                        sendToAll(messages);
                        System.out.println();
                        break;
                    //收到的消息包是 发送成绩给指定的学生家长
                    case MessageType.MESSAGE_SEND_ONE_SCORE:
                        messages = studentService.getGuardianIdAndStudentIdByStudentId(message);
                        sendToAll(messages);
                        System.out.println();
                        break;
                    //收到的消息包是 发送全班成绩给全班学生和家长
                    case MessageType.MESSAGE_SEND_ALL_SCORE:
                        //获取全班成绩的消息包
                        String teacherName = message.getSender().split(" ")[0];
                        message = allGradesService.getAllGradesByClassId(message);
                        //这里将消息包进行加工处理 变成向学生和家长发送 获取学生和家长的id 放入
                        //由于上一个函数改变了发送方 所以这里改回来 这些函数就充分体现了个别函数设计时的不合理
                        //导致需要这种奇奇怪怪的修改来避免bug
                        message.setSender(teacherName);
                        messages = studentService.chatWithClassStudent(message);
                        Vector<Message> messages1 = guardianService.chatWithClassGuardians(message);
                        messages.addAll(messages1);
                        Iterator<Message> iterator = messages.iterator();
                        while (iterator.hasNext()) {
                            Message next = iterator.next();
                            next.setMesType(MessageType.MESSAGE_GET_CLASS_GRADE);
                        }
                        sendToAll(messages);
                        System.out.println();
                        break;
                    //获取排名
                    case MessageType.MESSAGE_GET_RANK:
                        Message rank = allGradesService.getRank(message);
                        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                        objectOutputStream.writeObject(rank);
                        System.out.println();
                        break;
                    //获取班级人员对应信息
                    case MessageType.MESSAGE_GET_CLASS_INFO:
                        Message classInfo = studentService.getClassInfo(message);
                        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                        objectOutputStream.writeObject(classInfo);
                        System.out.println();
                        break;
                    //退出消息包
                    case MessageType.MESSAGE_CLIENT_EXIT:
                        quit(message);
                        System.out.println();
                        //这里报错是因为没有退出主循环wile 这里的break只是退出了switch
                        breakWhile = true;
                        break;
                    //创建成员消息包
                    case MessageType.MESSAGE_CREATE:
                        message1 = create(message);
                        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                        objectOutputStream.writeObject(message1);
                        System.out.println();
                        break;
                    //绑定成员消息包
                    case MessageType.MESSAGE_BIND:
                        message1 = bindUser(message);
                        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                        objectOutputStream.writeObject(message1);
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
