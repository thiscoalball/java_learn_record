package service;

import Common.Message;
import Common.MessageType;
import DAO.AllGradesDAO;
import domain.AllGrades;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

@SuppressWarnings({"all"})
public class AllGradesService {
    private AllGradesDAO allGradesDAO = new AllGradesDAO();
    private String content = "";

    //获取班级成绩
    public Message getAllGradesByClassId(Message message) throws SQLException, IOException {
        //--------这里解析发送数据-------------------
        String senderName = message.getSender().split(" ")[0];
        String senderId = message.getSender().split(" ")[1];
        String classId = message.getContent();
        //-------向数据库请求数据
        List<AllGrades> allGrades = allGradesDAO.queryMulti("select * from allGrades where classid = ? ORDER BY (english + math + chinese) DESC", AllGrades.class, classId);
        //-------这里将数据封装成消息包并返回
        if (allGrades.isEmpty()) {
            content = "null";
            message.setContent(content);
        }
        Iterator<AllGrades> iterator = allGrades.iterator();
        while (iterator.hasNext()) {
            content += iterator.next().toString();
            System.out.println(content);
        }

        System.out.println("用户:" + senderName + "  请求获取" + classId + "班级成绩" + "\t\t\t" + message.getSendTime());
        Message message1 = new Message();
        message.setMesType(MessageType.MESSAGE_GET_CLASS_GRADE);
        message.setSender(message.getReceiver());
        message.setReceiver(message.getSender());
        message.setContent(content);
        content = "";
        return message;
    }

    //获取单个人的成绩
    public Message getOneGradeByIdAndName(Message message) throws SQLException {
        //--------这里解析发送数据-------------------
        String[] studentMessage = message.getContent().split(" ");
        String senderName = message.getSender().split(" ")[0];
        String senderId = message.getSender().split(" ")[1];
        String studentName = studentMessage[0];
        String studentId = studentMessage[1];
        //--------向数据库请求数据--------------------
        AllGrades allGrades = allGradesDAO.querySingle("select * from allGrades where name = ? and personId = ?",
                AllGrades.class, studentName, studentId);

        //---------这里将数据封装成消息包并返回
        if (allGrades == null) {
            content = "null";
            message.setContent(content);
            return message;
        }
        System.out.println("用户:" + senderName + "  请求获取" + studentName + " 的成绩" + "\t\t\t" + message.getSendTime());
        Message message1 = new Message();
        message1.setMesType(MessageType.MESSAGE_GET_CLASS_GRADE);
        message1.setSender(message.getReceiver());
        message1.setReceiver(message.getSender());
        message1.setContent(allGrades.toString());
        return message1;
    }

    //获取班级总分
    public Message getClassTotalRank(String classId, Message message) throws SQLException {
        System.out.println("用户：" + message.getSender().split(" ")[0] + " 请求获取班级总分排名\t\t" + message.getSendTime());
        String content = "班级总分 ";
        String sql = "SELECT personId, `name`, chinese,math,english,@curRank := @curRank + 1 AS rank\n" +
                "FROM allgrades p, (\n" +
                "SELECT @curRank := 0\n" +
                ") q WHERE classId = ? ORDER BY (english+math+chinese) DESC ";
        return setAllRank(message, sql, content, classId);
    }

    //获取班级数学排名
    public Message getClassMathRank(String classId, Message message) throws SQLException {
        System.out.println("用户：" + message.getSender().split(" ")[0] + " 请求获取班级数学排名\t\t" + message.getSendTime());
        String content = "班级数学 ";
        String sql = "SELECT personId, `name`, math, @curRank := @curRank + 1 AS rank\n" +
                "FROM allgrades p, (\n" +
                "SELECT @curRank := 0\n" +
                ") q WHERE classId = ? ORDER BY math DESC ";
        return setAllRank(message, sql, content, classId);
    }

    //获取班级语文排名
    public Message getClassChineseRank(String classId, Message message) throws SQLException {
        System.out.println("用户：" + message.getSender().split(" ")[0] + " 请求获取班级语文排名\t\t" + message.getSendTime());
        String content = "班级语文 ";
        String sql = "SELECT personId, `name`, chinese, @curRank := @curRank + 1 AS rank\n" +
                "FROM allgrades p, (\n" +
                "SELECT @curRank := 0\n" +
                ") q WHERE classId = ? ORDER BY chinese DESC ";
        return setAllRank(message, sql, content, classId);
    }

    //获取班级英语排名
    public Message getClassEnglishRank(String classId, Message message) throws SQLException {
        System.out.println("用户：" + message.getSender().split(" ")[0] + " 请求获取班级英语排名\t\t" + message.getSendTime());
        String content = "班级英语 ";
        String sql = "SELECT personId, `name`, english, @curRank := @curRank + 1 AS rank\n" +
                "FROM allgrades p, (\n" +
                "SELECT @curRank := 0\n" +
                ") q WHERE classId = ? ORDER BY english DESC  ";
        return setAllRank(message, sql, content, classId);
    }

    //获取年段总分排名
    public Message getGradeTotalRank(Message message) throws SQLException {
        System.out.println("用户：" + message.getSender().split(" ")[0] + " 请求获取年段总分排名\t\t" + message.getSendTime());
        String content = "年段总分 ";
        String sql = "SELECT personId, `name`, chinese,math,english,classId,@curRank := @curRank + 1 AS rank\n" +
                "FROM allgrades p, (\n" +
                "SELECT @curRank := 0\n" +
                ") q ORDER BY (english+math+chinese) DESC ";
        return setAllRank(message, sql, content);
    }

    //获取年段数学排名
    public Message getGradeMathRank(Message message) throws SQLException {
        System.out.println("用户：" + message.getSender().split(" ")[0] + " 请求获取年段数学排名\t\t" + message.getSendTime());
        String content = "年段数学 ";
        String sql = "SELECT personId, `name`, math,classId, @curRank := @curRank + 1 AS rank\n" +
                "FROM allgrades p, (\n" +
                "SELECT @curRank := 0\n" +
                ") q  ORDER BY math DESC  ";
        return setAllRank(message, sql, content);
    }

    //获取年段英语排名
    public Message getGradeEnglishRank(Message message) throws SQLException {
        System.out.println("用户：" + message.getSender().split(" ")[0] + " 请求获取年段英语排名\t\t" + message.getSendTime());
        String content = "年段英语 ";
        String sql = "SELECT personId, `name`, english, classId,@curRank := @curRank + 1 AS rank\n" +
                "FROM allgrades p, (\n" +
                "SELECT @curRank := 0\n" +
                ") q  ORDER BY english DESC \n ";
        return setAllRank(message, sql, content);
    }

    //获取年段语文排名
    public Message getGradeChineseRank(Message message) throws SQLException {
        System.out.println("用户：" + message.getSender().split(" ")[0] + " 请求获取年段语文排名\t\t" + message.getSendTime());
        String content = "年段语文 ";
        String sql = "SELECT personId, `name`, chinese,classId, @curRank := @curRank + 1 AS rank\n" +
                "FROM allgrades p, (\n" +
                "SELECT @curRank := 0\n" +
                ") q  ORDER BY chinese DESC ";
        return setAllRank(message, sql, content);
    }

    //获取单人在班级总分排名
    public Message getOneClassTotalRank(String classId, Message message) throws SQLException {
        System.out.println("用户：" + message.getSender().split(" ")[0] + " 请求获取" + message.getContent().split(" ")[2] + " 班级总分排名\t\t" + message.getSendTime());
        String content = "班级总分 ";
        String sql = "SELECT personId, `name`, chinese,math,english,@curRank := @curRank + 1 AS rank\n" +
                "FROM allgrades p, (\n" +
                "SELECT @curRank := 0\n" +
                ") q WHERE classId = ? ORDER BY (english+math+chinese) DESC ";
        return setOnetRank(message, sql, content, classId);
    }

    //获取单人在班级数学排名
    public Message getOneClassMathRank(String classId, Message message) throws SQLException {
        System.out.println("用户：" + message.getSender().split(" ")[0] + " 请求获取 " + message.getContent().split(" ")[2] + " 班级数学排名\t\t" + message.getSendTime());
        String content = "班级数学 ";
        String sql = "SELECT personId, `name`, math, @curRank := @curRank + 1 AS rank\n" +
                "FROM allgrades p, (\n" +
                "SELECT @curRank := 0\n" +
                ") q WHERE classId = ? ORDER BY math DESC ";
        return setOnetRank(message, sql, content, classId);
    }

    //获取单人在班级语文排名
    public Message getOneClassChineseRank(String classId, Message message) throws SQLException {
        System.out.println("用户：" + message.getSender().split(" ")[0] + " 请求获取 " + message.getContent().split(" ")[2] + " 班级语文排名\t\t" + message.getSendTime());
        String content = "班级语文 ";
        String sql = "SELECT personId, `name`, chinese, @curRank := @curRank + 1 AS rank\n" +
                "FROM allgrades p, (\n" +
                "SELECT @curRank := 0\n" +
                ") q WHERE classId = ? ORDER BY chinese DESC ";
        return setOnetRank(message, sql, content, classId);
    }

    //获取单人在班级英语排名
    public Message getOneClassEnglishRank(String classId, Message message) throws SQLException {
        System.out.println("用户：" + message.getSender().split(" ")[0] + " 请求获取 " + message.getContent().split(" ")[2] + " 班级英语排名\t\t" + message.getSendTime());
        String content = "班级英语 ";
        String sql = "SELECT personId, `name`, english, @curRank := @curRank + 1 AS rank\n" +
                "FROM allgrades p, (\n" +
                "SELECT @curRank := 0\n" +
                ") q WHERE classId = ? ORDER BY english DESC  ";
        return setOnetRank(message, sql, content, classId);
    }

    //获取单人在年段总分排名
    public Message getOneGradeTotalRank(Message message) throws SQLException {
        System.out.println("用户：" + message.getSender().split(" ")[0] + " 请求获取 " + message.getContent().split(" ")[2] + " 年段总分排名\t\t" + message.getSendTime());
        String content = "年段总分 ";
        String sql = "SELECT personId, `name`, chinese,math,english,classId,@curRank := @curRank + 1 AS rank\n" +
                "FROM allgrades p, (\n" +
                "SELECT @curRank := 0\n" +
                ") q ORDER BY (english+math+chinese) DESC ";
        return setOnetRank(message, sql, content);
    }

    //获取单人在年段数学排名
    public Message getOneGradeMathRank(Message message) throws SQLException {
        System.out.println("用户：" + message.getSender().split(" ")[0] + " 请求获取 " + message.getContent().split(" ")[2] + " 年段数学排名\t\t" + message.getSendTime());
        String content = "年段数学 ";
        String sql = "SELECT personId, `name`, math,classId, @curRank := @curRank + 1 AS rank\n" +
                "FROM allgrades p, (\n" +
                "SELECT @curRank := 0\n" +
                ") q  ORDER BY math DESC  ";
        return setOnetRank(message, sql, content);
    }

    //获取单人在年段英语排名
    public Message getOneGradeEnglishRank(Message message) throws SQLException {
        System.out.println("用户：" + message.getSender().split(" ")[0] + " 请求获取 " + message.getContent().split(" ")[2] + " 年段英语排名\t\t" + message.getSendTime());
        String content = "年段英语 ";
        String sql = "SELECT personId, `name`, english, classId,@curRank := @curRank + 1 AS rank\n" +
                "FROM allgrades p, (\n" +
                "SELECT @curRank := 0\n" +
                ") q  ORDER BY english DESC \n ";
        return setOnetRank(message, sql, content);
    }

    //获取单人在年段语文排名
    public Message getOneGradeChineseRank(Message message) throws SQLException {
        System.out.println("用户：" + message.getSender().split(" ")[0] + " 请求获取 " + message.getContent().split(" ")[2] + " 年段语文排名\t\t" + message.getSendTime());
        String content = "年段语文 ";
        String sql = "SELECT personId, `name`, chinese,classId, @curRank := @curRank + 1 AS rank\n" +
                "FROM allgrades p, (\n" +
                "SELECT @curRank := 0\n" +
                ") q  ORDER BY chinese DESC ";
        return setOnetRank(message, sql, content);
    }

    //多人排名类型设置
    private Message setAllRank(Message message, String sql, String content, Object... classId) throws SQLException {
        int i = 1;
        String contentCopy = content;
        Message message1 = new Message();
        List<AllGrades> allGrades = allGradesDAO.queryMulti(sql, AllGrades.class, classId);
        Iterator<AllGrades> iterator = allGrades.iterator();

        while (iterator.hasNext()) {
            AllGrades oneGrade = iterator.next();
            switch (contentCopy) {
                case "年段语文 ":
                    content += i + "\t" +
                            oneGrade.getName() + "\t\t" +
                            oneGrade.getPersonId() + "\t" +
                            oneGrade.getClassId() + "\t\t" +
                            (oneGrade.getChinese()) + "\t" +
                            "\n";
                    break;
                case "班级语文 ":
                    content += i + "\t" +
                            oneGrade.getName() + "\t\t" +
                            oneGrade.getPersonId() + "\t" +
                            (oneGrade.getChinese()) + "\t" +
                            "\n";
                    break;
                case "班级英语 ":
                    content += i + "\t" +
                            oneGrade.getName() + "\t\t" +
                            oneGrade.getPersonId() + "\t" +
                            (oneGrade.getEnglish()) + "\t" +
                            "\n";
                    break;
                case "年段英语 ":
                    content += i + "\t" +
                            oneGrade.getName() + "\t\t" +
                            oneGrade.getPersonId() + "\t" +
                            oneGrade.getClassId() + "\t\t" +
                            (oneGrade.getEnglish()) + "\t" +
                            "\n";
                    break;
                case "年段数学 ":
                    content += i + "\t" +
                            oneGrade.getName() + "\t\t" +
                            oneGrade.getPersonId() + "\t" +
                            oneGrade.getClassId() + "\t\t" +
                            (oneGrade.getMath()) + "\t" +
                            "\n";
                    break;
                case "班级数学 ":
                    content += i + "\t" +
                            oneGrade.getName() + "\t\t" +
                            oneGrade.getPersonId() + "\t" +
                            (oneGrade.getMath()) + "\t" +
                            "\n";
                    break;
                case "年段总分 ":
                    content += i + "\t" +
                            oneGrade.getName() + "\t\t" +
                            oneGrade.getPersonId() + "\t" +
                            oneGrade.getClassId() + "\t\t" +
                            oneGrade.getChinese() + "\t" +
                            oneGrade.getMath() + "\t" +
                            oneGrade.getEnglish() + "\t" +
                            (oneGrade.getChinese() + oneGrade.getMath() + oneGrade.getEnglish()) + "\t" +
                            "\n";
                    break;
                case "班级总分 ":
                    content += i + "\t" +
                            oneGrade.getName() + "\t\t" +
                            oneGrade.getPersonId() + "\t" +
                            oneGrade.getChinese() + "\t" +
                            oneGrade.getMath() + "\t" +
                            oneGrade.getEnglish() + "\t" +
                            (oneGrade.getChinese() + oneGrade.getMath() + oneGrade.getEnglish()) + "\t" +
                            "\n";
                    break;
            }
            i++;
        }
        message1.setContent(content);
        message1.setReceiver(message.getSender().split(" ")[0]);
        message1.setSender(message.getReceiver());
        message1.setSender(message.getSendTime());
        message1.setMesType(message.getMesType());
        return message1;
    }

    //单人排名类型设置
    private Message setOnetRank(Message message, String sql, String content, Object... classId) throws SQLException {
        int i = 1;
        Message message1 = new Message();
        String studentName = message.getContent().split(" ")[2];
        String studentId = message.getContent().split(" ")[3];

        if (!studentIsExist(studentName, studentId)) {
            System.out.println("查找不到该用户 已返回\t\t" + message.getSendTime());
            message1.setContent("null");
            message1.setReceiver(message.getSender().split(" ")[0]);
            message1.setSender(message.getReceiver());
            message1.setSender(message.getSendTime());
            message1.setMesType(message.getMesType());
            return message1;
        }
        List<AllGrades> allGrades = allGradesDAO.queryMulti(sql, AllGrades.class, classId);
        Iterator<AllGrades> iterator = allGrades.iterator();

        while (iterator.hasNext()) {
            AllGrades oneGrade = iterator.next();
            switch (content) {
                case "年段语文 ":
                    if (oneGrade.getPersonId().equals(studentId)) {
                        content += i + "\t" +
                                oneGrade.getName() + "\t\t" +
                                oneGrade.getPersonId() + "\t" +
                                oneGrade.getClassId() + "\t\t" +
                                (oneGrade.getChinese()) + "\t" +
                                "\n";
                    }
                    break;
                case "班级语文 ":
                    if (oneGrade.getPersonId().equals(studentId)) {
                        content += i + "\t" +
                                oneGrade.getName() + "\t\t" +
                                oneGrade.getPersonId() + "\t" +
                                (oneGrade.getChinese()) + "\t" +
                                "\n";
                    }
                    break;
                case "班级英语 ":
                    if (oneGrade.getPersonId().equals(studentId)) {
                        content += i + "\t" +
                                oneGrade.getName() + "\t\t" +
                                oneGrade.getPersonId() + "\t" +
                                (oneGrade.getEnglish()) + "\t" +
                                "\n";
                    }
                    break;
                case "年段英语 ":
                    if (oneGrade.getPersonId().equals(studentId)) {
                        content += i + "\t" +
                                oneGrade.getName() + "\t\t" +
                                oneGrade.getPersonId() + "\t" +
                                oneGrade.getClassId() + "\t\t" +
                                (oneGrade.getEnglish()) + "\t" +
                                "\n";
                    }
                    break;
                case "年段数学 ":
                    if (oneGrade.getPersonId().equals(studentId)) {
                        content += i + "\t" +
                                oneGrade.getName() + "\t\t" +
                                oneGrade.getPersonId() + "\t" +
                                oneGrade.getClassId() + "\t\t" +
                                (oneGrade.getMath()) + "\t" +
                                "\n";
                    }
                    break;
                case "班级数学 ":
                    if (oneGrade.getPersonId().equals(studentId)) {
                        content += i + "\t" +
                                oneGrade.getName() + "\t\t" +
                                oneGrade.getPersonId() + "\t" +
                                (oneGrade.getMath()) + "\t" +
                                "\n";
                    }
                    break;
                case "年段总分 ":
                    if (oneGrade.getPersonId().equals(message.getContent().split(" ")[3])) {
                        content += i + "\t" +
                                oneGrade.getName() + "\t\t" +
                                oneGrade.getPersonId() + "\t" +
                                oneGrade.getClassId() + "\t\t" +
                                oneGrade.getChinese() + "\t" +
                                oneGrade.getMath() + "\t" +
                                oneGrade.getEnglish() + "\t" +
                                (oneGrade.getChinese() + oneGrade.getMath() + oneGrade.getEnglish()) + "\t" +
                                "\n";
                    }
                    break;
                case "班级总分 ":
                    if (oneGrade.getPersonId().equals(message.getContent().split(" ")[3])) {
                        content += i + "\t" +
                                oneGrade.getName() + "\t\t" +
                                oneGrade.getPersonId() + "\t" +
                                oneGrade.getChinese() + "\t" +
                                oneGrade.getMath() + "\t" +
                                oneGrade.getEnglish() + "\t" +
                                (oneGrade.getChinese() + oneGrade.getMath() + oneGrade.getEnglish()) + "\t" +
                                "\n";
                    }
                    break;
            }
            i++;
        }
        message1.setContent(content);
        message1.setReceiver(message.getSender().split(" ")[0]);
        message1.setSender(message.getReceiver());
        message1.setSender(message.getSendTime());
        message1.setMesType(message.getMesType());
        return message1;
    }

    //获取排名
    public Message getRank(Message message) throws SQLException {
        //获取想要的排名的类型 利用工厂模式实例化出需要的
        Message message1 = RankFactory.rankFactory(message);
        if (message1 != null) {
            return message1;
        } else {
            message1.setContent("null");
            return message1;
        }
    }

    //该生成绩是否存在
    public boolean studentIsExist(String name, String id) throws SQLException {
        String sql = "SELECT * FROM allgrades WHERE NAME=? AND personId = ?";
        AllGrades allGrades = allGradesDAO.querySingle(sql, AllGrades.class, name, id);
        if (allGrades == null) {
            return false;
        } else {
            return true;
        }
    }

}
