package InnerClass;

public class AnonymousInnerClass {
    public static void main(String[] args) {
        Outer04 outer04 = new Outer04();
        outer04.method();

    }
}
class Outer04{
    private int n1 = 10;//属性
    public void method(){//方法
        //基于接口的匿名内部类
        //1:需求：想使用接口IA 并创建对象
        //2:传统方式：写一个类实现该接口并创建对象
        //   如果只需要使用一次接口  后面不再重复使用 定义一个类实现接口创建对象就有点浪费
        //3:所以需要匿名内部类简化！！！！！

//        InnerClass.IA tiger = new Tiger();
//        tiger.cry();
//        InnerClass.IA dog = new Dog();
//        dog.cry();

        //4:匿名类实现简化过程 tiger的编译类型是：IA类型  运行类型是：匿名内部类
        /*
        在底层 是 class xxxx implements InnerClass.IA{ 这里的xxxx一般是class InnerClass.Outer04$1 系统分配的名字
                    @Override
                    public void cry() {
                        System.out.println("老虎叫唤（匿名类）");
                    }
                }
        */
        IA tiger = new IA(){
            @Override
            public void cry() {
                System.out.println("老虎叫唤（匿名类）");
            }
        };
        System.out.println("tiger的运行类型 = "+tiger.getClass());//getClass返回该对象的运行时的类
        tiger.cry();



        //基于类的匿名内部类
        //father的编译类型是Father   运行类型是 InnerClass.Outer04$2
        //底层编译逻辑
        /*
            class outer04$2 extends InnerClass.Father{ }
        */
        Father father = new Father("jack"){
            @Override
            public void test() {
                System.out.println("匿名内部类重写了father的test方法");
            }
        };//记得加这个大括号 不加的话就是普通的那种创建对象了
        // 而匿名内部类则是用一个匿名对象实现继承或者接口的方法 而不在你看得见的代码里创建对象
        System.out.println("father匿名对象的运行类型是：" + father.getClass());
        father.test();

        //如果类是抽象类 那么必须重写内部的方法
    }
}

interface IA{
    public void cry();
}

//class Tiger implements InnerClass.IA{//只用一次的话这样很浪费
//
//    @Override
//    public void cry() {
//        System.out.println("老虎叫唤");
//    }
//}
//class Dog implements InnerClass.IA{//只用一次的话这样很浪费
//
//    @Override
//    public void cry() {
//        System.out.println("狗狗叫唤");
//    }
//}
class Father{

    public Father(String name){
        super();
    }
    public void test(){

    }
}