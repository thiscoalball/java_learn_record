import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
//2号位置插入元素”hsp“
//获得第五个元素
//删除第六个元素
//修改第七个元素

public class ListExercise {
    public static void main(String[] args) {
        List list = new ArrayList();
        for (int i = 0; i < 10; i++) {
            list.add("hello" + i);
        }

        list.add(1,"hsp");
        System.out.println(list.get(4));
        list.remove(5);
        list.set(6,"setsetset");

        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            Object next =  iterator.next();
            System.out.print(next +" ");
        }
    }
}
