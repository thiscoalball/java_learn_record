package Abstract;

abstract class Template {
    //我靠这个设计思路简直了！！！ 太妙了吧我的天！！！这样外面随便实现job都可以
    //然后抽象函数到里面再来调用公共的计算时间
    public abstract void job();

    public void calculateTime(){//实现方法 调用抽象方法
        long start = System.currentTimeMillis();
        job();//
        long end = System.currentTimeMillis();
        System.out.println("利用抽象模板的执行时间为" + (end - start) + "ms");
    }
}
