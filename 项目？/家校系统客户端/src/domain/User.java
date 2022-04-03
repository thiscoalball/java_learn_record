package domain;
import java.io.Serializable;

/**
 * 映射数据库的User表
 * CREATE TABLE `user`(
 * 	id INT PRIMARY KEY AUTO_INCREMENT, #自增主键
 * 	`NAME` VARCHAR(50) NOT NULL DEFAULT '',  #人名
 * 	personId VARCHAR(50) NOT NULL DEFAULT '',#账号
 * 	pwd VARCHAR(32) NOT NULL DEFAULT '',#密码md5
 * 	state VARCHAR(10) NOT NULL DEFAULT '' #岗位
 * );
 * */
@SuppressWarnings({"all"})
public class User implements Serializable {
    private Integer id;//自增主键
    private String name;
    private String personId;
    private String pwd;
    private String state;
    
    private static final long serialVersionUID = 1L;
    public User() {
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

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", personId='" + personId + '\'' +
                ", pwd='" + pwd + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
