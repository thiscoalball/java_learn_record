package InnerClass;

public class InnerClassExercise2 {
    public static void main(String[] args) {
        CellPhone cellPhone = new CellPhone();
        cellPhone.alarmClock(new Bell(){
            @Override
            public void ring() {
                System.out.println("懒猪起床了");
            }
        });
        cellPhone.alarmClock(new Bell(){
            @Override
            public void ring() {
                System.out.println("上课了好哥哥们");//这就是匿名内部类的好处了 方便在这里直接改变
                //不然如果像常规创建常规的类的话，一修改类的ring外面都会被改变
            }
        });
    }
}
interface Bell{
    void ring();
}

class CellPhone{
    public void alarmClock(Bell bell){
        bell.ring();
    }
}