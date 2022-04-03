package Homework13;

public class Student extends Person{
    private int stu_id;

    public Student(String name, String sex, int age, int stu_id) {
        super(name, sex, age);
        this.stu_id = stu_id;
    }

    public int getStu_id() {
        return stu_id;
    }

    public void setStu_id(int stu_id) {
        this.stu_id = stu_id;
    }

    public void study()
    {
        System.out.println("我承诺我会好好学习");
    }
    @Override
    public String play() {
        return super.play() + "足球";
    }

    @Override
    public void printBase() {
        System.out.println("学生的信息如下");
        super.printBase();
        this.study();
        System.out.println(this.play());
    }
}
