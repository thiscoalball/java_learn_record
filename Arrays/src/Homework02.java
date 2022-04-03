import java.util.Locale;

public class Homework02 {
    public static void main(String[] args) {
        String str = "Chen zi ye";
        printName(str);
    }
    //思路分析 进行分割  然后格式化
    public static void printName(String str){
        if(str == null){
            System.out.println("不能为空");
            return;
        }
        String[] names = str.split(" ");
        if(names.length != 3){
            System.out.println("输入的字符串格式不正确");
            return;
        }
        String format = String.format("%s,%s.%c",names[2],
                names[0],names[1].toUpperCase().charAt(0));
        System.out.println(format);
    }
}


