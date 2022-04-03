package Homework;

public class Homework02 {
    public static void main(String[] args) {
        A a = new A();
        Thread thread = new Thread(a);
        Thread thread1 = new Thread(a);//让这两个线程去取钱

        thread.start();
        thread1.start();
    }
}



class A implements Runnable{
    private int money = 10000;

    @Override
    public void run() {
        while(true){

            synchronized (this) {
                if (money < 1000) {
                    System.out.println("余额不足");
                    break;
                }
                money -= 1000;
                System.out.println(Thread.currentThread().getName()
                        + "取出1000 余额还有：" + money);
            }


            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}