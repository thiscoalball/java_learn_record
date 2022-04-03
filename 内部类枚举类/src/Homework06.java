public class Homework06 {
    public static void main(String[] args) {
        Person tang = new Person("tang", new Horse());
        tang.common(); //一般情况
        tang.passRiver();
        tang.common();
        tang.passRiver();
    }
}


//定义接口
interface Vehicles{
    public void work();
}

//交通工具 马
class Horse implements Vehicles{
    @Override
    public void work() {
        System.out.println("马匹被使用");
    }
}

//交通工具 船
class Boat implements Vehicles{
    @Override
    public void work() {
        System.out.println("船只被使用");
    }
}

//交通工具工厂类
class VehiclesFactory{

    //一点儿优化 假设 马始终是同一匹
    private static Horse horse = new Horse();//这里要加static 为了和底下静态函数匹配 不然静态方法无法调用非静态属性
    //要求两个办法获得交通工具
    public static Horse getHorse(){//用static关键字修饰 这样就不用创建工厂类 可以直接用类名调用方法
        return horse;
    }
    public static Boat getBoat(){
        return new Boat();
    }
}

//人类
class Person{
    private String name;
    private Vehicles vehicles;

    public Person(String name, Vehicles vehicles) {
        this.name = name;
        this.vehicles = vehicles;
    }
    //题目要求实例化对象唐僧 一般情况下 用 Horse作为交通工具   过河的情况下用船

    //这个函数就体现了编程思想 把我们所要做的事情集合成一个方法   和前面的类融汇贯通起来 而且符合开闭
    //并且让代码看起来更加简洁 可读性更高 就是把new船或者new马的操作封装到这个工厂里
    public void passRiver(){
        if(!(vehicles instanceof Boat)) {//不是船或者是空 就获取船
            vehicles = VehiclesFactory.getBoat();
        }
        vehicles.work();
    }
    public void common(){
        if(!(vehicles instanceof Horse)) {//不是马或者是空 就获取马
            //这里使用多态  动态绑定
            vehicles = VehiclesFactory.getHorse();
        }
        vehicles.work();//接口的解耦的特性
    }
}
