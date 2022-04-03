import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Vector;
import java.io.File;

@SuppressWarnings({"all"})
//为了让面板不停的重绘子弹 面板也需要当作一个线程使用
public class MyPanel extends JPanel implements KeyListener, Runnable {//游戏的绘图区域
    //定义我的Tank
    MyTank mytank = null;
    //定义敌人坦克
    EnemyTank enemytank = null;//敌人坦克可能有多辆 放入vector
    //定义敌人坦克数组                            //因为vector是线程安全的
    Vector<EnemyTank> enemytanks = new Vector<>();
    //定义爆炸 当子弹击中坦克时 加入一个对象到bombs中
    Vector<Bomb> bombs = new Vector<>();
    //定义三张图片组合成爆炸
    Image image = null;
    Image image1 = null;
    Image image2 = null;
    //定义砖块
    Brick brick = null;
    //定义砖块数组:放入绘制的每一个砖块
    Vector<Brick> bricks = new Vector<>();
    //放入所有的坦克
    Vector<Tank> tanks = new Vector<>();
    //定义node对象vector用于恢复敌人坦克坐标
    Vector<Node> nodes = new Vector<>();

    int MyShotSize = 5;//修改我方坦克子弹数量处
    int MySpeed = 3;   //修改我方坦克速度

    int enemyTankSize = 5;//修改敌方坦克数量
    int enemyTankShotSize = 3;//修改敌方子弹数量
    int enemyTankSpeed = 3; //修改敌方坦克速度

    public MyPanel(String key) throws IOException {
        mytank = new MyTank(700, 50);//初始化自己的坦克
        mytank.setSpeed(MySpeed);//利用提供的接口可以很方便的改变坦克的速度
        mytank.setShotSize(MyShotSize);
        File file = new File(Recorder.getRecoder());


        nodes = Recorder.getNodesAndNum();
        Recorder.setEnemyTanks(tanks);

        switch (key) {

            case "1":
                tanks.add(mytank);
                for (int i = 0; i < enemyTankSize; i++) {
                    //创建一个敌人坦克
                    EnemyTank enemytank = new EnemyTank(100 * (i + 1), 0);
                    //将坦克集合设置给每一辆敌方坦克让他们直到各自位置

                    //设置方向
                    enemytank.setDirect(2);
                    if (i >= 5) {
                        enemytank = new EnemyTank(100 * (i - 4), 600);
                        enemytank.setDirect(0);

                    }
                    enemytank.setSpeed(enemyTankSpeed);
                    enemytank.setShotSize(enemyTankShotSize);
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
                    tanks.add(enemytank);

                    //让敌方的每一辆坦克都知道砖块和我方坦克的位置
                    enemytank.setTanks(tanks);
                    enemytank.setBricks(bricks);

                }
                //让我方坦克知道砖块和敌方坦克的位置
                mytank.setTanks(tanks);
                mytank.setBricks(bricks);
                //初始化图片对象
                image = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
                image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
                image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));
                new AePlayWave("src\\111.wav").start();
                //地图初始化
                map();
                break;
            case "2"://继续上局游戏
                for (int i = 0; i < nodes.size(); i++) {
                    //创建一个敌人坦克
                    Node node = nodes.get(i);
                    if (i == 0) {
                        mytank = new MyTank(node.getX(), node.getY());//初始化自己的坦克
                        mytank.setDirect(node.getDirect());
                        mytank.setSpeed(MySpeed);//利用提供的接口可以很方便的改变坦克的速度
                        mytank.setShotSize(MyShotSize);
                        tanks.add(mytank);
                    } else {
                        EnemyTank enemytank = new EnemyTank(node.getX(), node.getY());
                        //将坦克集合设置给每一辆敌方坦克让他们直到各自位置
                        //设置方向
                        enemytank.setDirect(node.getDirect());
                        if (nodes.size() >= 5) {
                            enemytank = new EnemyTank(node.getX(), node.getY());
                            enemytank.setDirect(node.getDirect());

                        }
                        enemytank.setSpeed(enemyTankSpeed);
                        enemytank.setShotSize(enemyTankShotSize);
                        //启动坦克线程
                        new Thread(enemytank).start();
                        //给该坦克创建子弹
                        Shot shot = new Shot(enemytank.getX() + 19, enemytank.getY() + 60, enemytank.getDirect());
                        //shot.setBricks(bricks);
                        //加入到Vector
                        enemytank.shots.add(shot);
                        //启动shot线程
                        new Thread(shot).start();

                        //加入
                        enemytanks.add(enemytank);
                        tanks.add(enemytank);

                        //让敌方的每一辆坦克都知道砖块和我方坦克的位置
                        enemytank.setTanks(tanks);
                        enemytank.setBricks(bricks);
                    }

                }
                //让我方坦克知道砖块和敌方坦克的位置
                mytank.setTanks(tanks);
                mytank.setBricks(bricks);

                //初始化图片对象
                image = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
                image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
                image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));
                new AePlayWave("src\\111.wav").start();
                //地图初始化
                map();
                break;
        }
        //敌人坦克初始化


    }


    //编写方法记录我方击毁坦克的信息
    public void DrawInfo(Graphics g) {
        g.setColor(Color.BLACK);
        Font font = new Font("宋体", Font.BOLD, 30);
        g.setFont(font);
        g.drawString("累计击毁敌方坦克", 1020, 50);
        drawTank(1030, 60, g, 0, 1);
        g.setColor(Color.black);
        g.drawString(Recorder.getEnemyTankNum() + "", 1080, 100);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //绘制矩形画成游戏区域
        g.fillRect(0, 0, 1000, 750);
        DrawInfo(g);
        //绘制自己的坦克
        if (mytank != null && mytank.isLive()) {
            drawTank(mytank.getX(), mytank.getY(), g, mytank.getDirect(), 0);

        }
        //绘制敌人的坦克
        for (int i = 0; i < enemytanks.size(); i++) {
            EnemyTank enemytank = enemytanks.get(i);//取出每一辆坦克的信息
            //判断是否存活
            if (enemytank.isLive()) {
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
            } else {
                enemytanks.remove(i);//移除这个坦克的位置 不然坦克样子不在了 我们的子弹还是碰到
                tanks.remove(i + 1);
            }

        }

        //绘制自己的子弹
        for (int i = 0; i < mytank.shots.size(); i++) {
            Shot shot = mytank.shots.get(i);
            //shot.touchBrick();
            if (shot != null && shot.isLive == true) {
                //场面上不存在子弹的时候
                g.setColor(Color.GREEN);
                g.draw3DRect(shot.x, shot.y, 2, 2, false);
            } else {
                mytank.shots.remove(shot);
            }
        }

        //画出炸弹爆炸 bombs集合中有的时候就画出
        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            if (bomb.life > 6) {
                g.drawImage(image, bomb.x, bomb.y, 60, 60, this);
            } else if (bomb.life > 3) {
                g.drawImage(image1, bomb.x, bomb.y, 60, 60, this);
            } else {
                g.drawImage(image2, bomb.x, bomb.y, 60, 60, this);
            }
            bomb.lifeDown();//看不懂啊，这里不是只会炸一次吗 为什么爆炸效果不停
            //如果bomblife 为0 就从bombs集合中删除
            if (bomb.life == 0) {
                bombs.remove(i);
            }
        }

        //绘制砖块
        for (int i = 0; i < bricks.size(); i++) {
            Brick brick = bricks.get(i);
            if (brick != null) {
                DrawBrick(brick.getX(), brick.getY(), g, 0);
            }
        }

    }

    //坦克样子
    public void drawTank(int x, int y, Graphics g, int direct, int type) {
        //x,y为坦克参考点坐标 //g画笔 //direct方向 //type为坦克类型
        switch (type) {
            case 0://自己的坦克
                g.setColor(Color.GREEN);//绿色
                break;
            case 1://敌人的坦克
                g.setColor(Color.RED);//红色
                break;
        }
        //根据坦克方向绘制坦克(0向上 1向右 2向下 3向左
        switch (direct) {
            case 0://向上
                g.fill3DRect(x, y, 10, 60, false);//画出坦克左边轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//坦克中间矩形
                g.fill3DRect(x + 30, y, 10, 60, false);//坦克右边轮子
                g.fillOval(x + 10, y + 20, 20, 20);//坦克中间圆
                g.drawLine(x + 20, y + 30, x + 20, y); //坦克炮筒
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

    //砖块样子
    public void DrawBrick(int x, int y, Graphics g, int type) {
        switch (type) {
            case 0://不可破坏的砖块
                g.setColor(Color.GRAY);
                break;
            case 1://可被破坏的砖块
                g.setColor(Color.orange);
                break;
        }
        g.fill3DRect(x, y, 15, 15, false);
    }

    //判断单个子弹是否击中坦克
    public void hitTank(Shot s, Tank tank) {
        //判断s击中坦克
        switch (tank.getDirect()) {
            case 0:
            case 2://敌人坦克在上下的方向的时候
                if (s.x > tank.getX() && s.x < tank.getX() + 40 && s.y > tank.getY()
                        && s.y < tank.getY() + 60) {
                    s.isLive = false;
                    tank.setLive(false);
                    if (tank instanceof EnemyTank) {
                        Recorder.addEnemyTankNum();
                    }
                    //击中坦克时 炸弹加入
                    Bomb bomb = new Bomb(tank.getX(), tank.getY());
                    bombs.add(bomb);
                }
                break;
            case 1:
            case 3:
                if (s.x > tank.getX() && s.x < tank.getX() + 60 && s.y > tank.getY()
                        && s.y < tank.getY() + 40) {
                    s.isLive = false;
                    tank.setLive(false);
                    if (tank instanceof EnemyTank) {
                        Recorder.addEnemyTankNum();
                    }
                    Bomb bomb = new Bomb(tank.getX(), tank.getY());
                    bombs.add(bomb);
                }
                break;

        }
    }

    //判断子弹是否击中敌方坦克
    public void hitEnemyTank() {
        //由于是多个子弹 我们现在需要挨个取出来判断
        for (int j = 0; j < mytank.shots.size(); j++) {
            Shot shot = mytank.shots.get(j);
            if (shot != null && shot.isLive) {
                //遍历敌人所有的坦克
                for (int i = 0; i < enemytanks.size(); i++) {
                    EnemyTank enemyTank = enemytanks.get(i);
                    hitTank(shot, enemyTank);//判断单个子弹是否碰到坦克
                }
            }
        }
    }

    //判断子弹是否击中我方坦克
    public void hitMytank() {
        for (int i = 0; i < enemytanks.size(); i++) {
            EnemyTank enemytank = enemytanks.get(i);//取出每个敌方坦克
            //遍历每一个坦克的所有的子弹
            for (int j = 0; j < enemytank.shots.size(); j++) {
                Shot shot = enemytank.shots.get(j);//取出每辆坦克的子弹
                if (mytank.isLive() && shot.isLive) {
                    hitTank(shot, mytank);
                }
            }
        }
    }

    //判断单个子弹是否撞墙
    public void hitBrick(Shot shot, Brick brick) {
        if (shot.x >= brick.getX()
                && shot.x <= brick.getX() + 15
                && shot.y >= brick.getY()
                && shot.y <= brick.getY() + 15) {
            if (shot.isLive) {
                shot.isLive = false;
            }
        }
    }

    //取出所有子弹调上面函数判断
    public void shotHitBrick() {
        for (int i = 0; i < tanks.size(); i++) {
            Tank tank = tanks.get(i);

            for (int j = 0; j < tank.shots.size(); j++) {
                Shot shot = tank.shots.get(j);
                for (int k = 0; k < bricks.size(); k++) {
                    Brick brick = bricks.get(k);
                    hitBrick(shot, brick);
                }
            }
        }
    }

    //创建地图
    public void map() {
        int brickSize = 250;
        for (int i = 0; i < brickSize; i++) {
            Brick brick = new Brick(60 + i * 15, 100);
            if (i >= 50) {
                brick = new Brick(160 + (i - 50) * 15, 230);
            }
            if (i >= 100) {
                brick = new Brick(60 + (i - 100) * 15, 360);
            }
            if (i >= 150) {
                brick = new Brick(160 + (i - 150) * 15, 490);
            }
            if (i >= 200) {
                brick = new Brick(60 + (i - 200) * 15, 620);
            }
            bricks.add(brick);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //坦克方向发生变化  先改变方向 然后走
        //按键WASD：控制方向
        if (e.getKeyCode() == KeyEvent.VK_W) {
//            mytank.setDirect(0);
//            mytank.moveUp();
            mytank.up1 = true;//利用这个方法可以消除按键的延迟
            mytank.left1 = false;
            mytank.right1 = false;
            mytank.down1 = false;

        } else if (e.getKeyCode() == KeyEvent.VK_S) {
//            mytank.setDirect(2);
//            mytank.moveDown();
            mytank.up1 = false;
            mytank.left1 = false;
            mytank.right1 = false;
            mytank.down1 = true;

        } else if (e.getKeyCode() == KeyEvent.VK_D) {
//            mytank.setDirect(1);
//            mytank.moveRight();
            //mytank.setX(mytank.getX()+1);
            mytank.up1 = false;
            mytank.left1 = false;
            mytank.right1 = true;
            mytank.down1 = false;
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
//            mytank.moveLeft();
//            mytank.setDirect(3);
            //mytank.setX(mytank.getX()-1);
            mytank.up1 = false;
            mytank.left1 = true;
            mytank.right1 = false;
            mytank.down1 = false;
        }

        //按键J:  发射子弹
        if (e.getKeyCode() == KeyEvent.VK_J) {
            //发射一颗子弹
//            if(mytank.shot == null || mytank.shot.isLive == false) {
//                mytank.MyShot();
//            }
            //发生多颗子弹
            if (mytank.isLive()) {
                mytank.MyShot();
            }
        }

        //重绘面板
        // this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true) {
            //刷新绘制界面
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //利用该方法消除按键延迟
            if (mytank.up1) {
                mytank.setDirect(0);
                mytank.moveUp();
            } else if (mytank.down1) {
                mytank.setDirect(2);
                mytank.moveDown();
            } else if (mytank.left1) {
                mytank.moveLeft();
                mytank.setDirect(3);
            } else if (mytank.right1) {
                mytank.setDirect(1);
                mytank.moveRight();
            }
            //是否击中我方坦克
            hitMytank();
            //是否击中敌方坦克
            hitEnemyTank();
            shotHitBrick();
            //重绘界面
            this.repaint();
        }
    }
}