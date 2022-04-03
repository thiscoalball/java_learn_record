package Client.View;

import Client.Service.FileClientService;
import Client.Service.MessageClientService;
import Client.Service.UserClientService;
import Client.Util.Utility;
import com.sun.jndi.toolkit.url.Uri;

import java.awt.*;
import java.io.IOException;

public class qqView {


    private boolean loop = true;
    //用于接收键盘输入
    private String key = "";
    //用于登陆服务器注册用户
    private UserClientService userClientService = new UserClientService();
    //用于该对象用于传输消息
    private MessageClientService messageClientService = new MessageClientService();
    //用于该对象传输文件
    private FileClientService fileClientService = new FileClientService();
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new qqView().mainMenu();
    }


    //显示主菜单
    private void mainMenu() throws IOException, ClassNotFoundException {
        while (loop) {
            System.out.println("-------------欢迎登录通信系统-------------");
            System.out.println("               1 登录系统                ");
            System.out.println("               9 退出系统                ");
            System.out.println("----------------------------------------");


            System.out.println("请输入你的选择");
            key = Utility.readString(1);
            switch (key) {
                case "1":
                    System.out.println("请输入用户号:");
                    String userId = Utility.readString(50);
                    System.out.println("请输入用户密码");
                    String pwd = Utility.readString(50);
                    //这里就需要开始取服务端验证该用户是否合法了
                    //我们编写一个类 UserClientService 做用户登陆验证
                    if (userClientService.checkUser(userId, pwd)) {
                        //进入二级菜单
                        while (loop) {
                            System.out.println();
                            System.out.println("----------------用户：" + userId + "-------------");
                            System.out.println("               1 显示在线用户列表               ");
                            System.out.println("               2 群发消息               ");
                            System.out.println("               3 私聊消息               ");
                            System.out.println("               4 发送文件               ");
                            System.out.println("               9 退出系统               ");
                            System.out.println("-----------------------------------------");

                            key = Utility.readString(1);
                            switch (key) {
                                case "1":
                                    userClientService.onlineFriendList();
                                    break;
                                case "2":
                                    System.out.println("请输入想对大家说的话");
                                    String content1 = Utility.readString(100);
                                    //调用方法
                                    messageClientService.sendMessageToAll(content1,userId);
                                    break;
                                case "3":
                                    System.out.println("请输入在线的用户号：");
                                    String receiverId = Utility.readString(50);
                                    System.out.println("请输入聊天内容");
                                    String content = Utility.readString(100);
                                    messageClientService.setMessageToOne(userId,content,receiverId);

                                    break;
                                case "4":
                                    System.out.println("你想把文件发给谁(在线)：");
                                    String receiverId1 = Utility.readString(100);
                                    System.out.println("请输入发送文件的完整路径");
                                    String filePath = Utility.readString(100);
                                    System.out.println("请输入你要发送的文件名字");
                                    String name = Utility.readString(40);
                                    fileClientService.sendFileToOne(filePath,name,userId,receiverId1);
                                    break;
                                case "8":
                                    System.out.println("返回上级");
                                    break;
                                case "9":
                                    userClientService.logout();
                                    break;
                            }
                        }

                    } else {
                        System.out.println("登录失败了");
                    }
                    break;
                case "9":
                    loop = false;
                    break;
            }
        }
    }
}