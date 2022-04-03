package service;

import dao.MenuDAO;
import domain.Menu;

import java.sql.SQLException;
import java.util.List;

public class MenuService {

    private MenuDAO menuDAO = new MenuDAO();

    //编写方法返回所有的菜品
    public List<Menu> list() throws SQLException {
        return menuDAO.queryMulti("select * from menu",Menu.class);
    }

    //根据id返回菜品对象
    public Menu geyMenuById(int id) throws SQLException {
        return menuDAO.querySingle("select * from menu where id = ?",Menu.class,id);
    }
}
