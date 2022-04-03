package com.jason.service;

import com.jason.dao.UserDAO;
import com.jason.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    public List<User> findAll(){
        return userDAO.findAll();
    }
    public int deleteById(int id){
        return userDAO.deleteById(id);
    }
}
