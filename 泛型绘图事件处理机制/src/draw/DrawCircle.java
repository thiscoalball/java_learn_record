package draw;

import javax.swing.*;
import java.awt.*;

public class DrawCircle extends JFrame{//框架窗口

    private MyPanel mp = null;
    public static void main(String[] args) {
        new DrawCircle();
    }


    public DrawCircle(){//构造器完成
        //初始化面板
        MyPanel mp = new MyPanel();
        //把面板放入窗口里
        this.add(mp);
        //设置窗口大小
        this.setSize(400,300);
        //点击窗口的X的时候程序就退出
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置为可以显示
        this.setVisible(true);

    }
}

//1 先定义一个面板 继承JPanel类
//Graphics g 理解成一支画笔
//Graphics 提供了很多的绘图方法
class MyPanel extends JPanel {
    @Override
    public void paint(Graphics g) {//2：重写父类的绘图方法
        super.paint(g);
        System.out.println("画圆的方法被调用了");//第一次显示窗口的时候会调用  窗口的大小发生变化时会调用
        g.drawOval(10,10,100,100);
    }

}