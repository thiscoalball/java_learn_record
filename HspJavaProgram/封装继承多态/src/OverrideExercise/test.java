package OverrideExercise;

public class test {
    public static void main(String[] args) {
        Person jack = new Person("jack", 20);
        Student marry = new Student("marry", 12, 2, 82.6);

        System.out.println(jack.say());
        System.out.println(marry.say());

    }
}
