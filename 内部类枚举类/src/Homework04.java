
public class Homework04 {
    public static void main(String[] args) {
        CellPhone cellPhone = new CellPhone();

        cellPhone.testwork(new Calculator() {
            @Override
            public double work(double n1, double n2) {
                return n1 + n2;
            }
        },10,8); //牛皮
        cellPhone.testwork(new Calculator() {
            @Override
            public double work(double n1, double n2) {
                return 10 * 10;
            }
        },10,10);
    }
}
interface Calculator{
    public double work(double n1,double n2);//具体加减乘除运算我们可以在重写的时候指定
}

class CellPhone{

    public void testwork(Calculator calculator,double n1, double n2) {

        double result = calculator.work(n1, n2);
        System.out.println("计算后的结果为= " + result);
    }
}
