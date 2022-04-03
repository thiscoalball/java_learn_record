//package CustomGeneric;
//
//public class CustomGeneric {
//}
//
//
////Tiger后面的这种泛型就叫做自定义泛型 一般是单个大写字母
////普通成员可以使用泛型
//class Tiger<T,R,M>{
//    String name;
//    R r;
//    T t;
//    M m;
//    //T[] ts = new T[8];不可以 因为没有确定类型
//    public Tiger(String name, R r, T t, M m) {//构造器使用泛型 同理方法也可以
//        this.name = name;
//        this.r = r;
//        this.t = t;
//        this.m = m;
//    }
//    static R r2;  //不行
//    public static void m1(M m){}//这里也不行 静态方法不能使用
//}