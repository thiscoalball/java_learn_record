package service;

import dao.EmployeeDAO;
import domain.Employee;

import java.sql.SQLException;

//完成对Employee表的各种业务操作 通过调用DAO层
public class EmployeeService {
    private EmployeeDAO employeeDAO =  new EmployeeDAO();

    //根据id和密码返回一个Employee对象
    public Employee EmployeeByIdAndPwd(String empId,String pwd) throws SQLException {
        //pwd = md5(?)
        return employeeDAO.querySingle
                ("select * from employee where empId=? and pwd = md5(?)",Employee.class,empId,pwd);
    }

}
