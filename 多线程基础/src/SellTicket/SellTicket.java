package SellTicket;

public class SellTicket {
    public static void main(String[] args) {
        //三个窗口售票一百张
        //继承方式
//        SellTicket01 sellTicket01 = new SellTicket01();
//        SellTicket01 sellTicket02 = new SellTicket01();
//        SellTicket01 sellTicket03 = new SellTicket01();
//        sellTicket01.start();
//        sellTicket02.start();
//        sellTicket03.start();
        //接口方式
        SellTicket02 sellTicket02 = new SellTicket02();
        new Thread(sellTicket02).start();
        new Thread(sellTicket02).start();
        new Thread(sellTicket02).start();


    }
}


//使用Thread方式 ------->出现互斥同步问题 比如剩下两张票时  三个线程同时访问到了
//导致超卖 而且前面那个内存块就算是大于3 三个同时访问到时也会导致计票出错
class SellTicket01 extends Thread{
    private  static int num = 10;//static让多个线程共享票数
    @Override

    public void run() {
        while(true){
            if(num<=0){
                System.out.println("售票结束");
                break;
            }
            try {//模拟50ms休眠
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("窗口：" + Thread.currentThread().getName() + "卖出一张票"
            +"    " + "剩余票数" + (--num));
        }
    }
}



//使用接口方式 还是出现了互斥 因为还是三个线程同时访问到票
//class SellTicket02 implements Runnable{
//    private int num = 10;
//    @Override
//    public void run() {
//        while(true){
//            if(num<=0){
//                System.out.println("售票结束");
//                break;
//            }
//            try {//模拟50ms休眠
//                Thread.sleep(50);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("窗口：" + Thread.currentThread().getName() + "卖出一张票"
//                    +"    " + "剩余票数" + (--num));
//        }
//    }
//}
class SellTicket02 implements Runnable{
    private static int num = 100;
    private boolean loop = true;


    public /*synchronized*/ void m() {//加上锁
        synchronized (this) {//同步代码块加锁 还是在this对象
            if (num <= 0) {
                System.out.println("售票结束");
                loop = false;
                return;
            }
            try {//模拟50ms休眠
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("窗口：" + Thread.currentThread().getName() + "卖出一张票"
                    + "    " + "剩余票数" + (--num));
        }
    }
    @Override
    public void run() {//使用同步方法上锁
        while(loop){
            m();

        }
    }
}