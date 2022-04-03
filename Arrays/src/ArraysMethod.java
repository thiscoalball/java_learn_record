import java.util.Arrays;
import java.util.Comparator;

public class ArraysMethod {

    //可以直接冒泡排序 也可以用提供的sort 这里不做重点
    public static void main(String[] args) {
        //重点看看 Comparator定制排序
        //调用定制排序时 传入两个参数  1：排序的数组  2：匿名内部类
        Integer[] arr = {2,1,3,1,23,123,12,12321,1231,213,23};

        Arrays.sort(arr, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {

                Integer i1 = (Integer) o1;
                Integer i2 = (Integer) o2;
                return i2 - i1;

            }
        });
        System.out.println(Arrays.toString(arr));
    }
}
