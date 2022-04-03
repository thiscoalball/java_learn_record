package Interface;

public class Camera implements UsbInterface{
    @Override
    public void start() {
        System.out.println("相机插入了计算机usb接口");
    }

    @Override
    public void stop() {
        System.out.println("相机拔出了计算机usb接口");
    }
}
