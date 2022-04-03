package service;

import Domain.House;

/**
 * 定义House[] 保存House对象
 * 完成对房屋的各种操作
 * */
public class HouseService {
    private House[] houses;
    private int houseNums = 1;//记录当前房屋数量
    private int idCounter = 1;//记录当前id增长到哪个值
    //默认构造 创造房屋
    public HouseService(int size) { //创建HouseService对象 指定数组大小
        houses = new House[size];
        houses[0] = new House(1,"jack","1212","海淀区",2000,"未出租");//测试数据
    }

    //返回houseList
    public House[] list(){
        return houses;
    }

    //编写方法 增加房屋 加入到house数组里 返回bool
    public boolean add(House newhouses) {
        //暂时不考虑数组的扩容问题
        if (houseNums == houses.length) {
            System.out.println("数组已满 不能添加");
            return false;
        }
        houses[houseNums] = newhouses;
        houseNums++;
        //设置一个id自增长机制 自动更新新房子的id
        idCounter++;
        newhouses.setId(idCounter);
        return true;
    }

    //删除一个房屋信息
    public boolean del(int delId){
        //先找到要删除的房屋信息
        int index = -1;
        for (int i = 0; i < houseNums; i++) {
            if(delId == houses[i].getId()){
                index = i; //记录位置
            }
        }
        if(index == -1){//说明要删除的id不存在
           return false;
        }
        for(int i = index; i < houseNums -1;i++) {//后面往前面覆盖
            houses[i] = houses[i+1];
        }
        houses[houseNums - 1] = null;
        houseNums--;
        return true;
    }

    //返回一个house对象或者null
    public House findById(int findId){
        for (int i = 0; i < houseNums; i++) {
            if(findId == houses[i].getId()){
                return houses[i];
            }
        }
        return null;
    }
}
