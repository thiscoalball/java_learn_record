import java.util.Vector;

@SuppressWarnings({"all"})

public class MyTank extends Tank {

    Shot shot = null;
    boolean up1 = true, down1 = true, right1 = true, left1 = true;

    public MyTank(int x, int y) {
        super(x, y);
    }

    public void MyShot() {
        if (shots.size() == getShotSize()) {
            return;
        }
        shoting();//面向对象真的好用
    }
}
