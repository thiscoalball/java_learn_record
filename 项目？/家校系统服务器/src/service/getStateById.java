package service;

import domain.User;

import java.sql.SQLException;

public interface getStateById{
    User getStateById(String name,String personId) throws SQLException;
}
