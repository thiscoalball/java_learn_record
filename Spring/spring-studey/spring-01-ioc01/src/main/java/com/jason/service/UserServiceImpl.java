package com.jason.service;

import com.jason.dao.UserDAO;
import com.jason.dao.UserDAOMysqlImpl;
import com.jason.dao.UserDaoImpl;

public class UserServiceImpl implements UserService{
    //我们发现增加类的时候需要改变原有的代码 这样不好
    //假如以后还要使用其他的数据库 那就要改很多的源代码 违反开闭原则
    private UserDAO userDAO = new UserDaoImpl();
    //private UserDAO userDAO = new UserDAOMysqlImpl();

    //利用set进行动态实现值的注入 然后在用户层修改
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void getUser() {
        userDAO.getUser();
    }
}
