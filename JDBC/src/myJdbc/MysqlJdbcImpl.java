package myJdbc;

//模拟实现jdbc接口
public class MysqlJdbcImpl implements JdbcInterface{
    @Override
    public Object getConnection() {
        System.out.println("connection");
        return null;
    }

    @Override
    public void crud() {
        System.out.println("crud");
    }

    @Override
    public void close() {
        System.out.println("close   ");
    }
}
