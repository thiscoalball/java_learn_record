package 懒汉式;

public class SingleTon02 {
    public static void main(String[] args) {
        Cat cat1 = Cat.getcat();
        System.out.println(cat1);

        Cat cat2 = Cat.getcat();
        System.out.println(cat2);
    }
}


//希望程序运行中只能创建一个cat对象
class Cat{

    private String name;
    private static Cat cat;//默认为空 这个static很重要 然后我们先不创建它
    //1:构造器私有化
    //2:定义一个static静态属性对象
    //3:提供一个public的static方法 返回一个cat对象
    private Cat(String name) {
        this.name = name;
    }
    public static Cat getcat(){
        if(cat==null) {
           cat = new Cat("meow");//如果还没有创建则创建
        }
        return cat;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                '}';
    }
}