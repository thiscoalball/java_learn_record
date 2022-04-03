package service;

import DAO.RecordListDAO;
import domain.RecordList;

import java.sql.SQLException;
import java.util.List;

public class RecordListService {
    private RecordListDAO recordListDAO = new RecordListDAO();

    //借书时往该表插入该数据
    public boolean insertRecord(String bookName, String personName) throws SQLException {
        int update = recordListDAO.update("insert into recordList values(null,?,?,now(),null,'未归还')", personName, bookName);
        if (update > 0) {
            return true;
        } else {
            return false;
        }
    }

    //还书时更新该表对应列的状态
    public boolean returnRecord(String bookName, String personName) throws SQLException {
        int update = recordListDAO.update("update recordList set state = '已归还',returnDate = now()" +
                " where bookName = ? and personName = ?", bookName, personName);
        if (update > 0) {
            return true;
        } else {
            return false;
        }
    }

    //记录过多 清除记录
    public boolean clear() throws SQLException {
        int flag = recordListDAO.update("TRUNCATE TABLE recordList");
        if (flag > 0) {
            return true;
        } else {
            return false;
        }
    }

    //打印总记录表
    public void getRecordList() throws SQLException {
        List<RecordList> recordLists = recordListDAO.queryMulti("select * from recordList", RecordList.class);
        System.out.println();
        System.out.println("============================总记录表如下========================");
        System.out.println("\t姓名\t\t书名\t\t\t借阅日期\t\t\t\t\t归还日期\t\t\t\t\t状态");
        for (RecordList record : recordLists) {
            System.out.println(record);
        }
        System.out.println();
    }

    //打印某人的借阅记录
    public void getOneRecord(String personName,String bookName) throws  SQLException{
        List<RecordList> recordLists = recordListDAO.queryMulti("select * from recordList where personName = ? or bookName = ?", RecordList.class,personName,bookName);
        System.out.println();
        System.out.println("===========================记录如下========================");
        System.out.println("\t姓名\t\t书名\t\t\t借阅日期\t\t\t\t\t归还日期\t\t\t\t\t状态");
        for (RecordList record : recordLists) {
            System.out.println(record);
        }
        System.out.println();
    }
}
