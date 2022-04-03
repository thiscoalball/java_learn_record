package domain;
/**
 * 映射数据库的所有成绩表
 * CREATE TABLE allGrades(
 * 	id INT PRIMARY KEY AUTO_INCREMENT, #自增主键
 * 	`NAME` VARCHAR(50) NOT NULL DEFAULT '',  #姓名
 * 	personId VARCHAR(50) NOT NULL DEFAULT '',#账号
 * 	classId VARCHAR(8) NOT NULL DEFAULT '',#班级Id
 * 	English FLOAT NOT NULL DEFAULT 0, #英语分
 * 	Math FLOAT NOT NULL DEFAULT 0,#数学分
 * 	Chinese FLOAT NOT NULL DEFAULT 0#语文分
 * )
 * */
@SuppressWarnings({"all"})
public class AllGrades {
    private Integer id;
    private String name;
    private String personId;
    private String classId;
    private Float english;
    private Float math;
    private Float chinese;

    public AllGrades() {
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

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public Float getEnglish() {
        return english;
    }

    public void setEnglish(Float english) {
        this.english = english;
    }

    public Float getMath() {
        return math;
    }

    public void setMath(Float math) {
        this.math = math;
    }

    public Float getChinese() {
        return chinese;
    }

    public void setChinese(Float chinese) {
        this.chinese = chinese;
    }

    @Override
    public String toString() {
        return "AllGrades{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", personId='" + personId + '\'' +
                ", classId='" + classId + '\'' +
                ", english=" + english +
                ", math=" + math +
                ", chinese=" + chinese +
                '}';
    }
}
