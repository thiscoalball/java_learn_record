package service;

import DAO.BookListDAO;
import DAO.BorrowListDAO;
import DAO.MultiInfoDAO;
import domain.BookList;
import domain.MultiInfoBean;
import domain.PersonList;
import utils.Utility;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@SuppressWarnings({"all"})
public class BorrowListService {
    private BorrowListDAO borrowListDAO = new BorrowListDAO();
    private BookListDAO bookListDAO = new BookListDAO();
    private PersonListService personListService = new PersonListService();
    private MultiInfoDAO multiInfoDAO = new MultiInfoDAO();

    //存入借书信息
    public void saveBorrowBook(String personId, String bookId) throws SQLException {
        String sql = "INSERT INTO borrowList VALUES(?,?,NOW(),DATE_ADD(NOW(),INTERVAL + 30 DAY ))";
        borrowListDAO.update(sql, personId, bookId);
    }

    //还书
    public boolean saveReturnBook(PersonList personList, String bookName, String author) throws SQLException {
        //先查询这本书的Id
        String sql = "SELECT * FROM bookList WHERE author = ? AND NAME = ?";
        String personId = personList.getPersonId();
        BookList bookList = bookListDAO.querySingle(sql, BookList.class, author, bookName);
        if (bookList == null) {
            System.out.println("你可能没借这本书");
            return false;
        }
        Integer bookId = bookList.getId();
        //欠了几天前
        Object day = borrowListDAO.queryScalar("SELECT DATEDIFF(NOW(),returndate) AS DAY FROM borrowlist\n" +
                "\tWHERE DATE_ADD(returndate,INTERVAL 1 SECOND) <= NOW() AND bookId = ? AND personId = ?", bookId, personId);

        //如果逾期
        if(day != null){
            int i = Integer.parseInt(day.toString());
            System.out.println("你已经逾期" + i + "天未还 请交纳" + i +"元罚款");
            System.out.println("请确认是否交钱，不交则将被登记为黑名单(y/n)");
            char c = Utility.readConfirmSelection();
            if(c == 'Y'){
                System.out.println("罚款交纳完成");
                personListService.setWhiteList(personId);
                personListService.returnBookSizeAdd(personId);
                personList.setState("白名单");
                if(removeBorrowBook(personId,bookId)) {
                    return true;
                }
            } else {
                System.out.println("你已经被登记为黑名单");
                personList.setState("黑名单");
                personListService.setBlackList(personId);

                return false;
            }

        }

        //未逾期
        if (removeBorrowBook(personId,bookId)) {
            //还书成功的话就需要把自己的最大借书量+1
            personListService.returnBookSizeAdd(personId);
            return true;
        } else {
            return false;
        }
    }

    //续借
    public boolean renewBook(String personId, String bookName, String author) throws SQLException {
        String sql = "SELECT * FROM bookList WHERE author = ? AND NAME = ?";

        BookList bookList = bookListDAO.querySingle(sql, BookList.class, author, bookName);
        //这一条if语句始终要放在所有if语句的开头 否则可能引发空指针异常
        if (bookList == null) {
            System.out.println("你可能没借这本书");
            return false;
        }
        if (!canRenewBook(personId, bookList.getId())) {
            System.out.println("你已经续借了三次了");
            return false;
        }
        if (bookList == null) {
            System.out.println("你可能没借这本书");
            return false;
        } else {
            Integer bookId = bookList.getId();
            int update = borrowListDAO.update("UPDATE borrowlist SET returndate = returndate+ INTERVAL + 30 DAY \n" +
                    "\tWHERE personid = ? AND bookId = ?", personId, bookId);
            if (update > 0) {
                Object day = borrowListDAO.queryScalar("select returndate from borrowlist where bookId=? and personid = ?", bookId, personId);
                System.out.println("你应该在 " + day.toString() + " 归还本书");
                return true;
            } else {
                return false;
            }
        }
    }

    //查看自己借阅情况
    public List<MultiInfoBean> searchOne(String personId) throws SQLException {
        String sql = "SELECT borrowDate,returndate,booklist.name AS bookName,personlist.name AS personName\n" +
                "\tFROM borrowlist,booklist,personlist \n" +
                "\t\tWHERE BookId=booklist.id \n" +
                "\t\t\tAND borrowlist.personid=personlist.personid\n" +
                "\t\t\tAND personList.personid = ?";
        return multiInfoDAO.queryMulti(sql, MultiInfoBean.class, personId);
    }

    //查看所有人借阅情况
    public List<MultiInfoBean> searchAll(String personId) throws SQLException {
        String sql = "SELECT borrowDate,returndate,booklist.name AS bookName,personlist.name AS personName\n" +
                "\tFROM borrowlist,booklist,personlist \n" +
                "\t\tWHERE BookId=booklist.id \n" +
                "\t\t\tAND borrowlist.personid=personlist.personid";
        return multiInfoDAO.queryMulti(sql, MultiInfoBean.class);
    }

    //查看某personid值有无借某个书 防止重复借书
    public boolean isBorrowThisBook(String personId, String bookName) throws SQLException {
        String sql = "SELECT borrowDate,returndate,booklist.name AS bookName,personlist.name AS personName\n" +
                "\tFROM borrowlist,booklist,personlist \n" +
                "\t\tWHERE BookId=booklist.id \n" +
                "\t\t\tAND borrowlist.personid=personlist.personid \n" +
                "\t\t\tAND personList.personid = ? AND booklist.Name = ?";
        MultiInfoBean multiInfoBean = multiInfoDAO.querySingle(sql, MultiInfoBean.class, personId, bookName);
        if (multiInfoBean != null) {
            return true;
        } else {
            return false;
        }
    }

    //查看该书借到续借的总天数 限制续借次数
    public boolean canRenewBook(String personId, int bookId) throws SQLException {
        String sql = "SELECT DATEDIFF(returndate,borrowdate) AS DAY FROM borrowlist \n" +
                "\tWHERE personid = ? AND bookid = ?";
        Object day = borrowListDAO.queryScalar(sql, personId, bookId);
        int i = Integer.parseInt(day.toString());
        if (i > 115) {
            return false;
        } else {
            return true;
        }
    }

    //根据书的id和人的id删除书籍
    public boolean removeBorrowBook(String personId, int bookId) throws SQLException {
        int update = borrowListDAO.update
                ("DELETE FROM borrowList WHERE personId  = ? AND bookId = ?", personId, bookId);
        if(update > 0){
            return true;
        } else {
            return false;
        }
    }
}
