package extend.extendHomework;

public class PC extends Computer {

    private String brand;

    public PC(String cpu, String memory, String disk, String brand) {
        super(cpu, memory, disk);
        this.brand = brand;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public void printInfo(){
        System.out.println("PC的信息为：");
        //调用父类的getDetail方法得到相关属性
        System.out.println(getDetail() + " brand=" + brand);
    }
}
