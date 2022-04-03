package domain;

/**
 * 与数据库的personList表映射
 * id INT PRIMARY KEY AUTO_INCREMENT, #自增
 * personId VARCHAR(50) NOT NULL DEFAULT '',#账号
 * `NAME` VARCHAR(50) NOT NULL DEFAULT '',  #人名
 * pwd VARCHAR(32) NOT NULL DEFAULT '',#密码md5
 * job VARCHAR(50) NOT NULL DEFAULT '', #岗位
 * maxBook INT NOT NULL DEFAULT 3
 */
public class PersonList {
    private Integer id;
    private String personId;
    private String name;
    private String pwd;
    private String job;
    private Integer maxBook;
    private String state;

    public PersonList() {
    }

    public PersonList(Integer id, String personId, String name, String pwd, String job, Integer maxBook, String state) {
        this.id = id;
        this.personId = personId;
        this.name = name;
        this.pwd = pwd;
        this.job = job;
        this.maxBook = maxBook;
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getMaxBook() {
        return maxBook;
    }

    public void setMaxBook(Integer maxBook) {
        this.maxBook = maxBook;
    }

    @Override
    public String toString() {
        return "PersonList{" +
                "id=" + id +
                ", personId='" + personId + '\'' +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", job='" + job + '\'' +
                ", maxBook=" + maxBook +
                '}';
    }
}
