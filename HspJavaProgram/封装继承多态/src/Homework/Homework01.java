package Homework;

public class Homework01 {
    //Person类 初始化Person对象数组 3个对象  按照age从大到小冒泡排序
    public static void main(String[] args) {
        Person[] persons = new Person[3];
        persons[0] = new Person("jack",18,"student");
        persons[1] = new Person("marry",40,"teacher");
        persons[2] = new Person("Tom",3,"null");
        int temp = 0;
        for (int i = 0; i < persons.length - 1; i++) {
            for (int j = 0; j < persons.length - i - 1; j++) {
                if(persons[j].getAge() < persons[j+1].getAge()){
                    temp = persons[j].getAge();
                    persons[j].setAge( persons[j+1].getAge() );
                    persons[j+1].setAge(temp);
                }
            }
        }
        for (int i = 0; i < persons.length; i++) {
            System.out.println(persons[i].getName() + "的年龄为 " + persons[i].getAge());
        }
    }
}
