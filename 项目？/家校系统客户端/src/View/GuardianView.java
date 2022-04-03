package View;

import Service.ConnectServerThread;
import Service.GuardianClientService;
import Util.Utility;
import domain.Guardian;
import domain.User;

import java.io.IOException;

@SuppressWarnings({"all"})

public class GuardianView {
    private boolean loop = true;
    //用于接收键盘输入
    private String key = "";
    private ChooseRankView chooseRankView = new ChooseRankView();
    private GuardianClientService guardianClientService = new GuardianClientService();

    //转型函数
    private Guardian getStateById(User user) throws IOException, InterruptedException {
        guardianClientService.getStateById(user.getName(), user.getPersonId());
        Thread.sleep(300);
        return (Guardian) ConnectServerThread.getUser();
    }

    //向全班家长聊天
    private void chatWithClassGuardians(Guardian guardian) throws InterruptedException, IOException {
        System.out.println("请输入你要发送的话");
        String content = Utility.readString(400);
        guardianClientService.chatGuardians(guardian.getName(), guardian.getPersonId(), guardian.getClassId(), content);
        Thread.sleep(300);
    }

    //给指定人发送消息
    private void sendMessageToOne(Guardian guardian) throws IOException, InterruptedException {
        System.out.println("请输入接收方姓名");
        String personName = Utility.readString(10);
        System.out.println("请输入接收方账号");
        String personId = Utility.readString(10);
        System.out.println("请输入聊天内容:");
        String content = Utility.readString(50);
        guardianClientService.sendMessageToOne(guardian.getName(), guardian.getPersonId(), personId, personName, content);
        Thread.sleep(300);
    }

    //查询总成绩
    private void getAllRankScores(Guardian guardian) throws IOException, InterruptedException {
        //总成绩不需要具体孩子的姓名和id
        chooseRankView.chooseTotalMenu(guardian.getClassId(), guardian.getPersonId(), guardian.getName(), "", "");
    }

    //获取孩子的排名
    private void getChildRank(Guardian guardian) throws IOException, InterruptedException {
        chooseRankView.chooseOneMenu(guardian.getClassId(), guardian.getPersonId(), guardian.getName(), guardian.getChildName(), guardian.getChildId());
    }

    //退出消息包
    private void quit(Guardian guardian) throws IOException {
        guardianClientService.quitMessage(guardian.getName(), guardian.getPersonId());
    }

    //绑定孩子
    private void bindChild(Guardian guardian) throws IOException {
        System.out.println("请输入孩子姓名");
        String childName = Utility.readString(10);
        System.out.println("请输入孩子账号");
        String childId = Utility.readString(10);
        System.out.println("请输入孩子的班级号");
        String classId = Utility.readString(10);
        guardianClientService.bindChild(guardian.getName(), guardian.getPersonId(), childName, childId, classId);
    }

    //监护人主菜单
    public void guardianMenu(User user) throws IOException, InterruptedException {
        System.out.println("欢迎家长：" + user.getName());
        Guardian guardian = getStateById(user);
        //为家长进行转型
        while (loop) {
            System.out.println("-----------------请选择你要执行的操作-----------------");
            System.out.println("\t\t\t1 查询总成绩");
            System.out.println("\t\t\t2 查询孩子的成绩");
            System.out.println("\t\t\t3 向指定的成员发起聊天");
            System.out.println("\t\t\t4 向全班家长发起聊天");
            System.out.println("\t\t\t5 绑定孩子");
            System.out.println("\t\t\t9 退出 ");
            key = Utility.readString(1);
            switch (key) {
                case "1":
                    getAllRankScores(guardian);
                    break;
                case "2":
                    getChildRank(guardian);
                    break;
                case "3":
                    sendMessageToOne(guardian);
                    break;
                case "4":
                    chatWithClassGuardians(guardian);
                    break;
                case "5":
                    bindChild(guardian);
                    break;
                case "9":
                    quit(guardian);
                    break;
            }
        }
    }
}
