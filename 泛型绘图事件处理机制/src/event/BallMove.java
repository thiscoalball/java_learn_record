package event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BallMove extends JFrame {
    MyPanel mp = null;
    public static void main(String[] args) {
        new BallMove();
    }

    public BallMove() {
        mp = new MyPanel();
        this.add(mp);
        this.setSize(400,300);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }
}



class MyPanel extends JPanel implements KeyListener {//这个接口是在监听键盘发生的事件
    //为了让小球可以移动 把坐标设置为变量
    int x = 10;
    int y = 10;
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillOval(x,y,20,20);
    }

    @Override
    public void keyTyped(KeyEvent e) {//有字符输出 触发

    }

    @Override
    public void keyPressed(KeyEvent e) {//某个键按下 触发
        //根据用户按下的不同键触发小球的移动
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            //KeyEvent.VK_DOWN 就是向下的箭头 KeyEvent.VK_A 就是 对应键盘的A
            y++;
        }else if(e.getKeyCode() == KeyEvent.VK_UP){
            y--;
        }else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            x--;
        }else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            x++;
        }
        this.repaint();//动了过后重新绘制面板
    }

    @Override
    public void keyReleased(KeyEvent e) {//某个键松开了 触发

    }
}