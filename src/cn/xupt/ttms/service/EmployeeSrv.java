package cn.xupt.ttms.service;

import cn.xupt.ttms.idao.DAOFactory;
import cn.xupt.ttms.idao.IEmployeeDAO;
import cn.xupt.ttms.model.Employee;

import java.util.List;

public class EmployeeSrv {


    private IEmployeeDAO employeeDAO = DAOFactory.createEmployeeDAO();

    public boolean insert(Employee employee) {
        return employeeDAO.insert(employee);
    }

    public List<Employee> getEmployeeAll() {
        return employeeDAO.findEmployeeAll();
    }

    public boolean update(Employee employee) {
        return employeeDAO.update(employee);
    }

    public boolean delete(String employeeNo) {
        return employeeDAO.delete(employeeNo);
    }

    public Employee findByEmpId(Integer empId) {
        return employeeDAO.findByEmpId(empId);
    }

    public Employee findByEmpId(Employee employee) {
        return employeeDAO.findByEmpId(employee);
    }

    public List<Employee> findByEmpName(Employee employee) {
        return employeeDAO.findByEmpName(employee);
    }

    public List<Employee> findByEmpName(String empName) {
        return employeeDAO.findByEmpName(empName);
    }

    public Employee findByEmpNo(String empNo) {
        return employeeDAO.findByEmpNo(empNo);
    }

    public List<Employee> findEmployeeByPage(int currentPage, String employeeNo) {
        return employeeDAO.findEmployeeByPage(currentPage, employeeNo);
    }

    public int getAllCount() {
        return employeeDAO.getAllCount();
    }

    public int getAllPageCount() {
        return employeeDAO.getAllPageCount();
    }

    public int getCurrentPage() {
        return employeeDAO.getCurrentPage();
    }

}
