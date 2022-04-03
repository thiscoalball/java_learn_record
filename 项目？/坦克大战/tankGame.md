



# 第一版

## 游戏入口

```java
import javax.swing.*;
import java.awt.*;

public class TankGame01 extends JFrame {
    //定义一个MyPanel
    MyPanel mp = null;
    public static void main(String[] args) {
        new TankGame01();
    }

    public TankGame01(){
        mp = new MyPanel();
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp);//把绘图添加到窗口中
        this.setSize(1200,900);
        this.addKeyListener(mp);//在窗口中加入监听事件，不然窗口不会有相应
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
```



## 坦克类

```java
import java.util.Vector;

public class Tank {//坦克类
    private int x; //横坐标
    private int y; //纵坐标
    private int direct;//0向上 1向右 2向下 3向左
    private int speed = 3;
    private boolean isLive = true;

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    Vector<Shot> shots = new Vector<>();//储存坦克子弹

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public int getSpeed() {
        return speed;
    }
    public void moveUp(){
        //y-=3;这样操作速度也麻烦 直接再定义一个变量speed 提
        // 供一个set接口 直接在外面就可以很方便的改速度
        if(getY()>0) {//这一些if判断是防止坦克超出边界
          y -= speed;
          }
    }
    public void moveDown(){
       // y+=3;

        if((getY()+60)<750) {
            y+=speed;
        }
    }
    public void moveRight(){
        if( (getX()+60) < 1000) {
            x+=speed;
        }
    }
    public void moveLeft(){
        //x-=3;
        if(getX()>0){
            x-=speed;
        }
    }
    //封装坦克发射子弹函数
    public void shoting(){
        Shot s = null;
        switch (getDirect()) {//得到坦克的方向 才能确定子弹的方向
            case 0://向上
                s = new Shot(getX() + 19, getY(), 0);//坐标传炮管的坐标
                break;
            case 1://向右
                s = new Shot(getX() + 60, getY() + 19, 1);//坐标传炮管的坐标
                break;
            case 2://向下
                s = new Shot(getX() + 19, getY() + 60, 2);//坐标传炮管的坐标
                break;
            case 3://向左
                s = new Shot(getX(), getY() + 19, 3);//坐标传炮管的坐标
                break;
        }
        shots.add(s);
        new Thread(s).start();
    }
}

```



## 我的坦克

```java
import java.util.Vector;

public class MyTank extends Tank{

    Shot shot = null;
    boolean up = false,down = false,right = false,left = false;

    public MyTank(int x, int y) {
        super(x, y);
    }

    public void MyShot(){
        if(shots.size()==5){
            return;
        }
//        switch (getDirect()) {//得到坦克的方向 才能确定子弹的方向
//            case 0://向上
//                shot = new Shot(getX() + 19, getY(), 0);//坐标传炮管的坐标
//                break;
//            case 1://向右
//                shot = new Shot(getX() + 60, getY() + 19, 1);//坐标传炮管的坐标
//                break;
//            case 2://向下
//                shot = new Shot(getX() + 19, getY() + 60, 2);//坐标传炮管的坐标
//                break;
//            case 3://向左
//                shot = new Shot(getX(), getY() + 19, 3);//坐标传炮管的坐标
//                break;
//        }//创建完启动射击的线程
//        shots.add(shot);
//        new Thread(shot).start();
        shoting();//面向对象我爱你
    }
}

```



## 敌人坦克

```java
import java.util.Vector;

public class EnemyTank extends Tank implements Runnable{



    public EnemyTank(int x, int y) {
        super(x, y);
    }

    @Override
    public void run() {
        while (true){
            //根据坦克的方向继续移动 然后随机改变方向
            if(shots.size() <= 3 && isLive()){//没子弹了 创建一颗子弹放到shots集合
                //这里if里教程把坦克是否存活也放了，我没放
                //我觉得坦克这个进程如果不在的话也不会存在这里发射子弹的问题，先留一个伏笔，有问题再回来改

                //判断坦克方向发射对应子弹
//                Shot s = null;
//                switch (getDirect()) {//得到坦克的方向 才能确定子弹的方向
//                    case 0://向上
//                        s = new Shot(getX() + 19, getY(), 0);//坐标传炮管的坐标
//                        break;
//                    case 1://向右
//                        s = new Shot(getX() + 60, getY() + 19, 1);//坐标传炮管的坐标
//                        break;
//                    case 2://向下
//                        s = new Shot(getX() + 19, getY() + 60, 2);//坐标传炮管的坐标
//                        break;
//                    case 3://向左
//                        s = new Shot(getX(), getY() + 19, 3);//坐标传炮管的坐标
//                        break;
//                }
//                shots.add(s);
//                new Thread(s).start();
                shoting();
            }
            switch (getDirect()) {
                case 0:
                    //让坦克先保持一个方向走几步
                    for (int i = 0; i < 30; i++) {
                        moveUp();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 1:
                    for (int i = 0; i < 30; i++) {
                        moveRight();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < 30; i++) {
                        moveDown();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < 30; i++) {
                        moveLeft();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }

                setDirect((int)(Math.random()*4));
                //坦克被击中后 结束该线程
                if(isLive() == false){
                    break;
                }
        }
    }
}

```



## 爆炸类

```java
public class Bomb {
    int x,y;
    int life = 10;//炸弹的生命周期
    boolean isLive = true;

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //减少生命值
    public void lifeDown(){
        if(life>0){
            life--;
        }else{
            isLive = false;
        }
    }
}

```



## 子弹类

```java
public class Shot implements Runnable{
    int x;//子弹的x坐标
    int y;//子弹的y坐标
    int direct = 0; //子弹的方向
    int speed = 2; //子弹的速度

    public  void setSpeed(int speed) {
        this.speed = speed;//封装接口方便更改速度
    }

    boolean isLive = true; //子弹是否还存活
    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    //构造器
    @Override
    public void run() {//射击行为
        while(true){
            try {
                Thread.sleep(15);//休眠50ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //根据方向改变x，y坐标
            switch (direct){
                case 0:
                    y -= speed;
                    break;
                case 1:
                    x += speed;
                    break;
                case 2:
                    y += speed;
                    break;
                case 3:
                    x -= speed;
                    break;
            }
            System.out.println("子弹x = " +x+" y= " + y);//测试
            //如果不 && isLive的话 子弹在打到敌方坦克后只是不绘制了，但是还在
            //isLive 在打到敌人坦克之后就会变为 false 所以可以在这 &&
            if(      !(x>=0 && x<=1000 && y>=0 && y<= 750 && isLive) ){//出界了，就跳出
                isLive = false;
                break;
            }

        }
    }
}

```



## 面板类

```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

//为了让面板不停的重绘子弹 面板也需要当作一个线程使用
public class MyPanel extends JPanel implements KeyListener,Runnable {//游戏的绘图区域
    //定义我的Tank
    MyTank mytank = null;

    //定义敌人坦克
    EnemyTank enemytank = null;//敌人坦克可能有多辆 放入vector
    //因为vector是线程安全的 后面我们游戏肯定需要多线程运行 因为每个坦克有自己的生命周期
    Vector<EnemyTank> enemytanks = new Vector<>();

    //定义爆炸 当子弹击中坦克时 加入一个对象到bombs中
    Vector<Bomb> bombs = new Vector<>();

    //定义三张图片组合成爆炸
    Image image = null;
    Image image1 = null;
    Image image2 = null;

    int enemyTankSize = 3;

    public MyPanel() {
        mytank = new MyTank(700,100);//初始化自己的坦克
        mytank.setSpeed(3);//利用提供的接口可以很方便的改变坦克的速度


        for (int i = 0; i < enemyTankSize; i++) {//初始化敌人坦克
            //创建一个敌人坦克
            EnemyTank enemytank = new EnemyTank( 100*(i+1),0);
            //设置方向
            enemytank.setDirect(2);
            //启动坦克线程
            new Thread(enemytank).start();
            //给该坦克创建子弹
            Shot shot = new Shot(enemytank.getX() + 19, enemytank.getY() + 60, enemytank.getDirect());
            //加入到Vector
            enemytank.shots.add(shot);
            //启动shot线程
            new Thread(shot).start();

            //加入
            enemytanks.add(enemytank);
        }
        //初始化图片对象
        image = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
        image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
        image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));

    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0,1000,750);//填充矩形画成游戏区域

        //绘制自己的坦克
        if(mytank!=null && mytank.isLive()) {
            drawTank(mytank.getX(), mytank.getY(), g, mytank.getDirect(), 0);
        }
        //绘制敌人的坦克
        for (int i = 0; i < enemytanks.size(); i++) {
            EnemyTank enemytank = enemytanks.get(i);//取出每一辆坦克的信息
            //判断是否存活
            if(enemytank.isLive()) {
                //画出坦克
                drawTank(enemytank.getX(), enemytank.getY(), g, enemytank.getDirect(), 1);
                //画出坦克所有子弹
                for (int j = 0; j < enemytank.shots.size(); j++) {
                    //取出子弹
                    Shot shot = enemytank.shots.get(j);
                    //绘制
                    if (shot.isLive) {
                        g.setColor(Color.RED);
                        g.draw3DRect(shot.x, shot.y, 2, 2, false);
                    } else {
                        //从Vector移除
                        enemytank.shots.remove(j);
                    }
                }
            }
            else{
                enemytanks.remove(i);//移除这个坦克的位置 不然坦克样子不在了 我们的子弹还是碰到
            }

        }

        //绘制自己的子弹
        for (int i = 0;i<mytank.shots.size();i++) {
                Shot shot = mytank.shots.get(i);
                if(shot != null && shot.isLive == true){
                //场面上不存在子弹的时候
                g.setColor(Color.GREEN);
                g.draw3DRect(shot.x ,shot.y,2,2,false);
            }else{
                    mytank.shots.remove(shot);
                }
        }

        //画出炸弹爆炸 bombs集合中有的时候就画出
        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            if(bomb.life>6){
                g.drawImage(image,bomb.x,bomb.y,60,60,this);
            }else if(bomb.life>3){
                g.drawImage(image1,bomb.x,bomb.y,60,60,this);
            }else{
                g.drawImage(image2,bomb.x,bomb.y,60,60,this);
            }
            bomb.lifeDown();//看不懂啊，这里不是只会炸一次吗 为什么爆炸效果不停
            //如果bomblife 为0 就从bombs集合中删除
            if(bomb.life == 0){
                bombs.remove(i);
            }
        }
    }


    public void drawTank(int x,int y,Graphics g,int direct,int type){
        //x,y为坦克参考点坐标 //g画笔 //direct方向 //type为坦克类型
        switch (type){
            case 0://自己的坦克
                g.setColor(Color.GREEN);//绿色
                break;
            case 1://敌人的坦克
                g.setColor(Color.RED);//红色
                break;
        }
        //根据坦克方向绘制坦克(0向上 1向右 2向下 3向左
        switch (direct){
            case 0://向上
                g.fill3DRect(x, y, 10,60,false);//画出坦克左边轮子
                g.fill3DRect(x+10,y+10,20,40,false);//坦克中间矩形
                g.fill3DRect(x+30,y,10,60,false);//坦克右边轮子
                g.fillOval(x+10,y+20,20,20);//坦克中间圆
                g.drawLine(x+20,y+30,x+20,y); //坦克炮筒
                break;
            case 1: //表示向右
                g.fill3DRect(x, y, 60, 10, false);//画出坦克上边轮子
                g.fill3DRect(x, y + 30, 60, 10, false);//画出坦克下边轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false);//画出坦克盖子
                g.fillOval(x + 20, y + 10, 20, 20);//画出圆形盖子
                g.drawLine(x + 30, y + 20, x + 60, y + 20);//画出炮筒
                break;
            case 2: //表示向下
                g.fill3DRect(x, y, 10, 60, false);//画出坦克左边轮子
                g.fill3DRect(x + 30, y, 10, 60, false);//画出坦克右边轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//画出坦克盖子
                g.fillOval(x + 10, y + 20, 20, 20);//画出圆形盖子
                g.drawLine(x + 20, y + 30, x + 20, y + 60);//画出炮筒
                break;
            case 3: //表示向左
                g.fill3DRect(x, y, 60, 10, false);//画出坦克上边轮子
                g.fill3DRect(x, y + 30, 60, 10, false);//画出坦克下边轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false);//画出坦克盖子
                g.fillOval(x + 20, y + 10, 20, 20);//画出圆形盖子
                g.drawLine(x + 30, y + 20, x, y + 20);//画出炮筒
                break;

            default:
                System.out.println("其他情况以后再写");
        }
    }

    //判断单个子弹是否击中坦克   放到run方法里调用
    public void hitTank(Shot s,Tank tank){
        //判断s击中坦克
        switch (tank.getDirect()){
            case 0:
            case 2://敌人坦克在上下的方向的时候
                if(s.x>tank.getX() && s.x<tank.getX() + 40 && s.y>tank.getY()
                && s.y<tank.getY()+60){
                    s.isLive = false;
                    tank.setLive(false);
                    //击中坦克时 炸弹加入
                    Bomb bomb = new Bomb(tank.getX(), tank.getY());
                    bombs.add(bomb);
                }
                break;
            case 1:
            case 3:
                if(s.x>tank.getX() && s.x<tank.getX() + 60 && s.y>tank.getY()
                        && s.y<tank.getY()+40){
                    s.isLive = false;
                    tank.setLive(false);
                    Bomb bomb = new Bomb(tank.getX(), tank.getY());
                    bombs.add(bomb);
                }
                break;

        }
    }
    //由于是多个子弹 我们现在需要挨个取出来判断
    public void hitEnemyTank(){
        for (int j = 0; j < mytank.shots.size(); j++) {
            Shot shot = mytank.shots.get(j);
            if(shot != null && shot.isLive){
                //遍历敌人所有的坦克
                for (int i = 0; i < enemytanks.size(); i++) {
                    EnemyTank enemyTank = enemytanks.get(i);
                    hitTank(shot,enemyTank);//判断单个子弹是否碰到坦克
                }
            }
        }
    }

    public void hitHero(){
        for(int i = 0;i<enemytanks.size();i++){
            EnemyTank enemytank = enemytanks.get(i);//取出每个敌方坦克
            //遍历每一个坦克的所有的子弹
            for(int j = 0; j< enemytank.shots.size();j++){
                Shot shot = enemytank.shots.get(j);//取出每辆坦克的子弹
                if(mytank.isLive() && shot.isLive){
                    hitTank(shot,mytank);
                }
            }
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
    //处理键wdas键按下的情况
    @Override
    public void keyPressed(KeyEvent e) {
        //坦克方向发生变化  先改变方向 然后走
        if(e.getKeyCode() ==KeyEvent.VK_W ){
//            mytank.setDirect(0);
//            //mytank.setY(mytank.getY()-1);
//            // 往下走//这样其实可读性不咋地，而且访问两次接口 降低运行速度
//            // 那就往坦克类里封装方法 直接操作内部 并且方便控制坦克速度 不信自己看方法体
//            // 面向对象实在是太香了...这样改太方便了
//            mytank.moveUp();
            mytank.up = true;
            mytank.left = false;
            mytank.right = false;
            mytank.down = false;

        }else if(e.getKeyCode() ==KeyEvent.VK_S){
//            mytank.setDirect(2);
//            mytank.moveDown();
            mytank.up = false;
            mytank.left = false;
            mytank.right = false;
            mytank.down = true;

        }else if(e.getKeyCode() ==KeyEvent.VK_D){
//            mytank.setDirect(1);
//            mytank.moveRight();
            //mytank.setX(mytank.getX()+1);
            mytank.up = false;
            mytank.left = false;
            mytank.right = true;
            mytank.down = false;
        }else if(e.getKeyCode() ==KeyEvent.VK_A){
//            mytank.moveLeft();
//            mytank.setDirect(3);
            //mytank.setX(mytank.getX()-1);
            mytank.up = false;
            mytank.left = true;
            mytank.right = false;
            mytank.down = false;
        }
        //发射子弹
        if(e.getKeyCode() == KeyEvent.VK_J){
            //发射一颗子弹
//            if(mytank.shot == null || mytank.shot.isLive == false) {
//                mytank.MyShot();
//            }
            //发生多颗子弹
            if(mytank.isLive()) {
                mytank.MyShot();
            }
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(mytank.up){
                mytank.setDirect(0);
                //mytank.setY(mytank.getY()-1);
                // 往下走//这样其实可读性不咋地，而且访问两次接口 降低运行速度
                // 那就往坦克类里封装方法 直接操作内部 并且方便控制坦克速度 不信自己看方法体
                // 面向对象实在是太香了...这样改太方便了
                mytank.moveUp();
            }else if(mytank.down){
                mytank.setDirect(2);
                mytank.moveDown();
            }else if(mytank.left){
                mytank.moveLeft();
                mytank.setDirect(3);
            }else if(mytank.right){
                mytank.setDirect(1);
                mytank.moveRight();
            }
            //判断是否击中了坦克
//            if(mytank.shot != null && mytank.shot.isLive){//子弹或者的时候
//                //遍历敌人所有的坦克
//                for (int i = 0; i < enemytanks.size(); i++) {
//                    EnemyTank enemyTank = enemytanks.get(i);
//                    hitTank(mytank.shot,enemyTank);
//                }
//            }
            hitHero();
            hitEnemyTank();

            this.repaint();
        }
    }
}
```

