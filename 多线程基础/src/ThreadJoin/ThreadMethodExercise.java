package ThreadJoin;

public class ThreadMethodExercise {
    public static void main(String[] args) throws InterruptedException {
        //主线程每隔一秒输出hi 一共10次  当输出 hi5 时 启动一个子线程（要求用Runnable）
        //子线程每一秒输出hello 输出10次后退出 主线程继续输出hi 直到结束

        Thread thread = new Thread (new T3());
        thread.setPriority(Thread.MIN_PRIORITY);
        for (int i = 1; i <= 10 ; i++) {
            Thread.sleep(1000);
            System.out.println("hi" + i);
            if(i==5){
                thread.start();//开启子线程
                thread.join();//插队进来
            }

        }
    }
}

class T3 implements Runnable{
    @Override
    public void run() {
        for (int i = 1; i <=10 ; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("hello" + i);
        }
    }
}