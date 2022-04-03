package ThreadJoin;


public class ThreadJoin {
    public static void main(String[] args) throws InterruptedException {
        T2 t2 = new T2();
        t2.start();
        for (int i = 1; i <= 20; i++) {
            Thread.sleep(1000);
            System.out.println("主线程弟弟吃了 "+ i + " 个包子");
            if(i==5){
                System.out.println("主线程弟弟让子线程哥哥先吃完");
                t2.join(); //让t2线程插队进来
                System.out.println("哥哥吃完了，弟弟继续吃");
            }
        }
    }
}

class T2 extends Thread{
    @Override
    public void run() {
        for (int i = 1; i <= 20; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("异常打断了");
            }
            System.out.println("子线程哥哥吃了 " + i + " 个包子");
        }
    }
}

