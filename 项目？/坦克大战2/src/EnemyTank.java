@SuppressWarnings({"all"})
public class EnemyTank extends Tank implements Runnable {

    public EnemyTank(int x, int y) {
        super(x, y);
    }
    //增加成员vector 可以到到敌人坦克的Vector 方便与其他的敌人坦克进行比较
//


    public void EnemyTankMoveup() {

        for (int i = 0; i < 30; i++) {
            moveUp();
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void EnemyTankMoveLeft() {
        for (int i = 0; i < 30; i++) {
            moveLeft();
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void EnemyTankMoveRight() {
        for (int i = 0; i < 30; i++) {
            moveRight();
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void EnemyTankMoveDown() {
        for (int i = 0; i < 30; i++) {
            moveDown();
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        while (true) {

            //根据坦克的方向继续移动 然后随机改变方向
            if (shots.size() < getShotSize() && isLive()) {//没子弹了 创建一颗子弹放到shots集合

                shoting();
            }
            switch (getDirect()) {
                case 0:
                    //让坦克先保持一个方向走几步
                    EnemyTankMoveup();
                    break;
                case 1:
                    EnemyTankMoveRight();
                    break;
                case 2:
                    EnemyTankMoveDown();
                    break;
                case 3:
                    EnemyTankMoveLeft();
                    break;
            }

            setDirect((int) (Math.random() * 4));
            //坦克被击中后 结束该线程
            if (isLive() == false) {
                break;
            }
        }
    }
}
