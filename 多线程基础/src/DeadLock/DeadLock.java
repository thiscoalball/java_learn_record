package DeadLock;

public class DeadLock {
    public static void main(String[] args) {
        DeadLockDemo A = new DeadLockDemo(true);
        DeadLockDemo B = new DeadLockDemo(false);
        A.start();
        B.start();//这段代码就会卡住了 因为A要拿o2的锁的时候，这个锁现在是在B身上的
                    //B拿o1的锁的时候也同理
    }
}

class DeadLockDemo extends Thread{
    static Object o1 = new Object();
    static Object o2 = new Object();
    boolean flag;

    public DeadLockDemo(boolean flag){
        this.flag = flag;
    }

    @Override
    public void run() {
        //分析以下下面的业务逻辑会出现什么情况
        //如果flag为true 就会持有o1的锁   然后尝试获得o2的对象锁 拿不到对象锁的话就会阻塞
        if(flag){
            synchronized (o1){//对象互斥锁   下面就是同步代码
                System.out.println(Thread.currentThread().getName() + "进入1");

                synchronized (o2){//获得li对象的监视权
                    System.out.println(Thread.currentThread().getName() + "进入2");

                }
            }
        }else{
            synchronized (o2){
                System.out.println(Thread.currentThread().getName() + "进入3");
                synchronized (o1) {
                    System.out.println(Thread.currentThread().getName() + "进入4");
                }
            }
        }
    }
}
