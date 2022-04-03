package Collections;

        import java.util.ArrayList;
        import java.util.Collections;
        import java.util.Comparator;
        import java.util.List;

public class Collections01 {
    public static void main(String[] args) {
        List list = new ArrayList();

        list.add("tom");
        list.add("tom");
        list.add("john");
        list.add("smith");
        list.add("steven");
        System.out.println("原数列：" + list);
        Collections.reverse(list);
        System.out.println("反转过后："+list);
        Collections.shuffle(list);
        System.out.println("随即排序后："+list);
        Collections.sort(list);
        System.out.println("sort自然排序后："+list);
        Collections.sort(list,new Comparator(){
            @Override
            public int compare(Object o1, Object o2) {
                return ((String)o1).length() - ((String)o2).length();
            }
        });
        System.out.println("指定排序后："+list);
        Collections.swap(list,0,2);
        System.out.println("0和2索引交换："+list);
        System.out.println("自然顺序的最大元素"+Collections.max(list));
        Object max= Collections.max(list, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((String)o1).length() - ((String)o2).length();
            }
        });
        System.out.println("指定顺序后的最大元素："+max);

        System.out.println("tom的出现次数:"+Collections.frequency(list,"tom"));
        //为了完成完整拷贝 需要先给dest赋值  大小和list.size()一样
        ArrayList dest = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            dest.add("");
        }
        Collections.copy(dest,list);
        System.out.println("复制后的表："+dest);

        Collections.replaceAll(list,"tom","汤姆");
        System.out.println("tom被替换了："+list);
    }
}
