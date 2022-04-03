package Interface;

public class Interface01 {
    public static void main(String[] args) {
        Camera camera = new Camera();
        Phone phone = new Phone();
        Computer computer = new Computer();
        computer.work(phone);//相当于把手机和计算机挂钩
        computer.work(camera);//把相机通过接口接入computer
    }
}
