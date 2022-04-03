package View;

import Service.MessageService;
import Util.Utility;
import domain.User;

import java.io.IOException;

public class ChooseRankView {
    private boolean loop = true;
    //用于接收键盘输入
    private String key = "";
    private MessageService messageService = new MessageService();

    //班级总分排名
    private void getClassTotalRank(String classId,String personId,String personName,String studentName,String studentId) throws IOException, InterruptedException {
        messageService.getClassGradeRank(personName,personId,classId,"ClassTotalRank",studentName,studentId);
        Thread.sleep(200);
    }

    //班级数学排名
    private void getClassMathRank(String classId,String personId,String personName,String studentName,String studentId) throws IOException, InterruptedException {
        messageService.getClassGradeRank(personName,personId,classId,"ClassMathRank",studentName,studentId);
        Thread.sleep(200);
    }

    //班级语文排名
    private void getClassChineseRank(String classId,String personId,String personName,String studentName,String studentId) throws IOException, InterruptedException {
        messageService.getClassGradeRank(personName,personId,classId,"ClassChineseRank",studentName,studentId);
        Thread.sleep(200);
    }

    //班级英语排名
    private void getClassEnglishRank(String classId,String personId,String personName,String studentName,String studentId) throws IOException, InterruptedException {
        messageService.getClassGradeRank(personName,personId,classId,"ClassEnglishRank",studentName,studentId);
        Thread.sleep(200);
    }

    //年段总分排名
    private void getGradeTotalRank(String classId,String personId,String personName,String studentName,String studentId) throws IOException, InterruptedException {
        messageService.getClassGradeRank(personName,personId,classId,"GradeTotalRank",studentName,studentId);
        Thread.sleep(200);
    }

    //年段语文排名
    private void getGradeChineseRank(String classId,String personId,String personName,String studentName,String studentId) throws IOException, InterruptedException {
        messageService.getClassGradeRank(personName,personId,classId,"GradeChineseRank",studentName,studentId);
        Thread.sleep(200);
    }

    //年段数学排名
    private void getGradeMathRank(String classId,String personId,String personName,String studentName,String studentId) throws IOException, InterruptedException {
        messageService.getClassGradeRank(personName,personId,classId,"GradeMathRank",studentName,studentId);
        Thread.sleep(200);
    }

    //年段英语排名
    private void getGradeEnglishRank(String classId,String personId,String personName,String studentName,String studentId) throws IOException, InterruptedException {
        messageService.getClassGradeRank(personName,personId,classId,"GradeEnglishRank",studentName,studentId);
        Thread.sleep(200);
    }

    //单人班级总分排名
    private void getOneClassTotalRank(String classId,String personId,String personName,String studentName,String studentId) throws IOException, InterruptedException {
        messageService.getClassGradeRank(personName,personId,classId,"OneClassTotalRank",studentName,studentId);
        Thread.sleep(200);
    }

    //单人班级数学排名
    private void getOneClassMathRank(String classId,String personId,String personName,String studentName,String studentId) throws IOException, InterruptedException {
        messageService.getClassGradeRank(personName,personId,classId,"OneClassMathRank",studentName,studentId);
        Thread.sleep(200);
    }

    //单人班级语文总分排名
    private void getOneClassChineseRank(String classId,String personId,String personName,String studentName,String studentId) throws IOException, InterruptedException {
        messageService.getClassGradeRank(personName,personId,classId,"OneClassChineseRank",studentName,studentId);
        Thread.sleep(200);
    }

    //单人班级英语排名
    private void getOneClassEnglishRank(String classId,String personId,String personName,String studentName,String studentId) throws IOException, InterruptedException {
        messageService.getClassGradeRank(personName,personId,classId,"OneClassEnglishRank",studentName,studentId);
        Thread.sleep(200);
    }

    //单人年段总分排名
    private void getOneGradeTotalRank(String classId,String personId,String personName,String studentName,String studentId) throws IOException, InterruptedException {
        messageService.getClassGradeRank(personName,personId,classId,"OneGradeTotalRank",studentName,studentId);
        Thread.sleep(200);
    }

    //单人年段语文排名
    private void getOneGradeChineseRank(String classId,String personId,String personName,String studentName,String studentId) throws IOException, InterruptedException {
        messageService.getClassGradeRank(personName,personId,classId,"OneGradeChineseRank",studentName,studentId);
        Thread.sleep(200);
    }

    //单人年段数学排名
    private void getOneGradeMathRank(String classId,String personId,String personName,String studentName,String studentId) throws IOException, InterruptedException {
        messageService.getClassGradeRank(personName,personId,classId,"OneGradeMathRank",studentName,studentId);
        Thread.sleep(200);
    }

    //单人年段英语排名
    private void getOneGradeEnglishRank(String classId,String personId,String personName,String studentName,String studentId) throws IOException, InterruptedException {
        messageService.getClassGradeRank(personName,personId,classId,"OneGradeEnglishRank",studentName,studentId);
        Thread.sleep(200);
    }

    public void chooseTotalMenu(String classId,String personId,String personName,String studentName,String studentId) throws IOException, InterruptedException {
        while (loop) {
            System.out.println("-------------请选择你要查看的排名类型-------------");
            System.out.println("\t\t\t1 查询班级总分排名");
            System.out.println("\t\t\t2 查询班级语文排名");
            System.out.println("\t\t\t3 查询班级数学排名");
            System.out.println("\t\t\t4 查询班级英语排名");
            System.out.println("\t\t\t5 查询年段总分排名");
            System.out.println("\t\t\t6 查询年段语文排名");
            System.out.println("\t\t\t7 查询年段数学排名");
            System.out.println("\t\t\t8 查询年段英语排名");
            System.out.println("\t\t\t9 退出 ");
            System.out.println("请输入你的选择:");
            key = Utility.readString(1);
            switch (key) {
                case "1":
                    getClassTotalRank(classId,personId,personName,studentName,studentId);
                    break;
                case "2":
                    getClassChineseRank(classId, personId,personName,studentName,studentId);
                    break;
                case "3":
                    getClassMathRank(classId, personId,personName,studentName,studentId);
                    break;
                case "4":
                    getClassEnglishRank(classId, personId,personName,studentName,studentId);
                    break;
                case "5":
                    getGradeTotalRank(classId,personId,personName,studentName,studentId);
                    break;
                case "6":
                    getGradeChineseRank(classId,personId,personName,studentName,studentId);
                    break;
                case "7":
                    getGradeMathRank(classId,personId,personName,studentName,studentId);
                    break;
                case "8":
                    getGradeEnglishRank(classId,personId,personName,studentName,studentId);
                    break;
                case "9":
                    System.out.println("退出");
                    return;
            }
        }
    }


    public void chooseOneMenu(String classId,String personId,String personName,String studentName,String studentId) throws IOException, InterruptedException {
        while (loop) {
            System.out.println("-------------请选择你要查看的排名类型-------------");
            System.out.println("\t\t\t1 查询该生班级总分排名");
            System.out.println("\t\t\t2 查询该生班级语文排名");
            System.out.println("\t\t\t3 查询该生班级数学排名");
            System.out.println("\t\t\t4 查询该生班级英语排名");
            System.out.println("\t\t\t5 查询该生年段总分排名");
            System.out.println("\t\t\t6 查询该生年段语文排名");
            System.out.println("\t\t\t7 查询该生年段数学排名");
            System.out.println("\t\t\t8 查询该生年段英语排名");
            System.out.println("\t\t\t9 退出 ");
            System.out.println("请输入你的选择:");
            key = Utility.readString(1);
            switch (key) {
                case "1":
                    getOneClassTotalRank(classId,personId,personName,studentName,studentId);
                    break;
                case "2":
                    getOneClassChineseRank(classId, personId,personName,studentName,studentId);
                    break;
                case "3":
                    getOneClassMathRank(classId, personId,personName,studentName,studentId);
                    break;
                case "4":
                    getOneClassEnglishRank(classId, personId,personName,studentName,studentId);
                    break;
                case "5":
                    getOneGradeTotalRank(classId,personId,personName,studentName,studentId);
                    break;
                case "6":
                    getOneGradeChineseRank(classId,personId,personName,studentName,studentId);
                    break;
                case "7":
                    getOneGradeMathRank(classId,personId,personName,studentName,studentId);
                    break;
                case "8":
                    getOneGradeEnglishRank(classId,personId,personName,studentName,studentId);
                    break;
                case "9":
                    System.out.println("退出");
                    return;
            }
        }
    }
}
