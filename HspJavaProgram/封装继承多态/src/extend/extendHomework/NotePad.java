package extend.extendHomework;

public class NotePad extends Computer{
    private String color;

    public NotePad(String cpu, String memory, String disk, String color) {
        super(cpu, memory, disk);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    public void printInfo(){
        System.out.println("Notepad的信息为：");
        //调用父类的getDetail方法得到相关属性
        System.out.println(getDetail() + " color=" + color);
    }
}
