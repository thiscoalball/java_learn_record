package 类方法;

public class StaticMethod {
    public static void main(String[] args) {
        //细节：静态方法只能访问静态成员
        Student jack = new Student("jack");
        jack.payFee(200);
        Student marry = new Student("marry");
        marry.payFee(200);
        Student.showFee();
    }
}


class Student{
    private String name;
    static double fee = 0;

    public Student(String name) {
        this.name = name;
    }
    public static void payFee(double fee){
        Student.fee += fee;
    }
    public static void showFee(){
        System.out.println("总学费有："+ Student.fee );
       // Student.name
    }
}