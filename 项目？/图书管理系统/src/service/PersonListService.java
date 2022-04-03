package service;

import DAO.PersonListDAO;
import domain.PersonList;

import java.sql.SQLException;

@SuppressWarnings({"all"})

public class PersonListService {
    private PersonListDAO personListDAO = new PersonListDAO();


    //检测用户密码以及确认登录
    public PersonList checkIdAndPwd(String userId, String pwd) throws SQLException {

        //注意这里的 pwd=md5(?)
        String sql = "select * from personList where personId=? and pwd=md5(?)";
        return personListDAO.querySingle(sql, PersonList.class, userId, pwd);
    }

    //借书后 借书量-1函数
    public void borrowBookSizeSub(String peronId) throws SQLException {
        String sql = "UPDATE personlist SET maxBook = maxBook - 1 WHERE  personId = ?";
        int update = personListDAO.update(sql, peronId);
    }

    //还书后 借书量+1函数
    public void returnBookSizeAdd(String peronId) throws SQLException {
        String sql = "UPDATE personlist SET maxBook = maxBook + 1 WHERE  personId = ?";
        int update = personListDAO.update(sql, peronId);
    }

    //检测是否重复
    public boolean idIsRepeat(String personId) throws SQLException {
        String sql = "select personId from personList where personid = ?";
        Object personId2 = personListDAO.queryScalar(sql, personId);
        //如果查到
        if (personId2 != null) {
            return true;
        } else {
            return false;
        }
    }

    //注册函数 默认注册的都是读者账号
    public boolean RegisterAccount(String personId, String pwd, String name) throws SQLException {
        String sql = "insert into personlist values(null,?,?,md5(?),'读者',3,'白名单')";
        int update = personListDAO.update(sql, personId, name, pwd);
        if(update>0){
            return true;
        }else {
            return false;
        }
    }

    //将用户变为黑名单
    public void setBlackList(String personId) throws SQLException {
        int update = personListDAO.update("update personlist set state = '黑名单' where personId = ?", personId);
    }

    //将用户变为黑名单
    public void setWhiteList(String personId) throws SQLException {
        int update = personListDAO.update("update personlist set state = '白名单' where personId = ?", personId);
    }

    //根据id返回personList对象
    public PersonList getPersonListByPersonid(String personId) throws SQLException{
        return personListDAO.querySingle("select * from personList where personid = ?",PersonList.class,personId);
    }


}
