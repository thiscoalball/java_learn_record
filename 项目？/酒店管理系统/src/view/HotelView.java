package view;

import domain.*;
import service.BillService;
import service.DiningTableService;
import service.EmployeeService;
import service.MenuService;
import utils.Utility;

import javax.rmi.CORBA.Util;
import java.sql.SQLException;
import java.util.List;

public class HotelView {

    private boolean loop = true;
    private String key = "";

    private EmployeeService employeeService = new EmployeeService();
    private DiningTableService diningTableService = new DiningTableService();
    private MenuService menuService = new MenuService();
    private BillService billService = new BillService();

    public static void main(String[] args) throws SQLException {
        HotelView hotelView = new HotelView();
        hotelView.mainMenu();
    }

    //显示所有餐桌状态
    public void listDingingTable() throws SQLException {
        System.out.println();
        System.out.println("================显示餐桌状态================");
        List<DiningTable> list = diningTableService.list();
        System.out.println("餐桌编号\t\t餐桌状态");
        for (DiningTable diningTable : list) {
            System.out.println(diningTable);
        }
        System.out.println("==========================================");
        System.out.println();
    }

    //完成餐桌预定
    public void orderDiningTable() throws SQLException {
        System.out.println();
        System.out.println("==================预定餐桌===================");
        System.out.println("  请选择要预定的桌号 (-1退出)");
        int tableId = Utility.readInt();
        if (tableId == -1) {
            System.out.println("===============取消预定餐桌================");
            return;
        }

        char key = Utility.readConfirmSelection();
        if (key == 'Y') {
            //根据TableID返回对应的table对象
            DiningTable diningTableByID = diningTableService.getDiningTableByID(tableId);
            if (diningTableByID == null) {
                System.out.println("===============预定餐桌不存在================");
                System.out.println();
                return;
            }
            //有这个餐桌 判断是不是空状态
            if (!("空".equals(diningTableByID.getState()))) {
                System.out.println("================该餐桌已在使用=================");
                System.out.println();
                return;
            }
            //这时真的可以预定
            System.out.println("请输入您的姓名");
            String orderName = Utility.readString(20);
            System.out.println("请输入你的电话号码");
            String orderTel = Utility.readString(20);
            //更新状态
            if (diningTableService.orderDiningTable(tableId, orderName, orderTel)) {
                System.out.println("=================预定餐桌成功==================");
                System.out.println();
            } else {
                System.out.println("===================预定失败====================");
                System.out.println();
            }
        } else {
            System.out.println("===============取消预定餐桌================");
            System.out.println();
            return;
        }
    }

    //显示所有菜品
    public void listMenu() throws SQLException {
        System.out.println();
        System.out.println("===================菜单===================");
        System.out.println("菜品编号\t\t菜品名\t\t类别\t\t价格");
        List<Menu> list = menuService.list();
        for (Menu menu : list) {
            System.out.println(menu);
        }
        System.out.println("=================显示完毕=================");
        System.out.println();
    }

    //完成点餐
    public void orderMenu() throws SQLException {
        System.out.println("\n=================点餐服务==================");
        System.out.println("  请输入点餐的桌号(-1退出)");
        int orderDiningTableId = Utility.readInt();
        if (orderDiningTableId == -1) {
            System.out.println("==================取消点餐=================\n");
            return;
        }
        //验证桌号是否存在
        if (diningTableService.getDiningTableByID(orderDiningTableId) == null) {
            System.out.println("================餐桌不存在================\n");
            return;
        }


        System.out.println("  请输入点餐的菜品号(-1退出)");
        int orderMenuId = Utility.readInt();
        if (orderMenuId == -1) {
            System.out.println("==================取消点餐=================\n");
            return;
        }
        //验证菜品号是否存在
        if (menuService.geyMenuById(orderMenuId) == null) {
            System.out.println("===============菜品号不存在===============\n");
            return;
        }

        System.out.println("  请输入点餐的菜品数量(-1退出)");
        int orderNums = Utility.readInt();
        if (orderNums == -1) {
            System.out.println("==================取消点餐=================\n");
            return;
        }

        if (billService.orderMenu(orderMenuId, orderNums, orderDiningTableId)) {
            System.out.println("=================点餐成功=================\n");
        } else {
            System.out.println("=================点餐失败=================\n");
        }

    }

    //完成账单信息
    public void listBill() throws SQLException {
//        List<Bill> Bills = billService.list();
//        System.out.println("\n===============================账单信息如下===========================================");
//        System.out.println("编号\t\t菜品号\t\t菜品量\t\t金额\t\t桌号\t\t日期\t\t\t\t\t\t\t状态");
//        for (Bill bill : Bills) {
//            System.out.println(bill);
//        }
//        System.out.println("====================================================================================\n");
        List<MultiTableBean> multiTableBeans = billService.list2();
        System.out.println("\n===================================================账单信息如下============================================================");
        System.out.println("编号\t\t菜品号\t\t菜品名\t\t菜品单价\t\t菜品量\t\t总金额\t\t桌号\t\t日期\t\t\t\t\t\t\t状态");
        for (MultiTableBean multiTableBean : multiTableBeans) {
            System.out.println(multiTableBean);
        }
        System.out.println("=========================================================================================================================\n");
    }

    //完成结账
    public void payBill() throws SQLException {
        System.out.println("\n===================结账服务====================");

        System.out.println("请选择要结账的桌号(-1退出)");
        int diningTableId = Utility.readInt();
        if (diningTableId == -1) {
            System.out.println("=======================取消结账======================\n");
            return;
        }
        //验证餐桌是否存在
        if ((diningTableService.getDiningTableByID(diningTableId)) == null) {
            System.out.println("===================结账餐桌不存在==================\n");
            return;
        }
        //查看餐桌是否需要结账
        if (!(billService.hasPayBillByDiningTableId(diningTableId))) {
            System.out.println("==================餐桌无账单需要结账=================\n");
            return;
        }
        System.out.println("请选择结账方式(现金/支付宝/微信):回车表示退出");
        String state = Utility.readString(20, "");//为空说明如果回车就返回空船
        if ("".equals(state)) {
            System.out.println("=======================取消结账======================\n");
            return;
        }
        if (billService.payBill(state, diningTableId)) {
            System.out.println("=======================结账成功======================\n");
        } else {
            System.out.println("=======================结账失败======================\n");
        }
    }


    //显示主菜单
    public void mainMenu() throws SQLException {
        while (loop) {
            System.out.println("===================酒店管理系统===================");
            System.out.println("\t\t\t\t1 登录管理系统");
            System.out.println("\t\t\t\t2 退出管理系统");
            System.out.println("================================================");
            System.out.println("请输入你的选择");
            key = Utility.readString(1);
            switch (key) {
                case "1":
                    System.out.println("请输入员工号：");
                    String empId = Utility.readString(50);
                    System.out.println("输入密码：");
                    String pwd = Utility.readString(20);
                    Employee employee = employeeService.EmployeeByIdAndPwd(empId, pwd);
                    //前往数据库进行判断 不为空说明用户存在
                    if (employee != null) {
                        System.out.println(employee.getName() + " 登录成功");
                        while (loop) {
                            System.out.println("===============管理系统二级菜单===============");
                            System.out.println("\t\t\t\t1 显示餐桌状态");
                            System.out.println("\t\t\t\t2 预定餐桌 ");
                            System.out.println("\t\t\t\t3 显示所有菜品 ");
                            System.out.println("\t\t\t\t4 点餐服务 ");
                            System.out.println("\t\t\t\t5 查看账单 ");
                            System.out.println("\t\t\t\t6 结账 ");
                            System.out.println("\t\t\t\t9 退出 ");
                            System.out.println("============================================");

                            key = Utility.readString(1);
                            switch (key) {
                                case "1":
                                    listDingingTable();    //显示所有餐桌状态
                                    break;
                                case "2":
                                    orderDiningTable();    //预定餐桌
                                    break;
                                case "3":
                                    listMenu();
                                    break;
                                case "4":
                                    orderMenu();
                                    break;
                                case "5":
                                    listBill();
                                    break;
                                case "6":
                                    payBill();
                                    break;
                                case "9":
                                    System.out.println("退出");
                                    loop = false;
                                    break;
                                default:
                                    System.out.println("并不处理");
                                    break;
                            }
                        }
                    } else {
                        System.out.println("登录失败");
                    }
                    break;
                case "2":
                    System.out.println("登录失败");
                    loop = false;
                    break;
                default:
                    System.out.println("你输入有误请重新输入");
                    break;
            }
        }
    }
}
