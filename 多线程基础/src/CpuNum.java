public class CpuNum {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        int CpuNums = runtime.availableProcessors();
        //获取cpu核心数量
        System.out.println(CpuNums);
    }
}
