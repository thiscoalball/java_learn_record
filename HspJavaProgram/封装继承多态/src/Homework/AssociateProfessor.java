package Homework;

public class AssociateProfessor extends Teacher {
    public AssociateProfessor(String name, int age, String post, double salary, double grade) {
        super(name, age, post, salary, grade);
    }

    @Override
    public void introduce() {
        System.out.println("这里打印的是副教授的信息");
        super.introduce();
    }
}
