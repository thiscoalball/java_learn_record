package domain;

/**
 * 一个和employee表映射的javabean
 *      id INT PRIMARY KEY AUTO_INCREMENT, #自增
 * 	    empId VARCHAR(50) NOT NULL DEFAULT '',#员工号
 * 	    pwd CHAR(32) NOT NULL DEFAULT '',#密码md5
 * 	    NAME VARCHAR(50) NOT NULL DEFAULT '',#姓名
 * 	    job VARCHAR(50) NOT NULL DEFAULT '' #岗位
 * */
public class Employee {
    private Integer id;
    private String empId;
    private String pwd;
    private String name;
    private String job;

    public Employee() { //供反射使用的无参构造器
    }

    public Employee(Integer id, String empId, String pwd, String name, String job) {
        this.id = id;
        this.empId = empId;
        this.pwd = pwd;
        this.name = name;
        this.job = job;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", empId='" + empId + '\'' +
                ", pwd='" + pwd + '\'' +
                ", name='" + name + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}
