package 代码块;

public class CodeBlock01Detail {
    public static void main(String[] args) {
        AA aa = new AA();
        System.out.println(Cat.n1);//使用类的静态成员时 静态代码块也会被加载
    }
}


//static 静态代码块 作用就是对类进行初始化 随着类的加载而执行 并且只会执行一次
//如果是普通代码块的话 每创建一个对象就会执行
//普通代码块 在创建对象实例的时候 会被隐式的调用   被创建一次 就会调用一次
//如果只使用类的静态成员 那么普通代码块则不会被执行
class BB{
    static{
        System.out.println("bb静态代码块1被执行");
    }
}
class AA extends BB{
    static {
        System.out.println("aa的静态代码块1运行");//会先执行父类bb的静态代码块
    }
}

class Cat{
    public static int n1 = 999;
    static {
        System.out.println("ca1的静态代码块1被执行");
    }
}