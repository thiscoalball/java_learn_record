package service;

import DAO.BookListDAO;
import DAO.PersonListDAO;
import domain.BookList;
import domain.PersonList;
import domain.RecordList;

import java.awt.print.Book;
import java.sql.SQLException;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
@SuppressWarnings({"all"})
public class BookListService {
    private BookListDAO bookListDAO = new BookListDAO();
    private PersonListService personListService = new PersonListService();
    private BorrowListService borrowListService = new BorrowListService();
    private RecordListService recordListService = new RecordListService();
    //封装函数 展示馆藏书籍
    public void showBookList() throws SQLException {
        String sql = "select * from bookList";
        List<BookList> bookLists = bookListDAO.queryMulti(sql, BookList.class);

        System.out.println();
        System.out.println("====================馆藏书籍如下======================");
        System.out.println("书籍编号\t\t书籍名\t\t\t\t书籍余量\t\t作者");
        for(BookList book: bookLists){
            System.out.println(book);
        }
        System.out.println("======================展示完毕========================");
        System.out.println();
    }

    //封装函数 往书库里添加书籍
    public void insertBook(String bookName,String author) throws SQLException {
        int update = bookListDAO.update("INSERT INTO bookList VALUES (NULL,?,5,?)", bookName, author);
        if(update>0) {
            System.out.println("书籍：" + bookName + " 作者：" + author + " 添加成功");
        }else{
            System.out.println("添加失败");
        }
    }

    //查找函数查找书(不是精确查询)
    public boolean searchBooks(String bookName,String author) throws SQLException {
        String sql = "SELECT * FROM bookList WHERE author = ? OR NAME = ?";
        List<BookList> bookLists = bookListDAO.queryMulti(sql, BookList.class, author,bookName);

        //如果返回的列表不为空
        if(!bookLists.isEmpty()){
            System.out.println();
            System.out.println("查到到的相关信息如下");
            System.out.println("书籍编号\t\t书籍名\t\t书籍余量\t\t作者");
            for(BookList book:bookLists){
                System.out.println(book);
            }
            return true;
        }else{
            System.out.println("很抱歉，查找不到该书信息");
            return false;
        }
    }

    //找一本书
    public BookList searchOneBook(String bookName, String author) throws SQLException{
        String sql = "SELECT * FROM bookList WHERE author = ? OR NAME = ?";
        return bookListDAO.querySingle(sql, BookList.class, author, bookName);
    }

    //借书函数
    public void borrowBook(String bookName, String author, PersonList personList) throws SQLException {
        //先要查找到这本书
        String sql = "SELECT * FROM bookList WHERE author = ? AND NAME = ?";
        BookList bookList = bookListDAO.querySingle(sql, BookList.class, author, bookName);

        String personId = personList.getPersonId();
        //最好放在第一行if 不然可能引发空指针异常
        if (bookList == null) {
            System.out.println("查询不到相关信息");
            return;
        }
        if(borrowListService.isBorrowThisBook(personId,bookName)){
            System.out.println("你已经借过这本书了，请勿重复借书");
            return;
        }
        if (bookList.getNumbers() == 1) {
            System.out.println("馆藏仅剩一本，无法借阅");
            return;
        }
        if(personList.getMaxBook() <= 0){
            System.out.println("你已经达到最大借书量，无法借书");
            return;
        } else {
            //其实这边最好是使用事务进行一起提交，但是JDBC的事务交互我还没学
            //让馆藏书量-1
            int update = bookListDAO.update("UPDATE booklist SET numbers=numbers-1 WHERE author=? AND NAME=?", author, bookName);
            if(update>0) {
                //借到书了 调用人员类，让里面的最大借书量-1
                personListService.borrowBookSizeSub(personId);
                //传的是变量 所以这里也-1 防止小bug
                personList.setMaxBook(personList.getMaxBook()-1);
                //调用总记录表 存入借阅消息
                recordListService.insertRecord(bookList.getName(),personList.getName());
                //调用借阅表 存入本次借阅信息
                borrowListService.saveBorrowBook(personId,bookList.getId().toString());
                System.out.println("借阅成功 你还能借"+personList.getMaxBook()+"本书");
                LocalDateTime localDateTime = LocalDateTime.now().plusDays(30);
                String dateFormat = "yyyy-mm-dd hh-mm-ss";
                DateTimeFormatter basicIsoDate = DateTimeFormatter.ISO_LOCAL_DATE;
                String format = localDateTime.format(basicIsoDate);
                System.out.println("借书成功,你应该在 "+ format+" 之前归还");
            }
            else {
                System.out.println("借阅失败");
            }
        }
    }

    //还书函数
    public void returnBook(String bookName, String author,PersonList personList) throws SQLException {
        //这个函数可以查找借阅表中自己有无借这个书 有的话就删除这条记录
        boolean flag = borrowListService.saveReturnBook(personList, bookName, author);
        if(flag){
            //这时记录已经被删除，而我们也需要把这本书的余量给加回去
            int update = bookListDAO.update("UPDATE bookList SET numbers=numbers+1 WHERE author = ? AND NAME = ?", author, bookName);
            personList.setMaxBook(personList.getMaxBook()+1);
            //更新总记录表
            recordListService.returnRecord(bookName, personList.getName());
            System.out.println("还书成功，你还可以借" + personList.getMaxBook()+"本书");
        }else {
            System.out.println("还书失败");
        }
    }
}
