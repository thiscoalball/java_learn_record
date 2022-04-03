package CustomGeneric;

public class CustomInterfaceGeneric {
}
interface IUsb<T,R>{
    R get(T t);//方法可以使用
    void hi(R r);
}
interface  IA extends IUsb<String,Double>{}

class AA implements IA{//这里的泛型是被IA实现了 因为IA继承了 IUsb
    @Override
    public Double get(String s) {
        return null;
    }

    @Override
    public void hi(Double aDouble) {

    }
}