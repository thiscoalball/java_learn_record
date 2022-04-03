public class ArrayTest {
    public static void main(String[] args) {

        MyTools mt = new MyTools();
        int[] arr = {10, 20, 30, 40, 2, 32, 46};
        mt.bubble(arr);
        //输出排序后的数组
        System.out.println("排序后的数组为：");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}

//创建一个类MyTools 编写一个方法 可以完成对 int数组的泡泡排序
class MyTools {


    public void bubble(int[] arr) {
        //冒泡排序       //外层记得 -1
        int temp = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
