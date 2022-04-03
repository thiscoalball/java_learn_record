package myJdbc;

public class TestJdbc {
    public static void main(String[] args) {
        //完成对Jdbc的操作
        MysqlJdbcImpl mysqlJdbc = new MysqlJdbcImpl();
        mysqlJdbc.getConnection();
        mysqlJdbc.crud();
        mysqlJdbc.close();

        //同理Oracle也要实现继承jdbc
        //动态绑定方法规范
    }
}
