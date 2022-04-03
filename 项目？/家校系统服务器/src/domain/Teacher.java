package domain;

/**
 * 映射数据库的教师表
 * CREATE TABLE teacher(
 * 	id INT PRIMARY KEY AUTO_INCREMENT, #自增主键
 * 	`NAME` VARCHAR(50) NOT NULL DEFAULT '',  #教师名
 * 	personId VARCHAR(50) NOT NULL DEFAULT '',#账号
 * 	pwd VARCHAR(32) NOT NULL DEFAULT '',#密码md5
 * 	classId VARCHAR(12) NOT NULL DEFAULT '',#班主任的班级
 * 	job VARCHAR(50) NOT NULL DEFAULT '' #教师类型
 * );
 * */
@SuppressWarnings({"all"})
public class Teacher extends User {
    private Integer id;//自增主键
    private String name;
    private String personId;
    private String pwd;
    private String classID;
    private String job;

    public Teacher() {
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

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "姓名:" + name  +
                "\t账号:" + personId +
                "\t管理班级:'" + classID  +
                "\t岗位：" + job;
    }
}
