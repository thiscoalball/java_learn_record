package domain;
/**
 * 映射数据库的class表
 * CREATE TABLE class(
 * 	id INT PRIMARY KEY AUTO_INCREMENT, #自增主键
 * 	classId VARCHAR(50) NOT NULL DEFAULT '',#班级Id
 * 	highestEnglish FLOAT NOT NULL DEFAULT 0, #英语最高分
 * 	highestMath FLOAT NOT NULL DEFAULT 0,#数学最高分
 * 	highestChinese FLOAT NOT NULL DEFAULT 0,#语文最高分
 * 	average FLOAT NOT NULL DEFAULT 0,#班级平均分
 * 	highestEnglishName VARCHAR(50) NOT NULL DEFAULT '',#英语最高分的人
 * 	highestMathName VARCHAR(50) NOT NULL DEFAULT '',#数学最高分的人
 * 	highestChineseName VARCHAR(50) NOT NULL DEFAULT '',#语文最高分的人
 * 	highestAverageName VARCHAR(50) NOT NULL DEFAULT '',#平均分最高的人
 * 	chargeTeacher VARCHAR(32) NOT NULL DEFAULT ''#班主任
 * );
 * */
@SuppressWarnings({"all"})
public class Class_ {
    private Integer id;
    private String classId;
    private Float highestEnglish;
    private Float highestMath;
    private Float highestChinese;
    private Float average;
    private String highestEnglishName;
    private String highestMathName;
    private String highestChineseName;
    private String highestAverageName;
    private String chargeTeacher;

    public Class_() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public Float getHighestEnglish() {
        return highestEnglish;
    }

    public void setHighestEnglish(Float highestEnglish) {
        this.highestEnglish = highestEnglish;
    }

    public Float getHighestMath() {
        return highestMath;
    }

    public void setHighestMath(Float highestMath) {
        this.highestMath = highestMath;
    }

    public Float getHighestChinese() {
        return highestChinese;
    }

    public void setHighestChinese(Float highestChinese) {
        this.highestChinese = highestChinese;
    }

    public Float getAverage() {
        return average;
    }

    public void setAverage(Float average) {
        this.average = average;
    }

    public String getHighestEnglishName() {
        return highestEnglishName;
    }

    public void setHighestEnglishName(String highestEnglishName) {
        this.highestEnglishName = highestEnglishName;
    }

    public String getHighestMathName() {
        return highestMathName;
    }

    public void setHighestMathName(String highestMathName) {
        this.highestMathName = highestMathName;
    }

    public String getHighestChineseName() {
        return highestChineseName;
    }

    public void setHighestChineseName(String highestChineseName) {
        this.highestChineseName = highestChineseName;
    }

    public String getHighestAverageName() {
        return highestAverageName;
    }

    public void setHighestAverageName(String highestAverageName) {
        this.highestAverageName = highestAverageName;
    }

    public String getChargeTeacher() {
        return chargeTeacher;
    }

    public void setChargeTeacher(String chargeTeacher) {
        this.chargeTeacher = chargeTeacher;
    }

    @Override
    public String toString() {
        return "Class_{" +
                "id=" + id +
                ", classId='" + classId + '\'' +
                ", highestEnglish=" + highestEnglish +
                ", highestMath=" + highestMath +
                ", highestChinese=" + highestChinese +
                ", average=" + average +
                ", highestEnglishName='" + highestEnglishName + '\'' +
                ", highestMathName='" + highestMathName + '\'' +
                ", highestChineseName='" + highestChineseName + '\'' +
                ", highestAverageName='" + highestAverageName + '\'' +
                ", chargeTeacher='" + chargeTeacher + '\'' +
                '}';
    }
}
