@SuppressWarnings({"all"})
public class LifeBrick extends Brick{
    private boolean islife = true;//该砖块的生命
    public LifeBrick(int x, int y) {
        super(x, y);
    }

    public boolean isIslife() {
        return islife;
    }

    public void setIslife(boolean islife) {
        this.islife = islife;
    }
}
