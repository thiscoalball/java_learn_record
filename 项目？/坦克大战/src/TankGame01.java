import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Scanner;

@SuppressWarnings({"all"})

public class TankGame01 extends JFrame {
    //定义一个MyPanel
    MyPanel mp = null;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        new TankGame01();
    }

    public TankGame01() throws IOException {
        System.out.println("请输入你的选择1：新游戏  2：继续上局");
        String key = scanner.next();
        mp = new MyPanel(key);
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp);//把绘图添加到窗口中
        this.setSize(1300, 900);
        this.addKeyListener(mp);//在窗口中加入监听事件，不然窗口不会有响应
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    Recorder.keepRecord();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                System.out.println("监听到关闭窗口");
                System.exit(0);
            }
        });
    }
}