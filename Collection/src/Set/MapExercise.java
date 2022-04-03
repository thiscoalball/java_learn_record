package Set;

import java.util.*;

public class MapExercise {
    public static void main(String[] args) {
        HashMap hashMap = new HashMap();
        hashMap.put("1",new Emp("jason",20000,1));
        hashMap.put("2",new Emp("Marry",10000,2));
        hashMap.put("3",new Emp("Tom",5000,3));
        hashMap.put("4",new Emp("Tom",50000,4));

        //获取工资大于180001的员工
        System.out.println("======keySet方法加增强for======");
        Set keySet = hashMap.keySet();
        for(Object key:keySet){
            Emp emp = (Emp) hashMap.get(key);
            if(emp.getSalary() >= 18000){
                System.out.println(emp);
            }
        }
        //EntrySet 加  迭代器
        System.out.println("======entrySet加迭代器的方法======");
        Set entrySet = hashMap.entrySet();
        Iterator iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();//这个next就是 MapEntry
            Emp emp = (Emp)entry.getValue();   //value就是我们的Emp类
            if(emp.getSalary()>18000){
                System.out.println(emp);
            }
        }

    }
}

class Emp{
    private String name;
    private double salary;
    private int id;

    public Emp(String name, double salary, int id) {
        this.name = name;
        this.salary = salary;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", id=" + id +
                '}';
    }
}