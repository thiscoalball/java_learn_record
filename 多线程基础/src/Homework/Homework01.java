package Homework;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

public class Homework01 {
    public static void main(String[] args) {
        T t = new T();
        B b = new B(t);
        t.start();
        b.start();
    }
}

class T extends Thread{
    private int num = 0;
    private boolean loop = true;
    @Override
    public void run() {
        while(loop){
            System.out.println(     (int)(Math.random() * 100 + 1));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }
}
class B extends Thread{
    private T t;
    private Scanner scanner =new Scanner(System.in);

    public B(T t){
        this.t = t;
    }

    @Override
    public void run() {
        char key = scanner.next().charAt(0);
        if(key == 'Q'){
            t.setLoop(false);

        }
    }
}