package Service;

import Common.Message;
import Common.MessageType;
import domain.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

@SuppressWarnings({"all"})
public class ConnectServerThread extends Thread {
    private Socket socket;
    static public User user = null;

    public ConnectServerThread(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public static User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void chooseRank(Message message) {
        String type = message.getContent().split(" ")[0];
        if (message.getContent().equals("null")) {
            System.out.println("查无该生消息，请确认姓名和学号是否对应");
        } else {
            System.out.println(type + "排名如下");
        }
        switch (type) {
            case "班级总分":
                System.out.println("排名\t姓名\t\t学号\t\t语文\t\t数学\t\t英语\t\t总分");
                System.out.println(message.getContent().split(" ")[1]);
                break;
            case "班级语文":
                System.out.println("排名\t姓名\t\t学号\t\t语文");
                System.out.println(message.getContent().split(" ")[1]);
                break;
            case "班级数学":
                System.out.println("排名\t姓名\t\t学号\t\t数学");
                System.out.println(message.getContent().split(" ")[1]);
                break;
            case "班级英语":
                System.out.println("排名\t姓名\t\t学号\t\t英语");
                System.out.println(message.getContent().split(" ")[1]);
                break;
            case "年段总分":
                System.out.println("排名\t姓名\t\t学号\t\t班级\t\t语文\t\t数学\t\t英语\t\t总分");
                System.out.println(message.getContent().split(" ")[1]);
                break;
            case "年段语文":
                System.out.println("排名\t姓名\t\t学号\t\t班级\t\t语文");
                System.out.println(message.getContent().split(" ")[1]);
                break;
            case "年段数学":
                System.out.println("排名\t姓名\t\t学号\t\t班级\t\t数学");
                System.out.println(message.getContent().split(" ")[1]);
                break;
            case "年段英语":
                System.out.println("排名\t姓名\t\t学号\t\t班级\t\t英语");
                System.out.println(message.getContent().split(" ")[1]);
                break;
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) ois.readObject();
                switch (message.getMesType()) {
                    //获取班级成绩消息包
                    case MessageType.MESSAGE_GET_CLASS_GRADE:
                        if (message.getContent().equals("null")) {
                            System.out.println("您的输入有误或者本次考试成绩还未录入");
                            return;
                        }
                        System.out.println("-----------------------这次的成绩如下------------------------");
                        System.out.println("姓名\t\t账号\t\t\t班级\t\t英语\t\t\t数学\t\t\t语文");
                        System.out.println(message.getContent());
                        break;
                    //获取指定成员成绩消息包
                    case MessageType.MESSAGE_GET_ONE_GRADE:
                        if (message.getContent().equals("null")) {
                            System.out.println("您的输入有误或者本次考试成绩还未录入");
                            return;
                        }
                        System.out.println("----------------------该生的成绩如下---------------------");
                        System.out.println("姓名\t\t账号\t\t\t班级\t\t英语\t\t\t数学\t\t\t语文");
                        System.out.println(message.getContent());
                        break;
                    //指定成员聊天消息包
                    case MessageType.MESSAGE_COMMON_MES:
                        System.out.print(message.getSender().split(" ")[0] + " 对你说:" + message.getContent());
                        System.out.println("\t\t\t\t\t" + message.getSendTime());
                        break;
                    //user转型消息包
                    case MessageType.MESSAGE_GET_STATE_BY_ID:
                        System.out.println("转型完成\t\t" + message.getSendTime());
                        user = message.getUser();
                        break;
                    //全班学生聊天消息包
                    case MessageType.MESSAGE_TO_CLASS_STUDENT_MES:
                        if (message.getSender().equals("服务器")) {
                            return;
                        }
                        System.out.print(message.getSender().split(" ")[0] + ": " + message.getContent());
                        System.out.println("\t\t\t\t\t" + message.getSendTime());
                        break;
                    //全班学生的家长聊天消息包
                    case MessageType.MESSAGE_TO_CLASS_GUARDIANS_MES:
                        if (message.getSender().equals("服务器")) {
                            return;
                        }
                        System.out.print(message.getSender().split(" ")[0] + ": " + message.getContent());
                        System.out.println("\t\t\t\t\t" + message.getSendTime());
                        break;
                    //获取排名消息包
                    case MessageType.MESSAGE_GET_RANK:
                        if (message == null) {
                            System.out.println("本次考试成绩还未录入");
                            return;
                        }
                        //调用函数分类显示
                        chooseRank(message);
                        break;
                    //获取班级详情消息包
                    case MessageType.MESSAGE_GET_CLASS_INFO:
                        System.out.println("学生姓名\t\t学生账号\t\t家长姓名\t\t家长账号");
                        System.out.println(message.getContent());
                        break;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
