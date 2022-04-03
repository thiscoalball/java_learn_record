package polymorphic.polyArr;

public class PloyArr {
    public static void main(String[] args) {
        //创建2个student对象 和两个 teacher对象 统一放在数组中 调用每个对象的say方法
        Person[] persons = new Person[5]; //创建对象数组
        persons[0] = new Person("jack",20);
        persons[1] = new Student("jack",18,89.5);
        persons[2] = new Student("smith",19,60.5);
        persons[3] = new Teacher("Marry",19,5000);
        persons[4] = new Teacher("Tom",19,20000);
        for (int i = 0; i < persons.length; i++) {

            System.out.println(persons[i].say());
            if(persons[i] instanceof Student){//利用向下转型强转
                ((Student)persons[i]).study();
            }
            else if(persons[i] instanceof Teacher){
                ((Teacher)persons[i]).teach();
            }
            else{
            System.out.println("你的类型有误");
            }
            System.out.println();
        }

    }
}
