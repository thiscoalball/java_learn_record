package encapsulation;

public class Encapsulation01 {
    public static void main(String[] args) {
        Person person = new Person();
        //person.age = 3000; 这段是错误的 已经设为私有化 无法在另外的类中直接赋值
        person.setName("jack");
        person.setAge(50);
        person.setSalary(30000);
        System.out.println(person.info());

        //如果我们自己用构造器指定属性
        Person smith = new Person("smith", 2000, 50000);
        System.out.println(smith.info());//这里会发现不改构造器的话防护机制失效了
    }
}
/**
 * 实现一个小程序  不能随便查看别人的年龄工资等隐私 并对设置的年龄进行合理的验证
 * 合理就设置 否则给默认值  必须在1-120   年龄工资不能直接查看 名字字段在2-6字符
 */
class Person{
    public String name;
    private int age; //年龄私有化这样在另外一个类无法修改
    private double salary;
    //无参构造器
    public Person() {
    }
    //有参构造器
    public Person(String name, int age, double salary) {
//        this.name = name;
//        this.age = age;
//        this.salary = salary; 为了防止保护机制失效 构造器不能这样写
        //我们可以将set方法写在构造器中就是直接把防护机制写在构造器里
        setName(name);
        setSalary(salary);
        setAge(age);
    }
    //手写接口（set，get）太慢了 使用快捷键 Alt + Insert
  //生成完根据要求完善我们的代码
    public String getName() {
        return name;
    }
    public void setName(String name) {
        //对数据的校验检测
        if(name.length() >= 2 && name.length() <= 6)
        {
            this.name = name;
        }
        else
        {
            System.out.println("名字长度不对 需要为2-6字符");
            this.name = "NULL";
        }
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        if(age >= 1 && age <= 120)
        {
            this.age = age;
        }
        else
        {
            System.out.println("年龄需要在1-120");
            this.age = 18; //给一个默认的年龄
        }
    }
    public double getSalary() {
        //这里可以增加对当前对象的权限判断
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    //写一个方法 返回属性信息
    public String info(){
        return "信息为 name = " + name + " age = " + age + " 薪水 = " + salary;
    }
}