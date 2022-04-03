package Interface;

public class Computer {
    public void work(UsbInterface usbInterface){
        //通过接口调用方法
        usbInterface.start();
        usbInterface.stop();
    }
}
