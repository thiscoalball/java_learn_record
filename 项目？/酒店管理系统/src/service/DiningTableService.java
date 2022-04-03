package service;

import dao.DiningTableDAO;
import domain.DiningTable;

import java.sql.SQLException;
import java.util.List;

public class DiningTableService {

    private DiningTableDAO diningTableDAO = new DiningTableDAO();

    //返回所有餐桌的消息
    public List<DiningTable> list() throws SQLException {
        return diningTableDAO.queryMulti("select id,state from diningTable", DiningTable.class);
    }

    //根据id查询对应的餐桌对象是否存在    null表示不存在
    public DiningTable getDiningTableByID(int tableId) throws SQLException {
        return diningTableDAO.querySingle("select * from diningTable where id=?", DiningTable.class, tableId);
    }

    //如果餐桌可以预定调用方法对其状态进行更新
    public boolean orderDiningTable(int tableId, String orderName, String orderTel) throws SQLException {
        int update = diningTableDAO.update("update diningTable set state='已预定',orderName=?,orderTel = ? where id = ?", orderName, orderTel,tableId);
        return update>0;      //如果大于0证明更新成功了
    }

    //根据id改变餐桌状态的方法
    public boolean updateDiningTableState(int id,String state) throws SQLException {
        int update = diningTableDAO.update("update diningtable set state = ? where id = ?", state, id);
        return update > 0;
    }

    //将餐桌设置为空闲状态
    public boolean updateDiningTableToFree(int id) throws SQLException {
        int update = diningTableDAO.update("update diningtable set state = '空',orderName ='', orderTel = ''  where id = ?", id);
        return update > 0;
    }

}
