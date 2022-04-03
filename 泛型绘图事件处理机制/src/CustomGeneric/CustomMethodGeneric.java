package CustomGeneric;

    public class CustomMethodGeneric {
        public static void main(String[] args) {
            Car car = new Car();
            car.fly("hhh",12.1);//传值不用指定类型 编译器会帮你确定类型
            car.fly(100,false);
        }
    }

    class Car<U>{
        public<T,R> void fly(T t,R r){
            System.out.println(t.getClass());
            System.out.println(r.getClass());
        }
        public void hi(U u){
            //这样是使用了类声明的泛型 不是泛型方法
        }
    }