package Dao_.test;

import Dao_.dao.ActorDAO;
import Dao_.domain.Actor;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

//测试ActorDao
public class TestDAO {
    @Test
    public void testActorDAO() throws SQLException {
        ActorDAO actorDAO = new ActorDAO();
        List<Actor> actors = actorDAO.queryMulti("select * from actor where id >= ?", Actor.class, 2);
        for (Actor actor : actors) {
            System.out.println(actor);
        }
        System.out.println("=============================================================================");
        Actor actor = actorDAO.querySingle("select * from actor where id = ?", Actor.class, 3);
        System.out.println(actor);
        System.out.println("===============================================================================");
        Object o = actorDAO.queryScalar("select name from actor where id = ?", 4);
        System.out.println(o);
        System.out.println("===============================================================================");
        int update = actorDAO.update("insert into actor values(null,'Jay' , '男' , '2020-10-10' , '4444')");
        if(update>0){
            System.out.println("执行成功");
        }
    }
}
