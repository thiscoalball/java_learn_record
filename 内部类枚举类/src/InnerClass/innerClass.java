package InnerClass;

public class innerClass {
    public static void main(String[] args) {

    }
}

class Outer{
    private int n1 = 100; //属性
    public void m1(){     //方法
        System.out.println("m1()");
    }
    {
        System.out.println("代码块"); //代码块
    }

    public Outer(int n1) { //构造器
        this.n1 = n1;
    }
    class inner{//内部类

    }
}