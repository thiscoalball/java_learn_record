import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CollectionMethod {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("jack");
        arrayList.add(2);
        arrayList.add("ccc");
        arrayList.add(true);//add里面存的已经不是基本数据类型了，是对象了
        System.out.println("添加后: " + arrayList);

        arrayList.remove(3);
        System.out.println("删除最后一个索引位置: "+ arrayList);

        arrayList.remove("ccc");
        System.out.println("移除了ccc: "+arrayList);

        System.out.println("查找有无jack： "+arrayList.contains("jack"));

        System.out.println("打印现在长度: " + arrayList.size());

        arrayList.clear();
        System.out.println("进行清空操作后判断是否为空 ： " + arrayList.isEmpty());

        ArrayList arrayList1 = new ArrayList();
        arrayList1.add("红楼梦");
        arrayList1.add("三国演绎");
        arrayList.addAll(arrayList1);
        System.out.println("添加多个元素： " + arrayList);
        System.out.println("查找多个元素是否存在: " + arrayList.containsAll(arrayList1));

        arrayList.removeAll(arrayList1);
        System.out.println("一次删除多个元素是否为空：" + arrayList.isEmpty());
    }

}
