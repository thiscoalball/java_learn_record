package ThreadInterrupt;

public class ThreadInterrupt {
    public static void main(String[] args) throws InterruptedException {
        T t = new T();
        t.setName("czy：");
        t.setPriority(Thread.MIN_PRIORITY);//设置最小优先级
        t.start();

        //主线程打印5秒 然后中断子线程
        for (int i = 0; i < 5; i++) {
            Thread.sleep(1000);
            System.out.println((i+1)+"s");
        }
        t.interrupt();
    }
}

class T extends Thread{
    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < 20; i++) {
                System.out.println(Thread.currentThread().getName()+ "吃包子:"  + (i+1));
            }
            try {
                System.out.println("正常休息中");
                Thread.sleep(10000);//吃完20个包子后休息10秒
            } catch (InterruptedException e) {
                System.out.println("异常打断了");
            }
        }
    }
}