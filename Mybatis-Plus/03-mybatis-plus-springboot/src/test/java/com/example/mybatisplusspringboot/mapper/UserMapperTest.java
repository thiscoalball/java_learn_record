package com.example.mybatisplusspringboot.mapper;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplusspringboot.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectList(){
        List<User> users = userMapper.selectList(null);
        users.forEach(user -> System.out.println(user));
    }
    @Test
    public void selectList2(){
        //测试自定义xml的sql语句
        List<User> all = userMapper.findAll();
        all.forEach(user -> System.out.println(user));
    }

    @Test
    public void insert1(){
        User user = new User();
        user.setAge(20);
        user.setEmail("123@qq.com");
        user.setUserName("liubei");
        user.setName("刘备");
        user.setPassword("123123");
        userMapper.insert(user);
    }

    @Test
    public void update1(){
        User user = new User();
        user.setId(1L);
        user.setAge(999);
        user.setPassword("6666666");
        userMapper.updateById(user);
    }

    @Test
    public void update2(){
        User user = new User();
        QueryWrapper<User> wrapper =  new QueryWrapper<>();
        wrapper.eq("user_name","wangwu");
        user.setAge(999);
        user.setPassword("6666666");
        userMapper.update(user,wrapper);
    }

    @Test
    public void update3(){
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.set("age",1111).set("password",666).eq("user_name","wangwu");
        userMapper.update(null,wrapper);
    }

    @Test
    public void delete1(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("user_name","zhangsan");
        map.put("password","6666666");
        //根据map删除数据多条件之间是一个and的关系
        System.out.println(userMapper.deleteByMap(map));
    }

    @Test
    public void delete2(){
        ////方法1
        //QueryWrapper<User> wrapper = new QueryWrapper<>();
        //wrapper.eq("user_name","wangwu").eq("password","666");
        //方法2 我们更加推荐使用方法2，因为不用直接写字段名
        User user = new User();
        user.setUserName("wangwu");
        user.setPassword("666");
        QueryWrapper<User> wrapper = new QueryWrapper<>(user);
        userMapper.delete(wrapper);
    }

    @Test
    public void delete3(){
        //根据id批量删除
        System.out.println(userMapper.deleteBatchIds(Arrays.asList(7L, 6L)));
    }

    @Test
    public void select1(){
        System.out.println(userMapper.selectById(2L));
    }

    @Test
    public void select2(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(2L, 5l));
        users.forEach(user -> System.out.println(user));
    }

    @Test
    public void select3(){
        User user = new User();
        user.setUserName("lisi");
        QueryWrapper<User> wrapper = new QueryWrapper<>(user);
        System.out.println(userMapper.selectOne(wrapper));
    }

    @Test
    public void select4(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //大于20岁的
        wrapper.gt("age",20);
        System.out.println(userMapper.selectCount(wrapper));
    }


    @Test
    public void select5(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //大于20岁的,模糊查询li
        wrapper.gt("age",20).like("user_name","li");
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(user -> System.out.println(user));
    }

    @Test
    public void select6(){
        Page<User> userPage = new Page<>(1,2);
        Page<User> iPage = userMapper.selectPage(userPage,null);
        System.out.println("总条数:" +iPage.getTotal());
        System.out.println("总页数:"+ iPage.getPages());
        System.out.println("当前页数:"+iPage.getCurrent());
        List<User> users = iPage.getRecords();
        users.forEach(user -> System.out.println(user));
    }

}