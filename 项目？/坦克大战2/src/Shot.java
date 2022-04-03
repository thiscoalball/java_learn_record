import java.util.Vector;

@SuppressWarnings({"all"})

public class Shot implements Runnable {
    int x;//子弹的x坐标
    int y;//子弹的y坐标
    int direct = 0; //子弹的方向
    int speed = 2; //子弹的速度
    boolean isLive = true; //子弹是否还存活

    Vector<Brick> bricks = new Vector<>();


    public void setSpeed(int speed) {
        this.speed = speed;//封装接口方便更改速度
    }

    public void setBricks(Vector<Brick> bricks) {
        this.bricks = bricks;
    }

    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    @Override
    public void run() {//射击行为

        while (true) {
            try {
                Thread.sleep(15);//休眠50ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //根据方向改变x，y坐标
            switch (direct) {
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
            //System.out.println("子弹x = " +x+" y= " + y);//测试
            //如果不 && isLive的话 子弹在打到敌方坦克后只是不绘制了，但是还在
            //isLive 在打到敌人坦克之后就会变为 false 所以可以在这 &&
            if (!(x >= 0 && x <= 1000 && y >= 0 && y <= 750)) {//出界了，就跳出
                isLive = false;
                break;
            }

        }
    }
}
