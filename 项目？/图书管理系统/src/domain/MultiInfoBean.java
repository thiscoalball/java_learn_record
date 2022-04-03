package domain;

import java.time.LocalDateTime;

@SuppressWarnings({"all"})
public class MultiInfoBean {
    private String personId;
    private String bookId;
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;
    private String personName;
    private String bookName;

    public MultiInfoBean() {
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

    @Override
    public String toString() {
        return
                personName + "\t\t\t" +
                        bookName + "\t\t\t" +
                        borrowDate + "\t\t" +
                        returnDate;

    }
}
