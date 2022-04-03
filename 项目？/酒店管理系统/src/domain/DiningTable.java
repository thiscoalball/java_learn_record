package domain;
/**
 * 这时一个javaBean映射dingTable表
 *       id INT PRIMARY KEY AUTO_INCREMENT, #自增, 表示餐桌编号
 * 	     state VARCHAR(20) NOT NULL DEFAULT '',#餐桌的状态
 * 	     orderName VARCHAR(50) NOT NULL DEFAULT '',#预订人的名字
 * 	     orderTel VARCHAR(20) NOT NULL DEFAULT ''
 * */
public class DiningTable {
    private Integer id;
    private String state;
    private String orderName;
    private String orderTable;

    public DiningTable() {
    }

    public DiningTable(Integer id, String state, String orderName, String orderTable) {
        this.id = id;
        this.state = state;
        this.orderName = orderName;
        this.orderTable = orderTable;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderTable() {
        return orderTable;
    }

    public void setOrderTable(String orderTable) {
        this.orderTable = orderTable;
    }

    @Override
    public String toString() {
        return id + "\t\t\t" + state;
    }
}
