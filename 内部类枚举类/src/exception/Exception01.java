package exception;

public class Exception01 {
    public static void main(String[] args) {
        int num1 = 10;
        int num2 = 0;
        //除数不能为0，
        //当程序员认为一段代码可能会出问题的时候 利用try-catch异常处理机制来解决
        //保证程序的健壮性
        //ctrl + shift + t
        //出现问题过后程序还是会继续往下执行

        try {
            int res = num1/num2;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("异常的情况为：" + e.getMessage());
        }

        System.out.println("hhh");
    }
}
