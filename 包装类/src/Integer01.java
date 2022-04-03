public class Integer01 {
    public static void main(String[] args) {
        //包装类->String
        Integer i = 100;// 自动装箱
        //方式一：
        String str1 = i+""; //i本身是没有变换的 只是组合成了字符串
        //方式二：
        String str2 = i.toString();
        //方式三：
        String str3 = String.valueOf(i);

        //String->包装类
        String str4 = "12344";
        Integer i2 = Integer.parseInt(str4);//自动装箱
        Integer i3 = new Integer(str4);//构造器
        System.out.println("ok");
    }
}
