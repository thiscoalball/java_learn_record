package Set;

import java.util.*;

public class MapFor {
    public static void main(String[] args) {
        Map map = new HashMap();
        map.put("1","周杰伦");
        map.put("2","张宇");
        map.put("3","谢安琪");
        map.put("4","林俊杰");
        map.put("5","陈奕迅");
        map.put("6","五月天");

        //第一组：先取出所有的Key 通过Key  取出对应的value
        Set set = map.keySet();
        // 1：
        System.out.println("=====一:增强for循环=====");
        for(Object key:set){
            System.out.print(key + ":" + map.get(key)+"     ");
        }
        // 2:
        System.out.println("\n=====二:迭代器=====");
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Object next =  iterator.next();
            System.out.print(next + ":" + map.get(next)+"     ");
        }

        //第二组：把所有的value取出
        //1:增强for
        Collection values = map.values();
        System.out.println("\n=====三:增强for取value=====");
        for(Object value:values){
            System.out.print(value+"    ");
        }
        //2:迭代器
        System.out.println("\n=====四:迭代器取value=====");
        Iterator iterator1 = values.iterator();
        while (iterator1.hasNext()) {
            Object next =  iterator1.next();
            System.out.print(next + "    ");
        }

        //第三组  通过EntrySet获取key-value
        //1:增强for
        Set entryset = map.entrySet();
        System.out.println("\n=====五:EntrySet增强for访问=====");
        for(Object entry:entryset){
            //将entry转化从Map.Entry
            Map.Entry m = (Map.Entry)entry;
            System.out.print(m.getKey()+":"+m.getValue()+"     ");
        }
        Iterator iterator2 = entryset.iterator();
        System.out.println("\n=====六:EntrySet迭代器访问=====");
        while (iterator2.hasNext()) {
            Object entry = iterator2.next();
            Map.Entry entry1 = (Map.Entry) entry;
            System.out.print(entry1.getKey()+":"+entry1.getValue()+"     ");
        }
    }
}
