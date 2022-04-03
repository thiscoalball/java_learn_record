package view;

import domain.*;
import service.BookListService;
import service.BorrowListService;
import service.PersonListService;
import service.RecordListService;
import utils.Utility;

import java.sql.SQLException;
import java.util.List;

@SuppressWarnings({"all"})
public class View {
    private boolean loop = true;
    private String key = "";

    private PersonListService personListService = new PersonListService();
    private BookListService bookListService = new BookListService();
    private BorrowListService borrowListService = new BorrowListService();
    private RecordListService recordListService = new RecordListService();

    //============================该程序还可以继续完善的点=========================================
    //
    //一：让用户不能借同一本书多次， 通过borrowlist表进行检测                     -------已完成
    //      比如借了两本西游记 sout"你已经借了这本书了，不要再借一次"
    //
    //二：续借次数不能超过三次                                                 -------已完成
    //      实现思路1：如果借阅日期减去归还日期大于 4个月 那么就不能再续借了,数据库语句怎么实现这个判断来着? 到时再看看
    //      实现思路2：续借表中增加一个字段 续借次数 每次续借时 这个次数就++ 这样就可以限制次数，这个方法最笨，但是最简单
    //
    //三：逾期提醒
    //      这个需要数据库再配合写一个服务器程序可能还需要将现有的这个程序升级为客户端，暂时不做
    //
    //四：借书改善                                                           -------已完成
    //      在借书时打印你应该在什么时候归还
    //
    //五：续借改善
    //      在续借的时候打印你续借后应该在什么时候归还，获取returnDate的行列值     -------已完成
    //
    //六：超时未还
    //      还书的时候 获取当前日期与还书日期进行比较 如果逾期了那么就提醒还书并且提问是否要交纳罚金，如果不交的话 那就直接记录黑名单 这人不能再借书。
    //      通过发送mysql语句去数据库里进行逻辑判断       -------已完成
    //
    //七：创建一个记录表RecordList
    //      记录用户姓名，书名，用户借书时间以及归还日期(若还未归还就传null)和当前状态(已归还，未归还);
    //      用来做图书的总借阅记录。（总借阅记录 不是当前借阅表）     -------已完成
    //
    //八：进书函数改善：
    //      如果书籍和作者同时相同了，那么就 书库中已有同名同作者的书，请勿重复添加 -------已完成

    public static void main(String[] args) throws SQLException {
        View view = new View();
        view.mainMenu();
    }

    //注册函数
    public void RegisterAccount() throws SQLException {
        System.out.println("请输入您的账号：");
        //这里需要进入数据库检测是否有账号相同
        String personId = Utility.readString(50);
        if (personListService.idIsRepeat(personId)) {
            System.out.println("已经有相同账号");
            return;
        }
        System.out.println("输入密码：");
        String pwd = Utility.readString(20);
        System.out.println("请再次输入密码：");
        String pwd1 = Utility.readString(20);
        if (!pwd1.equals(pwd)) {
            System.out.println("两次密码输入不同 请重新注册");//其实可以做输入限定次数的但是我觉得没必要了，一个for循环的事情
            return;
        }
        System.out.println("输入姓名：");
        String name = Utility.readString(20);
        //进入人员服务层完成注册
        if (personListService.RegisterAccount(personId, pwd, name)) {
            System.out.println("注册完成，可以登录");
        } else {
            System.out.println("注册失败");
        }
    }

    //验证函数
    public PersonList checkPerson() throws SQLException {
        System.out.println("请输入账号：");
        String userId = Utility.readString(50);
        System.out.println("输入密码：");
        String pwd = Utility.readString(20);
        //调用人员服务层 对是否存在该人员进行检查
        PersonList personList = personListService.checkIdAndPwd(userId, pwd);
        return personList;
    }

    //进书函数
    public void insertBook(PersonList personList) throws SQLException {
        if (personList.getJob().equals("读者")) {
            System.out.println("很抱歉，您无权进书");
            return;
        } else {
            System.out.println("请输入书籍名字：");
            String bookName = Utility.readString(50);
            System.out.println("请输入书籍的作者：");
            String author = Utility.readString(50);
            BookList bookList = bookListService.searchOneBook(bookName, author);
            if (bookList != null) {
                System.out.println(" 书库中已有同名同作者的书，请勿重复添加");
                return;
            }
            bookListService.insertBook(bookName, author);
        }
    }

    //查找书籍函数
    public void searchBook(PersonList personList) throws SQLException {
        System.out.println("请输入书籍名字：");
        String bookName = Utility.readString(50);
        System.out.println("请输入书籍的作者：");
        String author = Utility.readString(50);
        bookListService.searchBooks(bookName, author);
    }

    //借书函数
    public void borrowBook(PersonList personList) throws SQLException {
        if (personList.getState().equals("黑名单")) {
            System.out.println("你是黑名单用户 无法借书 请尽快还钱");
            return;
        }
        System.out.println("请输入书籍名字：");
        String bookName = Utility.readString(50);
        System.out.println("请输入书籍的作者：");
        String author = Utility.readString(50);
        if (true) {
            bookListService.borrowBook(bookName, author, personList);
        } else {
            System.out.println("你已经借过这本书不要再借第二次");
        }
    }

    //还书函数
    public void returnBook(PersonList personList) throws SQLException {
        System.out.println("请输入书籍名字：");
        String bookName = Utility.readString(50);
        System.out.println("请输入书籍的作者：");
        String author = Utility.readString(50);
        bookListService.returnBook(bookName, author, personList);
    }

    //续借函数
    public void renewBook(PersonList personList) throws SQLException {
        System.out.println("请输入书籍名字：");
        String bookName = Utility.readString(50);
        System.out.println("请输入书籍的作者：");
        String author = Utility.readString(50);
        if (!borrowListService.renewBook(personList.getPersonId(), bookName, author)) {
            System.out.println("续借失败");
        } else {
            System.out.println("续借成功");
        }
    }

    //展示自己借阅情况函数
    public void showMyBookBorrow(PersonList personList) throws SQLException {
        List<MultiInfoBean> myBookList = borrowListService.searchOne(personList.getPersonId());
        System.out.println("借阅人\t\t借阅书籍\t\t\t借阅日期\t\t\t\t\t应归还日期");
        for (MultiInfoBean info : myBookList) {
            System.out.println(info);
        }
    }

    //展示所有人的借阅情况
    public void showAllBookBorrow(PersonList personList) throws SQLException {
        if (personList.getJob().equals("读者")) {
            System.out.println("很抱歉，您没有这个权限");
            return;
        } else {
            List<MultiInfoBean> myBookList = borrowListService.searchAll(personList.getPersonId());
            System.out.println("借阅人\t\t借阅书籍\t\t\t借阅日期\t\t\t\t\t应归还日期");
            for (MultiInfoBean info : myBookList) {
                System.out.println(info);
            }
        }
    }

    //查询总记录表
    public void showAllRecordList(PersonList personList) throws SQLException {
        if (personList.getJob().equals("读者")) {
            System.out.println("你无权进行该操作");
            return;
        }
        recordListService.getRecordList();
    }

    //查询某人或者某书借阅记录
    public void showOneRecordList(PersonList personList) throws SQLException {
        if (personList.getJob().equals("读者")) {
            System.out.println("你无权进行该操作");
            return;
        }
        System.out.println("请输入你要查询的人的姓名");
        String personName = Utility.readString(20);
        System.out.println("请输入你要查询的书名");
        String bookName = Utility.readString(30);
        recordListService.getOneRecord(personName, bookName);
    }

    //清除总记录表
    public void clearRecord(PersonList personList) throws SQLException {
        if (personList.getJob().equals("读者")) {
            System.out.println("你无权进行该操作");
            return;
        }
        recordListService.clear();
    }

    //主菜单
    public void mainMenu() throws SQLException {
        while (loop) {
            System.out.println("======================图书馆管理系统======================");
            System.out.println("\t\t\t\t1 登陆系统");
            System.out.println("\t\t\t\t2 注册账户");
            System.out.println("\t\t\t\t9 退出");
            System.out.println("========================================================");
            System.out.println("请输入你的选择");
            key = Utility.readString(1);
            switch (key) {
                case "1":
                    //验证用户的登录
                    PersonList personList = checkPerson();
                    if (personList != null) {
                        System.out.println("登录成功,欢迎" + personList.getJob() + personList.getName());
                        //这里为了方便直接使用loop 如果要实现 返回上一级的操作的话，就定义两个loop类变量就行
                        while (loop) {
                            System.out.println("===================图书馆管理系统二级菜单===================");
                            System.out.println("\t\t1 借书                      9  查看借阅总记录");
                            System.out.println("\t\t2 还书                      10 查询指定人借阅记录");
                            System.out.println("\t\t3 续借                      11 清除总记录表");
                            System.out.println("\t\t4 进书                      12 修改书籍");
                            System.out.println("\t\t5 展示馆藏书");
                            System.out.println("\t\t6 查询书籍");
                            System.out.println("\t\t7 查看自己借阅情况            15 退出");
                            System.out.println("\t\t8 查看当前借阅情况");
                            System.out.println("\t\t15 退出");
                            System.out.println("========================================================");
                            System.out.println("请输入你的选择");
                            key = Utility.readString(2);
                            switch (key) {
                                case "1":
                                    borrowBook(personList);
                                    break;
                                case "2":
                                    returnBook(personList);
                                    break;
                                case "3":
                                    renewBook(personList);
                                    break;
                                case "4":
                                    //判断用户权限能否进书 如果权限为读者的话就不行
                                    insertBook(personList);
                                    break;
                                case "5":
                                    //直接调用图书服务层的展示函数
                                    bookListService.showBookList();
                                    break;
                                case "6":
                                    searchBook(personList);
                                    break;
                                case "7":
                                    showMyBookBorrow(personList);
                                    break;
                                case "8":
                                    showAllBookBorrow(personList);//这是读者不具备的权限
                                    break;
                                case "9":
                                    showAllRecordList(personList);//这是读者不具备的权限
                                    break;
                                case "10":
                                    showOneRecordList(personList);
                                    break;
                                case "11":
                                    clearRecord(personList);
                                    break;
                                case "15":
                                    System.out.println("退出");
                                    loop = false;
                                    break;
                                default:
                                    System.out.println("无效操作");
                                    break;
                            }
                        }
                    } else {
                        System.out.println("账号密码错误或者账号不存在\r\n");
                    }
                    break;
                case "2":
                    RegisterAccount();
                    break;
                case "9":
                    System.out.println("退出");
                    loop = false;
                    break;
                default:
                    System.out.println("无效操作");
                    break;
            }
        }
    }
}
