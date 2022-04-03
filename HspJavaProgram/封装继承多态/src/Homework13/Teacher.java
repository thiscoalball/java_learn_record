package Homework13;

public class Teacher extends Person{
    private int work_age;

    public Teacher(String name, String sex, int age, int work_age) {
        super(name, sex, age);
        this.work_age = work_age;
    }

    public int getWork_age() {
        return work_age;
    }

    public void setWork_age(int work_age) {
        this.work_age = work_age;
    }
    public void teach()
    {
        System.out.println("我承诺我会认真教书");
    }

    @Override
    public String play() {
        return super.play() + "象棋";
    }

    public void printBase()
    {
        System.out.println("老师的信息如下");
        super.printBase();
        this.teach();
        System.out.println(this.play());
    }

}
