package Homework;

public class Worker extends Employee{
    public Worker(String name, double salary, int day) {
        super(name, salary, day);
    }

    @Override
    public void printSalary() {
        System.out.println("员工的工资为：" + getSalary() * getDay());
    }
}
