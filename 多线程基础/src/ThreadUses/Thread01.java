package ThreadUses;

public class Thread01 {
    //开启一个线程每个一秒输出一次 "喵呜！"
    public static void main(String[] args) {
        Cat cat = new Cat();
        cat.start();//启动线程

    }
}
class Cat extends Thread{
    private int times = 0;
    @Override
    public void run() {//重写run方法写上自己要他做的事情
        while (true) {
            System.out.println("喵呜！！！");
            ++times;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(times == 5) {//输出五次后退出
                break;
            }
        }
    }
}
