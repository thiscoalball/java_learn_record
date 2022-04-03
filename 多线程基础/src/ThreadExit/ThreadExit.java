package ThreadExit;

public class ThreadExit {
    public static void main(String[] args) throws InterruptedException {
        T t = new T();
        t.start();

        //让主线程休眠10秒再让t1退出
        Thread.sleep(10*1000);
        t.setLoop(false);
    }
}

class T extends Thread{
    private int count = 0;
    private boolean loop = true;

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    @Override
    public void run() {
        while(loop){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("A thread  运行中"+ (++count));
        }
    }
}
