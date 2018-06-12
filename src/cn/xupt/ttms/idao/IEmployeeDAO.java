package cn.xupt.ttms.idao;

import cn.xupt.ttms.model.Employee;

import java.util.List;

public interface IEmployeeDAO {
    boolean insert(Employee employee);

    List<Employee> findEmployeeAll();

    boolean update(Employee employee);

    boolean delete(String employeeNo);

    Employee findByEmpId(Employee employee);

    Employee findByEmpId(Integer empId);

    List<Employee> findByEmpName(Employee employee);

    List<Employee> findByEmpName(String empName);

    Employee findByEmpNo(String empNo);

    List<Employee> findEmployeeByPage(int currentPage, String employeName);

    public int getAllCount();

    public int getAllPageCount();

    public int getCurrentPage();


}
