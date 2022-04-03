package Homework01;

import java.util.*;

@SuppressWarnings({"all"})
public class Homework02 {
    public static void main(String[] args) {
        Map m = new HashMap();
        m.put("jack",650);
        m.put("tom",1200);
        m.put("smith",2900);
        //将jack的工资改为2600
        System.out.println("原情况"+ m);

        m.put("jack",2600);
        //对所有员工加1000
        System.out.println("jack加工资"+ m);
        Set set = m.keySet();//用keySet方法 遍历元素
        for(Object key:set){
            m.put(key,(Integer)m.get(key)+1000);//get方法找到对应的value 转化Interger并加1000
        }
        System.out.println("更新后的情况"+m);

        Set set1 = m.entrySet();
        Iterator iterator = set1.iterator();
        while (iterator.hasNext()) {
            Map.Entry next = (Map.Entry) iterator.next();
            System.out.println(next.getKey()+ ":" +next.getValue());
        }

        //遍历工资
        Collection values = m.values();
        for(Object value : values){
            System.out.println("工资为" + value);
        }
    }
}
