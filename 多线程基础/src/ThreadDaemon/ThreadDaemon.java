package ThreadDaemon;

public class ThreadDaemon {
    public static void main(String[] args) throws InterruptedException {
        T t = new T();
        //我们现在希望主线程退出后  子线程也一起退出
        t.setDaemon(true);//将其置为守护线程   顺序需要注意 先设定 再开始start
        t.start();


        for (int i = 1; i <= 5; i++) {
            System.out.println("主线程运行");
            Thread.sleep(1000);
        }
    }
}


class T extends Thread{
    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(200);//休眠59ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("(守护）子线程运行");
        }
    }
}