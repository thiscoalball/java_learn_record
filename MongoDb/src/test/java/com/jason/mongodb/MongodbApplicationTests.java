package com.jason.mongodb;

import com.jason.mongodb.entity.User;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@SpringBootTest
class MongodbApplicationTests {

    @Autowired
    MongoTemplate mongoTemplate;

    //添加
    @Test
    public void createUser() {
        User user = new User();
        user.setAge(10);
        user.setName("jason");
        user.setEmail("321@qq.com");
        User user1 = mongoTemplate.insert(user);
        System.out.println(user1);
    }

    //查询所有
    @Test
    public void findUser() {
        List<User> userList = mongoTemplate.findAll(User.class);
        System.out.println(userList);
    }

    //根据id查询
    @Test
    public void getById() {
        User user = mongoTemplate.
                findById("621f35f8b5f8071a9865e3a1", User.class);
        System.out.println(user);
    }

    //条件查询
    @Test
    public void findUserList() {
        Query query = new Query(Criteria
                .where("name").is("jason")
                .and("age").is(10));
        List<User> userList = mongoTemplate.find(query, User.class);
        System.out.println(userList);
    }

    //模糊查询
    @Test
    public void findUsersLikeName() {
        String name = "est";
        Query query = getQuery(name);
        List<User> userList = mongoTemplate.find(query, User.class);
        System.out.println(userList);
    }


    @Test
    public void findUsersPage() {
        String name = "est";
        int pageNo = 1;
        int pageSize = 10;
        Query query = getQuery(name);
        //获取条数
        int totalCount = (int) mongoTemplate.count(query, User.class);
        //分页     永恒的公式  (current-1) * size;
        List<User> userList = getPageUserList(pageNo, pageSize, query);
        Map<String, Object> pageMap = new HashMap<>();
        pageMap.put("list", userList);
        pageMap.put("totalCount",totalCount);
        System.out.println(pageMap);
    }

    private List<User> getPageUserList(int pageNo, int pageSize, Query query) {
        List<User> userList = mongoTemplate.
                find(query.skip((pageNo - 1) * pageSize).
                        limit(pageSize), User.class);
        return userList;
    }

    private Query getQuery(String name) {
        Query query = new Query();
        String regex = String.format("%s%s%s", "^.*", name, ".*$");
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        query.addCriteria(Criteria.where("name").regex(pattern));
        return query;
    }

    //修改
    @Test
    public void updateUser() {
        User user = mongoTemplate.findById("621f35f8b5f8071a9865e3a1", User.class);
        //条件 找到这个id的对象
        Query query = new Query(Criteria.where("_id").is(user.getId()));
        //改变的内容
        Update update = new Update();
        update.set("name", "jack");
        update.set("age", 25);
        update.set("email", "493220990@qq.com");
        UpdateResult result = mongoTemplate.upsert(query, update, User.class);
        long count = result.getModifiedCount();
        System.out.println(count);
    }

    //删除操作
    @Test
    public void delete() {
        Query query =
                new Query(Criteria.where("_id").is("621f35f8b5f8071a9865e3a1"));
        DeleteResult result = mongoTemplate.remove(query, User.class);
        long count = result.getDeletedCount();
        System.out.println(count);
    }
}
