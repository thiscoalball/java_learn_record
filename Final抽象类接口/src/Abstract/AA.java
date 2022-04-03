package Abstract;

class AA {
    //计算1加到10000
    public void job(){
        //得到开始的时间
       // long start = System.currentTimeMillis()原先方法
        int number = 0;
        for (int i = 1; i < 1000000; i++) {
            number += i;
        }
        //得到结束的时间
        long end = System.currentTimeMillis();
        //System.out.println("AA执行时间为" + (end - start) + "ms");原先方法

    }
    //这里是改进方法 //在这个改进方法上我们还可以进一步改进 因为AA和BB都要用到
    // 如果还有CC和DD 那么就很冗余了 所以我们可以把这个函数用抽象类实现
    public void calculateTime(){
        long start = System.currentTimeMillis();
        job();//这样每次要计算的时间差只需要在job里修改就可以了
        long end = System.currentTimeMillis();
        System.out.println("利用模板设计的执行时间为" + (end - start) + "ms");
    }
}

class BB {
    //计算1加到10000
    public void job(){
        //得到开始的时间
        long start = System.currentTimeMillis();
        int number = 0;
        for (int i = 1; i < 1000000; i++) {//我们会发现统计时间的话 这样写每次都要调用
            // long start = System.currentTimeMillis();
            //long end = System.currentTimeMillis();
            //代码复用性非常的不好 所以可以考虑利用设计模式
            //改进方法在AA类里
            number += i/2;
        }
        //得到结束的时间
        long end = System.currentTimeMillis();
        System.out.println("BB执行时间为" + (end - start) + "ms");
    }
}
