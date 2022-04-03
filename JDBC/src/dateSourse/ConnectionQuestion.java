package dateSourse;

import org.junit.jupiter.api.Test;
import utils.JDBCUtils;

import java.sql.Connection;

public class ConnectionQuestion {
    public static void main(String[] args) {

    }
    @Test
    public void testCon() throws Exception{
        long l = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            Connection connection = JDBCUtils.getConnection();
            JDBCUtils.close(null,null,connection);
        }
        long l1 = System.currentTimeMillis();
        System.out.println("耗时"+ (l1-l));//3696
    }
}
