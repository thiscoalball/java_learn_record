package domain;

/**
 * 与数据库里的书籍表映射
 * id INT PRIMARY KEY AUTO_INCREMENT, #书籍序号
 * NAME VARCHAR(50) NOT NULL DEFAULT '',  #书籍名
 * numbers INT NOT NULL DEFAULT 5,  #书本数量
 * author VARCHAR(32) NOT NULL DEFAULT ''#作者
 */
public class BookList {
    private Integer id;
    private String name;
    private Integer numbers;
    private String author;

    public BookList() {
    }

    public BookList(Integer id, String name, Integer numbers, String author) {
        this.id = id;
        this.name = name;
        this.numbers = numbers;
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumbers() {
        return numbers;
    }

    public void setNumbers(Integer numbers) {
        this.numbers = numbers;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return id + "\t\t\t" +
                name + "\t\t\t\t" +
                numbers + "\t\t\t" +
                author + "\t\t"
                ;
    }
}
