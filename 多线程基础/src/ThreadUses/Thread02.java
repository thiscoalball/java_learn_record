package ThreadUses;

public class Thread02 {
    public static void main(String[] args) {
        hi hi = new hi();
        Thread thread = new Thread(hi);//把实现对象放入Thread 后面再调用start
        thread.start();
    }
}
class hi implements Runnable{
    private int times = 0;
    @Override
    public void run() {
        while(true){
            System.out.println("hi");
            ++times;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(times == 5){
                break;
            }
        }
    }
}
