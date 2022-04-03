package service;

import Common.Message;
import domain.User;

import java.sql.SQLException;

//工厂类 生成不同的排名类型
public class RankFactory {
    static AllGradesService allGradesService = new AllGradesService();

    public static Message rankFactory(Message message) throws SQLException {
        String classId = message.getContent().split(" ")[0];
        String type = message.getContent().split(" ")[1];
        //解包
        switch (type) {
            case "ClassTotalRank":
                return allGradesService.getClassTotalRank(classId, message);
            case "ClassMathRank":
                return allGradesService.getClassMathRank(classId, message);
            case "ClassChineseRank":
                return allGradesService.getClassChineseRank(classId, message);
            case "ClassEnglishRank":
                return allGradesService.getClassEnglishRank(classId, message);
            case "GradeTotalRank":
                return allGradesService.getGradeTotalRank(message);
            case "GradeMathRank":
                return allGradesService.getGradeMathRank(message);
            case "GradeChineseRank":
                return allGradesService.getGradeChineseRank(message);
            case "GradeEnglishRank":
                return allGradesService.getGradeEnglishRank(message);
            case "OneClassTotalRank":
                return allGradesService.getOneClassTotalRank(classId, message);
            case "OneClassMathRank":
                return allGradesService.getOneClassMathRank(classId, message);
            case "OneClassChineseRank":
                return allGradesService.getOneClassChineseRank(classId, message);
            case "OneClassEnglishRank":
                return allGradesService.getOneClassEnglishRank(classId, message);
            case "OneGradeTotalRank":
                return allGradesService.getOneGradeTotalRank(message);
            case "OneGradeMathRank":
                return allGradesService.getOneGradeMathRank(message);
            case "OneGradeChineseRank":
                return allGradesService.getOneGradeChineseRank(message);
            case "OneGradeEnglishRank":
                return allGradesService.getOneGradeEnglishRank(message);
        }
        return null;
    }
}
