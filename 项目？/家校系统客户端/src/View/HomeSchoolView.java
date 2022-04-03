package View;


import Service.UserClientService;
import Util.Utility;
import domain.Guardian;
import domain.User;

import java.io.IOException;

@SuppressWarnings({"all"})
public class HomeSchoolView {
    private boolean loop = true;
    //用于接收键盘输入
    private String key = "";

    private UserClientService userClientService = new UserClientService();
    private TeacherView teacherView = new TeacherView();
    private StudentView studentView = new StudentView();
    private GuardianView guardianView = new GuardianView();

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        new HomeSchoolView().mainMenu();
    }

    private User loggingSystem() throws IOException, ClassNotFoundException {
        System.out.println("请输入用户号:");
        String personId = Utility.readString(50);
        System.out.println("请输入用户密码");
        String pwd = Utility.readString(50);
        User user = userClientService.checkUser(personId, pwd);
        return user;
    }

    //创建学生
    private void CreateStudent() throws IOException, ClassNotFoundException, InterruptedException {
        System.out.println("请输入用户号:");
        String personId = Utility.readString(50);
        while (personId.length() != 6) {
            System.out.println("长度不符合规则请重新输入");
            personId = Utility.readString(50);
        }
        System.out.println("请输入名字:");
        String personName = Utility.readString(50);
        System.out.println("请输入用户密码");
        String pwd = Utility.readString(50);
        System.out.println("请再次输入密码");
        String pwd2 = Utility.readString(50);
        while (!pwd.equals(pwd2)) {
            System.out.println("两次输入密码不一致请重新输入");
            pwd2 = Utility.readString(50);
        }
        System.out.println("请输入班级号");
        String classId = Utility.readString(10);
        while (classId.length() != 3) {
            System.out.println("班级输入格式有误请重新输入");
            classId = Utility.readString(10);
        }
        userClientService.createUser("student", personName, personId, pwd, classId);
        Thread.sleep(300);
    }

    //创建教师
    private void CreateTeacher() throws IOException, ClassNotFoundException, InterruptedException {
        System.out.println("请输入用户号:");
        String personId = Utility.readString(50);
        while (personId.length() != 6) {
            System.out.println("长度不符合规则请重新输入");
            personId = Utility.readString(50);
        }
        System.out.println("请输入名字:");
        String personName = Utility.readString(50);
        System.out.println("请输入用户密码");
        String pwd = Utility.readString(50);
        System.out.println("请再次输入密码");
        String pwd2 = Utility.readString(50);
        while (!pwd.equals(pwd2)) {
            System.out.println("两次输入密码不一致请重新输入");
            pwd2 = Utility.readString(50);
        }
        System.out.println("请输入班级号");
        String classId = Utility.readString(10);
        while (classId.length() != 3) {
            System.out.println("班级输入格式有误请重新输入");
            classId = Utility.readString(10);
        }
        userClientService.createUser("teacher", personName, personId, pwd, classId);
        Thread.sleep(300);
    }

    private void CreateGuardian() throws IOException, ClassNotFoundException, InterruptedException {
        System.out.println("请输入用户号:");
        String personId = Utility.readString(50);
        while (personId.length() != 6) {
            System.out.println("长度不符合规则请重新输入");
            personId = Utility.readString(50);
        }
        System.out.println("请输入名字:");
        String personName = Utility.readString(50);
        System.out.println("请输入用户密码");
        String pwd = Utility.readString(50);
        System.out.println("请再次输入密码");
        String pwd2 = Utility.readString(50);
        while (!pwd.equals(pwd2)) {
            System.out.println("两次输入密码不一致请重新输入");
            pwd2 = Utility.readString(50);
        }
        userClientService.createUser("guardian", personName, personId, pwd, "");
        Thread.sleep(300);
    }

    //根据不同的岗位选择不同菜单
    private void chooseMenu(String state, User user) throws IOException, InterruptedException {
        System.out.println();
        switch (state) {
            case "教师":
                teacherView.teacherMenu(user);
                break;
            case "管理员":
                System.out.println("管理员你好");
                break;
            case "学生":
                studentView.studentMenu(user);
                break;
            case "监护人":
                guardianView.guardianMenu(user);
                break;
            default:
                System.out.println("没有这个选项");
                break;
        }
    }

    private void mainMenu() throws IOException, ClassNotFoundException, InterruptedException {
        while (loop) {
            System.out.println("-------------欢迎登录家校系统-------------");
            System.out.println("               1 登录系统                ");
            System.out.println("               2 注册老师                ");
            System.out.println("               3 注册学生                ");
            System.out.println("               4 注册监护人              ");
            System.out.println("               9 退出系统                ");
            System.out.println("---------------------------------------");
            System.out.println("请输入你的选择");
            key = Utility.readString(1);
            switch (key) {
                case "1":
                    User user = loggingSystem();
                    if (user != null) {
                        //成功找到则进入第二层
                        while (loop) {
                            //System.out.println("\t\t\t欢迎:"+ user.getState()+" " + user.getName());
                            String state = user.getState();
                            chooseMenu(state, user);
                        }
                    }
                    break;
                case "2":
                    CreateTeacher();
                    break;
                case "3":
                    CreateStudent();
                    break;
                case "4":
                    CreateGuardian();
                    break;
                case "9":
                    loop = false;
                    System.exit(0);
                default:
                    System.out.println("没有这个选项");
                    break;
            }
        }
    }
}
