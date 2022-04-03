package Interface;

public class Interface02 {
    public static void main(String[] args) {

        MysqlDB mysqlDB = new MysqlDB();
        t(mysqlDB);
        OracleDB oracleDB = new OracleDB();
        t(oracleDB);
    }

    public static void t(DBInterface db)//这里直接用static让上面调用了 懒得再创建类了
    {
        db.connect();
        db.close();
    }
}
