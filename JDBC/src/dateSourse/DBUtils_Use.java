package dateSourse;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class DBUtils_Use {
    @Test
    public void testQueryMany() throws Exception {
        Connection connection = JDBCUtilsByDruid.getConnection();
        //创建QueryRunner
        QueryRunner queryRunner = new QueryRunner();

        String sql = "SELECT * FROM actor where id>=?";

        // new BeanListHandler<>(Actor.class) 就是把对象封装到List中 因为底层要用反射去看你里面有哪些属性才能帮你封装
        // 2 就是给sql语句里的？赋值 可以有多个值 因为是可变参数Object 可以不填只要你的sql里没有 ? 占位符
        List<Actor> list = queryRunner.query(connection, sql, new BeanListHandler<>(Actor.class), 2);
        for (Actor actor : list) {
            System.out.println(actor);
        }
        JDBCUtilsByDruid.close(null, null, connection);
    }

    @Test
    public void testQuerySingle() throws SQLException {
        Connection connection = JDBCUtilsByDruid.getConnection();
        //创建QueryRunner
        QueryRunner queryRunner = new QueryRunner();
        String sql = "SELECT * FROM actor where id=?";
        Actor actor = queryRunner.query(connection, sql, new BeanHandler<>(Actor.class),3);
        System.out.println(actor);
        JDBCUtilsByDruid.close(null, null, connection);
    }

    @Test
    //演示返回单行单列
    public void testScalar() throws SQLException {
        Connection connection = JDBCUtilsByDruid.getConnection();
        //创建QueryRunner
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select name from actor where id =1";
        Object query = queryRunner.query(connection, sql, new ScalarHandler());
        System.out.println(query);


        JDBCUtilsByDruid.close(null, null, connection);

    }

    @Test
    public void testDML() throws SQLException {
        Connection connection = JDBCUtilsByDruid.getConnection();
        //创建QueryRunner
        QueryRunner queryRunner = new QueryRunner();
        //String sql = "update actor set name = ? where id = ?";
        String sql = "insert into actor values(null,'周杰伦','男','1010-10-10',123123)";
        //String sql = "delete from actor where id = 2";
        //String sql = "delete from actor where id = 2";

        int rows = queryRunner.update(connection, sql);
        System.out.println(rows>=1 ?"执行成功":"没有执行成功");


        JDBCUtilsByDruid.close(null, null, connection);
    }
}