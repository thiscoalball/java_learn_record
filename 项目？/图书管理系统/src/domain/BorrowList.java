package domain;

import java.time.LocalDateTime;

/**
 * 与数据库的borrowList表映射
 * personId VARCHAR(50) NOT NULL DEFAULT '',#账号
 * bookId INT PRIMARY KEY AUTO_INCREMENT, #书籍序号
 * borrowDate DATETIME,
 * returnDate DATETIME
 */
public class BorrowList {
    private String personId;
    private String bookId;
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;

    public BorrowList() {
    }

    public BorrowList(String personId, String bookId, LocalDateTime borrowDate, LocalDateTime returnDate) {
        this.personId = personId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
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

    @Override
    public String toString() {
        return "BorrowList{" +
                "personId='" + personId + '\'' +
                ", bookId='" + bookId + '\'' +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
