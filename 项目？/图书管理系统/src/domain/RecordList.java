package domain;

import java.time.LocalDateTime;

/**
 * 与数据库的recordList表映射
 * CREATE TABLE recordList(
 * id INT PRIMARY KEY AUTO_INCREMENT, #自增
 * personNAME VARCHAR(50) NOT NULL DEFAULT '',  #人名
 * bookNAME VARCHAR(50) NOT NULL DEFAULT '',  #书籍名
 * borrowDate DATETIME,#借阅日期
 * ReturnDate DATETIME, #在什么时候归还 没还的话传null
 * state VARCHAR(10) NOT NULL DEFAULT '未归还'
 * )CHARSET=utf8;
 */
public class RecordList {
    private Integer id;
    private String personName;
    private String bookName;
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;
    private String state;

    public RecordList() {
    }

    public RecordList(Integer id, String personName, String bookName, LocalDateTime borrowDate, LocalDateTime returnDate, String state) {
        this.id = id;
        this.personName = personName;
        this.bookName = bookName;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public LocalDateTime getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDateTime borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return id +
                "\t" + personName +
                "\t\t" + bookName +
                "\t\t" + borrowDate +
                "\t\t" + returnDate +
                "\t\t" + state
                ;
    }
}
