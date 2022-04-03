package View;

import Service.ConnectServerThread;
import Service.StudentClientService;
import Util.Utility;
import domain.Student;
import domain.User;

import java.io.IOException;

@SuppressWarnings({"all"})

public class StudentView {
    private boolean loop = true;
    private ChooseRankView chooseRankView = new ChooseRankView();
    //用于接收键盘输入
    private String key = "";
    private StudentClientService studentClientService = new StudentClientService();

    //转型函数
    private Student getStateById(User user) throws IOException, InterruptedException {
        studentClientService.getStateById(user.getName(), user.getPersonId());
        Thread.sleep(300);
        return (Student) ConnectServerThread.getUser();
    }

    //获取自己成绩
    private void getGrade(Student student) throws IOException, InterruptedException {
        studentClientService.getGrade(student.getName(), student.getPersonId());
        Thread.sleep(300);
    }

    //发私聊
    private void sendMessageToOne(Student student) throws IOException, InterruptedException {
        System.out.println("请输入接收方姓名");
        String personName = Utility.readString(10);
        System.out.println("请输入接收方账号");
        String personId = Utility.readString(10);
        System.out.println("请输入聊天内容:");
        String content = Utility.readString(50);
        studentClientService.sendMessageToOne(student.getName(), student.getPersonId(), personId, personName, content);
        Thread.sleep(300);
    }

    //和班级同学聊天
    private void chatWithClassMate(Student student) throws IOException, InterruptedException {
        System.out.println("请输入你想对全班同学说的话");
        String content = Utility.readString(50);
        studentClientService.chatWithClassmates(student.getName(), student.getPersonId(), student.getClassId(), content);
        Thread.sleep(300);
    }

    //绑定家人
    private void bindGuardian(Student student) throws IOException {
        System.out.println("请输入监护人姓名");
        String guardianName = Utility.readString(10);
        System.out.println("请输入监护人账号");
        String guardianId = Utility.readString(10);
        studentClientService.bindGuardian(student.getName(), student.getPersonId(), guardianName, guardianId);
    }

    //退出
    private void quit(Student student) throws IOException {
        studentClientService.quitMessage(student.getName(), student.getPersonId());
    }

    //学生主菜单
    public void studentMenu(User user) throws IOException, InterruptedException {
        System.out.println("欢迎学生：" + user.getName());
        Student student = getStateById(user);
        while (loop) {
            System.out.println("-----------------请选择你要执行的操作-----------------");
            System.out.println("\t\t\t1 查询自己的成绩");
            System.out.println("\t\t\t2 查询自己的排名");
            System.out.println("\t\t\t3 查询总排名");
            System.out.println("\t\t\t4 向指定成员发起聊天");
            System.out.println("\t\t\t5 向全班同学发起聊天");
            System.out.println("\t\t\t6 绑定监护人");
            System.out.println("\t\t\t9 退出");
            key = Utility.readString(1);
            switch (key) {
                case "1":
                    getGrade(student);
                    break;
                case "2":
                    //和查询总排名差不多 只不过查询排名的时候顺带加一条定位 就是判断name.equal你需要的名字 或者personId.equal你需要的学号
                    chooseRankView.chooseOneMenu(student.getClassId(), student.getPersonId(), student.getName(), student.getName(), student.getPersonId());
                    break;
                case "3":
                    chooseRankView.chooseTotalMenu(student.getClassId(), student.getPersonId(), student.getName(), student.getName(), student.getPersonId());
                    break;
                case "4":
                    sendMessageToOne(student);
                    break;
                case "5":
                    chatWithClassMate(student);
                    break;
                case "6":
                    bindGuardian(student);
                    break;
                case "9":
                    quit(student);
                    loop = false;
                    break;
                default:
                    System.out.println("误操作");
                    break;
            }
        }
    }
}
