package InnerClass;

public class InnerClassExercise {
    public static void main(String[] args) {

        //当作实参传递
        f1(new IL() {
            @Override
            public void show() {
                System.out.println("这是一幅画");
            }
        });//注意这个分号   因为他是一条语句   这里传了一个匿名内部类
        //这样很方便 如果是传统方式的话要先写一个类 然后implements接口 再重写接口方法
        //接下来回到主函数 new这个类 并传入f1
    }

    //静态方法,形参是接口类型
    public static void f1(IL il){

    }
}

//接口
interface IL{
    void show();
}