package Set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class setMethod01 {
    public static void main(String[] args) {
        Set set = new HashSet();
        set.add("john");
        set.add("Cat");
        set.add("Bob");
        set.add("Apple");
        set.add(null);
        System.out.println(set.add(null));
        System.out.println(set);
        //遍历方法   迭代器
        System.out.println("=====迭代器遍历=====");
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Object next =  iterator.next();
            System.out.print(next+"  ");
        }
        //增强for遍历
        System.out.println("\n=====增强for遍历=====");
        for(Object b:set){
            System.out.print(b + "  ");
        }
        //set对象不能用for循环遍历 也就是索引下标无法使用
    }
}
