package 类变量;

public class ChileGame {
    public static void main(String[] args) {
        //定义一个变量统计有多少加入游戏
        //然后我们发现count类独立于对象 导致以后访问很麻烦 因为没有面向对象
        //所以 我们引出类变量/静态变量
        // 该变量是对象共享的
        //类变量随着类的加载而加载 所以没有创建对象实例也可以使用

        //什么时候需要使用类变量？
        //当需要某个类的所有对象都共享一个变量的时候
        //类变量是所有对象共享的   实例对象是每个对象独有的


        Child child1 = new Child("白骨精");
        child1.join();

        Child child2 = new Child("老鼠精");
        child2.join();

        Child child3 = new Child("狐狸精");
        child3.join();

        //可以直接用类型访问
        System.out.println("共有" + Child.count + "个小孩加入了游戏");

    }
}
class Child{
    static int count = 0; //类\静态变量
    private String name;
    public Child(String name) {
        this.name = name;
    }
    public void join(){
        System.out.println(name + "加入了游戏");
        count++;//静态变量的妙用；
    }
}
