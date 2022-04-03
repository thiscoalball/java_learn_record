package extend.extendHomework;

public class Test {
    public static void main(String[] args) {
        PC pc = new PC("Intel", "三星", "西数", "华硕");
        pc.printInfo();
        NotePad notePad = new NotePad("AMD", "sanxing", "sanxing", "blue");
        notePad.printInfo();
    }
}
