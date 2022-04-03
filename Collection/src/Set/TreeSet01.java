package Set;

import java.util.Comparator;
import java.util.TreeSet;

public class TreeSet01 {
    public static void main(String[] args) {
        //希望按照字符串的大小来排序
        TreeSet treeSet = new TreeSet(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
               // return   ( (String)o1 ).compareTo(  (String)o2 );这个返回字母表排序
                return  ( (String)o1 ).length() - (  (String)o2 ).length();//这个返回字符串长度比较
            }
        });

        treeSet.add("jack");
        treeSet.add("tom");
        treeSet.add("md");
        treeSet.add("a");
        System.out.println(treeSet);
    }
}
