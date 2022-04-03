public class Generic03 {
    public static void main(String[] args) {
        //可以理解 为Person类里的E全部用String替代
        Person<String> person1 = new Person<String>("hahaha");
        //这就相当于全部由Integer替代
        Person<Integer> person2 = new Person<Integer>(100);
        System.out.println(person1.s.getClass());//观察绑定类型
        System.out.println(person2.s.getClass());

    }
}


//泛型的作用是：可以在类声明的时候通过一个标识表示类中某个属性的类型
// 或者是某个方法的返回值的类型   或者是参数类型
class Person<E>{
    E s;

    public Person(E s) {
        this.s = s;
    }
    public E f(){
        return s;
    }
}