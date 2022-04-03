package myJdbc;

public interface JdbcInterface {
    //连接
    public Object getConnection();
    //crud操纵
    public void crud();
    //关闭连接
    public void close();


}
