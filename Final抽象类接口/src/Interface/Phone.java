package Interface;

//phone类 要实现接口 UsbInterface
//即 Phone需要实现 UsbInterface接口里 规定/声明的方法
public class Phone implements UsbInterface{
    @Override
    public void start() {
        System.out.println("手机插入了计算机usb接口");
    }

    @Override
    public void stop() {
        System.out.println("手机拔出了计算机usb接口");
    }
}
