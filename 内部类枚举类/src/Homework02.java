public class Homework02 {
    public static void main(String[] args) {

        System.out.println(Frock.getNextNum());
        System.out.println(Frock.getNextNum());

        Frock frock = new Frock();
        Frock frock1 = new Frock();
        Frock frock2 = new Frock();
        System.out.println("frock NUM: " + frock.getSerialNumber());
        System.out.println("frock1 NUM: " + frock1.getSerialNumber());
        System.out.println("frock2 NUM: " + frock2.getSerialNumber());


    }
}

class Frock{
    private static int currentNum = 100000;
    private int serialNumber = 0;

    public static int getNextNum(){
        return currentNum+=100;
    }

    public Frock() {
        this.serialNumber = getNextNum();
    }


    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }
}
