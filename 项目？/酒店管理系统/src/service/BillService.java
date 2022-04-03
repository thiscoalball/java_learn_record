package service;

import dao.BillDAO;
import dao.MultiTableDAO;
import domain.Bill;
import domain.MultiTableBean;


import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class BillService {

    private BillDAO billDAO = new BillDAO();
    private MultiTableDAO multiTableDAO = new MultiTableDAO();

    //为了调到菜品价格需要定义一个MenuService的属性
    private MenuService menuService = new MenuService();
    private DiningTableService diningTableService = new DiningTableService();
    //编写点餐方法
    public boolean orderMenu(int menuId, int nums, int diningTableId) throws SQLException {
        //生成一个账单号  UUID
        String billID = UUID.randomUUID().toString();

        //将账单生成到bill表
        int update = billDAO.update("insert into bill values(null, ?, ?, ?, ?, ?, now(), '未结账') ",
                billID, menuId, nums, (menuService.geyMenuById(menuId).getPrice() * nums), diningTableId);

        if (update <= 0) {
            return false;
        }

        //因为要改变餐桌状态就需要用到diningService  下面这个方法如果修改成功返回的就是bool值 所以我们直接return
        return diningTableService.updateDiningTableState(diningTableId, "就餐中");
    }

    public List<Bill> list() throws SQLException {
        return billDAO.queryMulti("select * from bill", Bill.class);
    }
    public List<MultiTableBean> list2() throws SQLException {
        return multiTableDAO.queryMulti("SELECT bill.*,NAME,price FROM menu,bill WHERE bill.menuId = menu.id", MultiTableBean.class);
    }

    //查看某个餐桌是否有未结账的账单
    public boolean hasPayBillByDiningTableId(int diningTableId) throws SQLException {
        Bill bill = billDAO.querySingle("SELECT * FROM bill WHERE diningTableId=? AND state = '未结账' LIMIT 0,1", Bill.class, diningTableId);
        return bill != null;  //这样有意思哦
    }

    //如果餐桌存在并且有未结账的账单 进行结账
    public boolean payBill(String state, int diningTableId) throws SQLException {
        //这里有两个update语句了 使用事务的话更好 需要使用ThreadLocal , 以后学的框架中比如mybatis提供了事务支持
        //1 修改bill表
        int update = billDAO.update("update bill set state = ? where diningTableId = ? and state = '未结账'", state, diningTableId);
        if (update <= 0) {
            return false;
        }
        //2 修改diningTable表   这里我们就不要在这里操作 要去调用diningService的方法完成
        if (!diningTableService.updateDiningTableToFree(diningTableId)) {
            return false;
        }
        return true;
    }
}