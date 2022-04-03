package extend;

public class Extends01 {
    //当两个类的属性和方法有很多地方是相同的时候
    //通过继承提高代码复用性
    public static void main(String[] args) {
        Pupil pupil = new Pupil();
        pupil.name = "jack";
        pupil.age = 10;
        pupil.testing();
        pupil.setScore(60);
        pupil.showInfo();

        Graduate graduate = new Graduate();
        graduate.name = "Marry";
        graduate.age = 12;
        graduate.testing();
        graduate.setScore(80);
        graduate.showInfo();
    }
}
