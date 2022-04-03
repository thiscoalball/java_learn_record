package Homework;

public class Manager extends Employee{
    public Manager(String name, double salary, int day) {
        super(name, salary, day);
    }

    @Override
    public void printSalary() {
        System.out.println("经理的工资为：" + getSalary() * getDay() * 1.2 + 1000);
    }
}
