package exception;

import java.util.Scanner;

public class Exception02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = 0;
        while (true){
            System.out.println("请输入一个整数");
            try {
                num = Integer.parseInt(scanner.next());//这可能出现异常
                break;              //没有出现异常就跳出
            } catch (NumberFormatException e) {
                System.out.println("你输入的不是一个整数");
            }
        }
        System.out.println("输入完成，你输入的数是："+num);
    }
}
