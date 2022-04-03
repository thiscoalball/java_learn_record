package Abstract;

public class abstractExercise {

    public static void main(String[] args) {
        Manager jack = new Manager(12, "jack", 5000, 1000);
        jack.work();
        CommonEmployee marry = new CommonEmployee(12, "marry", 500);
        marry.work();
    }
}

abstract class Employee{
    private int age;
    private String name;
    private double salary;

    public Employee(int age, String name, double salary) {
        this.age = age;
        this.name = name;
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    abstract void work();
}
class Manager extends Employee{

    private double bonus;

    public Manager(int age, String name, double salary, double bonus) {
        super(age, name, salary);
        this.bonus = bonus;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public void work(){
        System.out.println("经理：" + getName() + "工作中");
    }
}
class CommonEmployee extends Employee{
    public CommonEmployee(int age, String name, double salary) {
        super(age, name, salary);
    }

    @Override
    void work() {
        System.out.println("普通员工:" + getName() + " 工作中");
    }
}