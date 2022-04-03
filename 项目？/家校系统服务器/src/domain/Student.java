package domain;
/**
 * 映射数据库的学生表
 * CREATE TABLE student(
 * 	id INT PRIMARY KEY AUTO_INCREMENT, #自增主键
 * 	`NAME` VARCHAR(50) NOT NULL DEFAULT '',  #学生名
 * 	personId VARCHAR(50) NOT NULL DEFAULT '',#账号
 * 	pwd VARCHAR(32) NOT NULL DEFAULT '',#密码md5
 * 	classId VARCHAR(12) NOT NULL DEFAULT '',#所属的班级
 * 	`guardianName` VARCHAR(50) NOT NULL DEFAULT '',  #监护人名
 * 	guardianId VARCHAR(50) NOT NULL DEFAULT ''#亲属号(相当于电话号码)
 * );
 * */
@SuppressWarnings({"all"})
public class Student extends User {
    private Integer id;//自增主键
    private String name;
    private String personId;
    private String pwd;
    private String classId;
    private String guardianName;
    private String guardianId;

    public Student() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPersonId() {
        return personId;
    }

    @Override
    public void setPersonId(String personId) {
        this.personId = personId;
    }

    @Override
    public String getPwd() {
        return pwd;
    }

    @Override
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getGuardianName() {
        return guardianName;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }

    public String getGuardianId() {
        return guardianId;
    }

    public void setGuardianId(String guardianId) {
        this.guardianId = guardianId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", personId='" + personId + '\'' +
                ", pwd='" + pwd + '\'' +
                ", classId='" + classId + '\'' +
                ", guardianName='" + guardianName + '\'' +
                ", guardianId='" + guardianId + '\'' +
                '}';
    }
}
