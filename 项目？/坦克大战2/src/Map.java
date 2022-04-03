import java.awt.*;
import java.util.Vector;
@SuppressWarnings({"all"})

public class Map {
    private Brick brick;
    private Vector<Brick> bricks = new Vector<>();
    private int brickSize = 0;
    private int MapId;
    private int type;

    public Map(Brick brick, Vector<Brick> bricks, int mapId) {
        this.brick = brick;
        this.bricks = bricks;
        MapId = mapId;
        switch (mapId) {
            case 1:
                map1();
                break;
            case 2:
                map2();
                break;
        }
    }

    public Brick getBrick() {
        return brick;
    }

    public void setBrick(Brick brick) {
        this.brick = brick;
    }

    public Vector<Brick> getBricks() {
        return bricks;
    }

    public void setBricks(Vector<Brick> bricks) {
        this.bricks = bricks;
    }

    public int getMapId() {
        return MapId;
    }

    public void setMapId(int mapId) {
        MapId = mapId;
    }

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

    public void map1() {
        brickSize = 250;
        for (int i = 0; i < brickSize; i++) {
            brick = new Brick(60 + i * 15, 100);
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

    public void map2() {

        for (int i = 0; i < 30; i++) {
            if (i < 15) {
                for (int j = 0; j < 4; j++) {
                    brick = new Brick(200 + j * 200, 80 + i * 15);
                    bricks.add(brick);
                }
            } else {
                for (int j = 0; j < 4; j++) {
                    brick = new Brick(200 + j * 200, 500+ (i - 20) * 15);
                    bricks.add(brick);
                }
            }
        }
    }

    public void DrawMap(Graphics g) {
        for (int i = 0; i < bricks.size(); i++) {
            Brick brick = bricks.get(i);
            if (brick != null) {
                DrawBrick(brick.getX(), brick.getY(), g, 0);
            }
        }
    }
}
