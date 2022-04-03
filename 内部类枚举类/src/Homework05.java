public class Homework05 {
    public static void main(String[] args) {
        A a = new A();
        a.m();
        new A().m();
    }
}

class A {
    private final String name = "hello";
    public void m(){
        class B{
            private final String name = "ccc";

            public void show(){
                System.out.println(name + " " + A.this.name);
            }
        }
        B b = new B();
        b.show();
    }
}