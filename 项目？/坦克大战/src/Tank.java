import java.util.Vector;

public class Tank {//坦克类
    private int x; //横坐标
    private int y; //纵坐标
    private int direct;//0向上 1向右 2向下 3向左
    private int speed = 4;//坦克速度
    private boolean isLive = true;
    private int shotSize = 0;//坦克的子弹数量

    Vector<Brick> bricks = new Vector<>();
    Vector<Shot> shots = new Vector<>();//储存坦克子弹
    Vector<Tank> tanks = new Vector<>();

    boolean up = true, down = true, right = true, left = true;


    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //提供一个方法获取地图所有的砖块
    public void setBricks(Vector<Brick> bricks) {
        this.bricks = bricks;
    }

    public void setTanks(Vector<Tank> tanks) {
        this.tanks = tanks;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
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

    public boolean isTouchBrick() {
        switch (this.getDirect()) {
            case 0:
                for (int i = 0; i < bricks.size(); i++) {
                    Brick brick = bricks.get(i);
                    if (this.getX() >= brick.getX()
                            && this.getX() <= brick.getX() + 15
                            && this.getY() >= brick.getY()
                            && this.getY() <= brick.getY() + 15
                    )
                        return true;
                    if (this.getX() + 40 >= brick.getX()
                            && this.getX() + 40 <= brick.getX() + 15
                            && this.getY() >= brick.getY()
                            && this.getY() <= brick.getY() + 15
                    )
                        return true;
                }
                break;
            case 1:
                for (int i = 0; i < bricks.size(); i++) {
                    Brick brick = bricks.get(i);
                    if (this.getX() + 60 >= brick.getX()
                            && this.getX() + 60 <= brick.getX() + 15
                            && this.getY() >= brick.getY()
                            && this.getY() <= brick.getY() + 11
                    )
                        return true;
                    if (this.getX() + 60 >= brick.getX()
                            && this.getX() + 60 <= brick.getX() + 15
                            && this.getY() + 40 >= brick.getY()
                            && this.getY() + 40 <= brick.getY() + 11
                    )
                        return true;
                    if (this.getX() + 2 >= brick.getX()
                            && this.getX() + 2 <= brick.getX() + 15
                            && this.getY() + 20 >= brick.getY()
                            && this.getY() + 20 <= brick.getY() + 15
                    )
                        return true;
                }
                break;
            case 2:
                for (int i = 0; i < bricks.size(); i++) {
                    Brick brick = bricks.get(i);
                    if (this.getX() >= brick.getX()
                            && this.getX() <= brick.getX() + 15
                            && this.getY() + 60 >= brick.getY()
                            && this.getY() + 60 <= brick.getY() + 15
                    )
                        return true;
                    if (this.getX() + 40 >= brick.getX()
                            && this.getX() + 40 <= brick.getX() + 15
                            && this.getY() + 60 >= brick.getY()
                            && this.getY() + 60 <= brick.getY() + 15
                    )
                        return true;
                }
                break;
            case 3:
                for (int i = 0; i < bricks.size(); i++) {
                    Brick brick = bricks.get(i);
                    if (this.getX() >= brick.getX()
                            && this.getX() <= brick.getX() + 15
                            && this.getY() >= brick.getY()
                            && this.getY() <= brick.getY() + 11
                    )
                        return true;

                    if (this.getX() >= brick.getX()
                            && this.getX() <= brick.getX() + 15
                            && this.getY() + 40 >= brick.getY()
                            && this.getY() + 40 <= brick.getY() + 11
                    )
                        return true;
                    if (this.getX() + 2 > brick.getX()
                            && this.getX() + 2 < brick.getX() + 15
                            && this.getY() + 20 > brick.getY()
                            && this.getY() + 20 < brick.getY() + 15
                    )
                        return true;
                }
                break;
        }
        return false;
    }

    public boolean isTouchTank() {
        switch (this.getDirect()) {
            case 0://上
                for (int i = 0; i < tanks.size(); i++) {
                    Tank tank = tanks.get(i);
                    if (tank != this) {//不和自己比较
                        //如果敌人坦克是上下方向
                        if (tank.getDirect() == 0 || tank.getDirect() == 2) {
                            //考虑当前坦克向上的左上角坐标
                            if (this.getX() >= tank.getX()
                                    && this.getX() <= tank.getX() + 40
                                    && this.getY() >= tank.getY()
                                    && this.getY() <= tank.getY() + 60) {
                                return true;
                            }
                            //当前坦克的向上的右上角坐标
                            if (this.getX() + 40 >= tank.getX()
                                    && this.getX() + 40 <= tank.getX() + 40
                                    && this.getY() >= tank.getY()
                                    && this.getY() <= tank.getY() + 60) {
                                return true;
                            }
                        }
                        //如果敌人坦克是左右方向
                        if (tank.getDirect() == 1 || tank.getDirect() == 3) {
                            //判断当前坦克的左上角坐标
                            if (this.getX() >= tank.getX()
                                    && this.getX() <= tank.getX() + 60
                                    && this.getY() >= tank.getY()
                                    && this.getY() <= tank.getY() + 40) {
                                return true;
                            }
                            if (this.getX() + 40 >= tank.getX()
                                    && this.getX() + 40 <= tank.getX() + 60
                                    && this.getY() >= tank.getY()
                                    && this.getY() <= tank.getY() + 40) {
                                return true;
                            }

                        }
                    }

                }
                break;
            case 1://右
                for (int i = 0; i < tanks.size(); i++) {
                    Tank tank = tanks.get(i);
                    if (tank != this) {//不和自己比较
                        //如果敌人坦克是上下方向
                        if (tank.getDirect() == 0 || tank.getDirect() == 2) {
                            //考虑当前坦克的右上角坐标
                            if (this.getX() + 60 >= tank.getX()
                                    && this.getX() + 60 <= tank.getX() + 40
                                    && this.getY() >= tank.getY()
                                    && this.getY() <= tank.getY() + 60) {
                                return true;
                            }
                            //当前坦克的的右下角坐标
                            if (this.getX() + 60 >= tank.getX()
                                    && this.getX() + 60 <= tank.getX() + 40
                                    && this.getY() + 40 >= tank.getY()
                                    && this.getY() + 40 <= tank.getY() + 60) {
                                return true;
                            }
                        }
                        //如果敌人坦克是左右方向
                        if (tank.getDirect() == 1 || tank.getDirect() == 3) {
                            //判断当前坦克的右上角坐标
                            if (this.getX() + 60 >= tank.getX()
                                    && this.getX() + 60 <= tank.getX() + 60
                                    && this.getY() >= tank.getY()
                                    && this.getY() <= tank.getY() + 40) {
                                return true;
                            }
                            //当前坦克的向上的右下角坐标
                            if (this.getX() + 60 >= tank.getX()
                                    && this.getX() + 60 <= tank.getX() + 60
                                    && this.getY() + 40 >= tank.getY()
                                    && this.getY() + 40 <= tank.getY() + 40) {
                                return true;
                            }

                        }
                    }
                }
                break;
            case 2://下
                for (int i = 0; i < tanks.size(); i++) {
                    Tank tank = tanks.get(i);
                    if (tank != this) {//不和自己比较
                        //如果敌人坦克是上下方向
                        if (tank.getDirect() == 0 || tank.getDirect() == 2) {
                            //考虑当前坦克的左下角坐标
                            if (this.getX() >= tank.getX()
                                    && this.getX() <= tank.getX() + 40
                                    && this.getY() + 60 >= tank.getY()
                                    && this.getY() + 60 <= tank.getY() + 60) {
                                return true;
                            }
                            //当前坦克的向上的右下角坐标
                            if (this.getX() + 40 >= tank.getX()
                                    && this.getX() + 40 <= tank.getX() + 40
                                    && this.getY() + 60 >= tank.getY()
                                    && this.getY() + 60 <= tank.getY() + 60) {
                                return true;
                            }
                        }
                        //如果敌人坦克是左右方向
                        if (tank.getDirect() == 1 || tank.getDirect() == 3) {
                            //判断当前坦克的左下角坐标
                            if (this.getX() >= tank.getX()
                                    && this.getX() <= tank.getX() + 60
                                    && this.getY() + 60 >= tank.getY()
                                    && this.getY() + 60 <= tank.getY() + 40) {
                                return true;
                            }
                            //当前坦克的向上的右下角坐标
                            if (this.getX() + 40 >= tank.getX()
                                    && this.getX() + 40 <= tank.getX() + 60
                                    && this.getY() + 60 >= tank.getY()
                                    && this.getY() + 60 <= tank.getY() + 40) {
                                return true;
                            }

                        }
                    }
                }
                break;
            case 3://左
                for (int i = 0; i < tanks.size(); i++) {
                    Tank tank = tanks.get(i);
                    if (tank != this) {//不和自己比较
                        //如果敌人坦克是上下方向
                        if (tank.getDirect() == 0 || tank.getDirect() == 2) {
                            //考虑当前坦克的左上坐标
                            if (this.getX() >= tank.getX()
                                    && this.getX() <= tank.getX() + 40
                                    && this.getY() >= tank.getY()
                                    && this.getY() <= tank.getY() + 60) {
                                return true;
                            }
                            //当前坦克的向上的左下角坐标
                            if (this.getX() >= tank.getX()
                                    && this.getX() <= tank.getX() + 40
                                    && this.getY() + 40 >= tank.getY()
                                    && this.getY() + 40 <= tank.getY() + 60) {
                                return true;
                            }
                        }
                        //如果敌人坦克是左右方向
                        if (tank.getDirect() == 1 || tank.getDirect() == 3) {
                            //判断当前坦克的左上角坐标
                            if (this.getX() >= tank.getX()
                                    && this.getX() <= tank.getX() + 60
                                    && this.getY() >= tank.getY()
                                    && this.getY() <= tank.getY() + 40) {
                                return true;
                            }
                            //当前坦克的向上的左下角坐标
                            if (this.getX() >= tank.getX()
                                    && this.getX() <= tank.getX() + 60
                                    && this.getY() + 40 >= tank.getY()
                                    && this.getY() + 40 <= tank.getY() + 40) {
                                return true;
                            }

                        }
                    }
                }
                break;
        }
        return false;
    }

    public void moveUp() {
        //y-=3;这样操作速度也麻烦 直接再定义一个变量speed 提
        // 供一个set接口 直接在外面就可以很方便的改速度
        if (getY() > 0 && !isTouchBrick() && !isTouchTank()) {//这一些if判断是防止坦克超出边界
            y -= speed;
        }
    }

    public void moveDown() {
        // y+=3;

        if ((getY() + 60) < 750 && !isTouchBrick() && !isTouchTank()) {
            y += speed;
        }
    }

    public void moveRight() {
        if ((getX() + 60) < 1000 && !isTouchBrick() && !isTouchTank()) {
            x += speed;
        }
    }

    public void moveLeft() {
        //x-=3;
        if (getX() > 0 && !isTouchBrick() && !isTouchTank()) {
            x -= speed;
        }
    }

    public void setShotSize(int shotSize) {
        this.shotSize = shotSize;
    }

    public int getShotSize() {
        return shotSize;
    }

    //封装坦克发射子弹函数
    public void shoting() {
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
