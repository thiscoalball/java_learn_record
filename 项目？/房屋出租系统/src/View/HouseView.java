package View;

import Domain.House;
import Utils.Utility;
import service.HouseService;

/**
 * 1 显示界面
 * 2 接收输入
 * 3 调用HouseService完成操作
 * */
public class HouseView {
    private boolean loop = true; //控制菜单循环显示
    private char key = ' '; //接收用户选择

    private HouseService houseService = new HouseService(10);  //提供一个接口

    //显示mainMenu
    public void mainMenu(){
        do{
            System.out.println("==============房屋出租系统=============");
            System.out.println("\t\t\t1 新增房源");
            System.out.println("\t\t\t2 查找房屋");
            System.out.println("\t\t\t3 删除房屋");
            System.out.println("\t\t\t4 修改房屋信息");
            System.out.println("\t\t\t5 房屋列表");
            System.out.println("\t\t\t6 退出");
            System.out.print("请输入你的选择(1-6):");
            key = Utility.readChar();
            switch (key){
                case '1':
                    addHouse();
                    break;
                case '2':
                    findHouse();
                    break;
                case '3':
                    deleteHouse();
                    break;
                case '4':
                    update();
                    break;
                case '5':
                    ListHouses();
                    break;
                case '6':
                    char c = Utility.readConfirmSelection();
                    if(c == 'Y') {
                        loop = false;
                    }
                    break;
            }
        }while(loop);
    }

    //显示房屋列表界面
    public void ListHouses(){
        System.out.println("--------------房屋列表-------------");
        System.out.println("编号\t\t房主\t\t电话\t\t地址\t\t月租\t\t状态（已出租/未出租）");
        House[] houses = houseService.list();//获取房屋列表数组
        for (int i = 0; i < houses.length; i++) {
            if(houses[i] == null) { break; }
            System.out.println(houses[i]);
        }
        System.out.println("------------房屋列表显示完毕-----------\n");
    }

    //显示添加房屋界面 接收用户输入
    public void addHouse(){
        System.out.println("--------------添加房屋-------------");
        System.out.print("姓名：");
        String name = Utility.readString(8);
        System.out.print("电话：");
        String phone = Utility.readString(11);
        System.out.print("地址：");
        String address = Utility.readString(16);
        System.out.print("月租：");
        int rent = Utility.readInt();
        System.out.print("状态：");
        String state = Utility.readString(3);

        //输入完信息后 new一个对象 注意id是系统分配的用户不能输入
        House newHouse = new House(0, name, phone, address, rent, state);//将数据写入数组id在服务函数里自动分配
        if(houseService.add(newHouse)) {
            System.out.println("-------------添加房屋完成------------");
        }
        else{
            System.out.println("-------------添加房屋失败------------");
        }

    }

    //显示删除房屋界面 接收输入的id 调用Service的方法删除
    public void deleteHouse(){
        System.out.print("请输入要删除的房屋的编号（-1退出）：");
        int delId = Utility.readInt();
        if(delId == -1){
            System.out.println("-----------放弃删除房屋------------");
            return; //不要用break return代表结束了一个方法
        }
        char choice = Utility.readConfirmSelection();
        if(choice == 'Y'){
            if(houseService.del(delId)) {
                System.out.println("---------------删除房屋信息成功-------------");
            }
            else{
                System.out.println("--------------房屋编号不存在 删除失败---------------");
            }
        }
        else{
            System.out.println("--------------放弃删除房屋信息--------------");
        }
        System.out.println();
    }

    //显示查找房屋界面
    public void findHouse() {
        System.out.println("----------查找房屋信息---------");
        System.out.print("请输入你要查找的Id:");
        int findId = Utility.readInt();
        House house = houseService.findById(findId);
        if(house!= null){
            System.out.println("编号\t\t房主\t\t电话\t\t地址\t\t月租\t\t状态（已出租/未出租）");
            System.out.println(house);//打印house类里面的toString里的
        }
        else{
            System.out.println("--------ID不存在----------");
        }
    }

    //修改房屋信息
    public void update(){
        System.out.println("------------修改房屋信息-----------");
        System.out.print("请选择要修改的房屋的编号（-1退出）:");
        int updateId = Utility.readInt();
        if(updateId == -1){
            System.out.println("\n你放弃了修改信息");
            return;
        }
       House house = houseService.findById(updateId);
        if(house == null){
            System.out.println("你要修改的房屋编号不存在");
            return;
        }
        else{
            System.out.print("姓名("+house.getName() +"):");
            String name = Utility.readString(8,"");//这里用户直接回车表示不修改
            if(!"".equals(name)){
                house.setName(name);//修改完成
            }

            System.out.print("电话("+house.getPhone() +"):");
            String phone = Utility.readString(12,"");
            if(!"".equals(phone)){
                house.setPhone(phone);
            }

            System.out.print("地址("+house.getAddress() +"):");
            String address = Utility.readString(18,"");
            if(!"".equals(address))
            {
                house.setAddress(address);
            }

            System.out.print("租金("+house.getRent() +"):");
            int rent = Utility.readInt(-1);
            if(rent != -1){
                house.setRent(rent);
            }

            System.out.print("状态("+house.getState() +"):");
            String state = Utility.readString(3,"");
            if(!"".equals(state)){
                house.setState(state);
            }


        }
    }
}
