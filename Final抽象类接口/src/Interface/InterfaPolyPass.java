package Interface;

public class InterfaPolyPass {
   IG ig =  new Teacher();//接口类型的变量可以指向 实现该接口的类的对象实例
   IH ih = new Teacher(); //如果IG继承了IH的接口 而techer也实现了IG接口 那么相当于Teacher也实现了IH接口
   //这就是接口所谓的多态传递现象
}

interface IH{
    void hi();
}
interface IG extends IH{}
class Teacher implements IG{
    @Override
    public void hi() {

    }
}