public class Homework07 {
    public static void main(String[] args) {
//        Car car = new Car(70);
//        Car.Air air = car.new Air();
//        air.flow();
//        Car car1 = new Car(-10);
//        Car.Air air1 = car1.new Air();
//        air1.flow();
//        Car car2 = new Car(20);
//        Car.Air air2 = car2.new Air();
//        air2.flow(); //这样有点麻烦 写一个接口函数调用方便

        Car car = new Car(20);
        car.getAir().flow();
        Car car1 = new Car(100);
        car1.getAir().flow();
        Car car2 = new Car(-10);
        car2.getAir().flow();
    }
}


class Car{
    private double temperature;

    public Car(double temperature) {
        this.temperature = temperature;
    }

    class Air {

        public void flow(){
            if(temperature >= 40){
                System.out.println("吹冷气");
            }
            else if(temperature<=0){
                System.out.println("吹暖气");
            }
            else{
                System.out.println("温度合适空调关闭");
            }
        }
    }

    public Air getAir(){
        return new Air();
    }
}