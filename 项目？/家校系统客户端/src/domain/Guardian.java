package domain;

import com.sun.org.apache.xerces.internal.impl.dv.xs.YearDV;

/**
 * 映射数据库的监护人表
 * CREATE TABLE guardian(
 * 	id INT PRIMARY KEY AUTO_INCREMENT, #自增主键
 * 	`NAME` VARCHAR(50) NOT NULL DEFAULT '',  #监护人名
 * 	personId VARCHAR(50) NOT NULL DEFAULT '',#账号
 * 	pwd VARCHAR(32) NOT NULL DEFAULT '',#密码md5
 * 	`childName` VARCHAR(50) NOT NULL DEFAULT '',  #孩子名
 * 	childId VARCHAR(50) NOT NULL DEFAULT ''#孩子账号(相当于电话号码)
 * 	classId VARCHAR(12) NOT NULL DEFAULT ''#孩子的班级
 * );
 * */
@SuppressWarnings({"all"})
public class Guardian extends User {
    private Integer id;//自增主键
    private String name;
    private String personId;
    private String pwd;
    private String childName;
    private String childId;
    private String classId;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public Guardian() {
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

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }

    @Override
    public String toString() {
        return "Guardian{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", personId='" + personId + '\'' +
                ", pwd='" + pwd + '\'' +
                ", childName='" + childName + '\'' +
                ", childId='" + childId + '\'' +
                '}';
    }
}
