package View;

import Service.ConnectServerThread;
import Service.TeacherClientService;
import Util.Utility;

import domain.Teacher;
import domain.User;

import java.io.IOException;

public class TeacherView {
    private String key;
    private boolean loop = true;
    private TeacherClientService teacherClientService = new TeacherClientService();
    private ChooseRankView chooseRankView = new ChooseRankView();

    //获取班级成绩
    private void getGradesByClassId(Teacher teacher) throws IOException, InterruptedException {
        System.out.println("请输入你要查找的班级(纯数字即可)：");
        String classId = Utility.readString(10);
        teacherClientService.getGradesByClass(teacher.getName(), classId, teacher.getPersonId());
        //为了让主线程的接下来的消息不提前打出来 我们采取这种手段
        Thread.sleep(400);
    }

    //获取学生成绩
    private void getOneGradeByNameAndId(Teacher teacher) throws IOException, InterruptedException {
        System.out.println("请输入要查询的学生的姓名");
        String studentName = Utility.readString(10);
        System.out.println("请输入要查询的学生的学号");
        String studentId = Utility.readString(10);
        teacherClientService.getOneGradeByNameAndId(teacher.getName(), teacher.getPersonId(), studentName, studentId);
        Thread.sleep(400);
    }

    //给指定人发送消息
    private void sendMessageToOne(Teacher teacher) throws IOException, InterruptedException {
        System.out.println("请输入接收方姓名");
        String personName = Utility.readString(10);
        System.out.println("请输入接收方账号");
        String personId = Utility.readString(10);
        System.out.println("请输入聊天内容:");
        String content = Utility.readString(50);
        teacherClientService.sendMessageToOne(teacher.getName(), teacher.getPersonId(), personId, personName, content);
        Thread.sleep(300);
    }

    //转型函数
    private Teacher getStateById(User user) throws IOException, InterruptedException {
        teacherClientService.getStateById(user.getName(), user.getPersonId());
        Thread.sleep(300);
        return (Teacher) ConnectServerThread.getUser();
    }

    //和班级学生聊天
    private void chatWithClassStudents(Teacher teacher) throws IOException, InterruptedException {
        System.out.println("请输入聊天内容:");
        String content = Utility.readString(200);
        teacherClientService.chatWithClassStudents(teacher.getName(), teacher.getPersonId(), teacher.getClassID(), content);
        Thread.sleep(300);

    }

    //和班级家长聊天
    private void chatWithGuardians(Teacher teacher) throws IOException, InterruptedException {
        System.out.println("请输入聊天内容");
        String content = Utility.readString(200);
        teacherClientService.chatWithGuardians(teacher.getName(), teacher.getPersonId(), teacher.getClassID(), content);
        Thread.sleep(300);
    }

    //发送单人的成绩
    private void sendOneGradeToOne(Teacher teacher) throws IOException {
        System.out.println("请输入姓名:");
        String name = Utility.readString(200);
        System.out.println("请输入学号:");
        String personId = Utility.readString(200);
        teacherClientService.sendOneGradeToOne(teacher.getName(), teacher.getPersonId(), name, personId);

    }

    //发送全班的成绩
    private void sendGradeToAll(Teacher teacher) throws IOException {
        teacherClientService.sendGradesToAll(teacher.getName(), teacher.getPersonId(), teacher.getClassID());
    }

    //总排名查询
    private void getAllScoresRank(Teacher teacher) throws IOException, InterruptedException {
        chooseRankView.chooseTotalMenu(teacher.getClassID(), teacher.getPersonId(), teacher.getName(), "", "");
        Thread.sleep(300);
    }

    //单人排名查询
    private void chooseOneRankMenu(Teacher teacher) throws IOException, InterruptedException {
        System.out.println("请输入查询的姓名:");
        String studentName = Utility.readString(200);
        System.out.println("请输入查询的学号:");
        String studentId = Utility.readString(200);
        chooseRankView.chooseOneMenu(teacher.getClassID(), teacher.getPersonId(), teacher.getName(), studentName, studentId);
        Thread.sleep(300);

    }

    //班级详情查询
    private void getClassInfo(Teacher teacher) throws IOException, InterruptedException {
        System.out.println("请输入你要查询的班级:");
        String classId = Utility.readString(10);
        teacherClientService.getClassInfo(teacher.getName(), teacher.getPersonId(), classId);
        Thread.sleep(300);

    }

    //退出
    private void quit(Teacher teacher) throws IOException {
        teacherClientService.quitMessage(teacher.getName(), teacher.getPersonId());
    }

    //教师菜单
    public void teacherMenu(User user) throws IOException, InterruptedException {
        System.out.println("欢迎教师：" + user.getName());
        //调用一个函数 根据user返回teacher 利用多态
        Teacher teacher = getStateById(user);
        while (loop) {
            System.out.println();
            System.out.println("------------------请选择你要执行的操作：-------------------");
            System.out.println("\t1  全班成绩查询                     9  总排名查询");
            System.out.println("\t2  查询指定学生成绩                  10 单人排名查询 ");
            System.out.println("\t3  向学生和家长发送他这次成绩");
            System.out.println("\t4  向学生和家长发送全班的成绩");
            System.out.println("\t5  向指定成员发起聊天");
            System.out.println("\t6  向全班同学发起聊天");
            System.out.println("\t7  向全班家长发起聊天");
            //打印出班级学生的姓名学号，再打印出家长的账号名称
            System.out.println("\t8  查询班级状况");
            System.out.println("\t\t20 退出");
            key = Utility.readString(2);
            switch (key) {
                case "1":
                    getGradesByClassId(teacher);
                    break;
                case "2":
                    getOneGradeByNameAndId(teacher);
                    break;
                case "3":
                    sendOneGradeToOne(teacher);
                    break;
                case "4":
                    sendGradeToAll(teacher);
                    break;
                case "5":
                    sendMessageToOne(teacher);
                    break;
                case "6":
                    chatWithClassStudents(teacher);
                    break;
                case "7":
                    chatWithGuardians(teacher);
                    break;
                case "8":
                    getClassInfo(teacher);
                    break;
                case "9":
                    getAllScoresRank(teacher);
                    break;
                case "10":
                    chooseOneRankMenu(teacher);
                    break;
                case "20":
                    quit(teacher);
                    loop = false;
                    break;
                default:
                    System.out.println("没有这个选项");
                    break;
            }
        }
    }
}
