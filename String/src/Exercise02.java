import java.util.Arrays;
import java.util.Scanner;

public class Exercise02 {
    //输入商品名称   要求打印效果实例
    //商品名  商品价格
    //手机    123,564.58；
    //整数部分三位之间用逗号隔开
    public static void main(String[] args) {

        String price = "123564.58";
        StringBuffer stringBuffer = new StringBuffer(price);
        //1 找到小数点
        //System.out.println(i);
        //2 在该位置的前3位插入逗号
        for (int i = stringBuffer.lastIndexOf(".") ;i>3 ; i -=3) {
            stringBuffer = stringBuffer.insert(i -3,",");
        }
        System.out.println(stringBuffer);

    }
}
