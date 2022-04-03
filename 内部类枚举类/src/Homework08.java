public class Homework08 {
    public static void main(String[] args) {
        Color red = Color.RED;
        red.show();
        switch (red){
            case RED:
                System.out.println("匹配到了红色");
                break;
            case BLUE:
                System.out.println("匹配到蓝色");
                break;
            default:
                System.out.println("没匹配到");
        }
    }

}
interface InterColor{
    public void show();

}

enum Color implements InterColor{

    RED(255,0,0),BLUE(0,0,255),
    BLACK(0,0,0),YELLOW(255,255,0),
    GREEN(0,255,0);

    private int redValue;
    private int greenValue;
    private int blueValue;

    Color(int redValue, int greenValue, int blueValue) {
        this.redValue = redValue;
        this.greenValue = greenValue;
        this.blueValue = blueValue;
    }

    @Override
    public void show() {
        System.out.println("属性为" + redValue + "," + greenValue + "," + blueValue);
    }
}