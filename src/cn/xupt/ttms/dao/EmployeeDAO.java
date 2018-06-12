package cn.xupt.ttms.dao;

import cn.xupt.ttms.idao.IEmployeeDAO;
import cn.xupt.ttms.model.Employee;
import cn.xupt.ttms.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO implements IEmployeeDAO {

    public static final int PAGE_SIZE = 10; // ÿҳ��ʾ����
    private int allCount; // ���ݿ�������
    private int allPageCount; // ��ҳ��
    private int currentPage; // ��ǰҳ

    public int getAllCount()
    {
        return allCount;
    }

    public int getAllPageCount()
    {
        return allPageCount;
    }

    public int getCurrentPage()
    {
        return currentPage;
    }

    /**
     * �洢�û���Ϣ
     *
     * @return �ɹ����boolean
     */
    @SuppressWarnings("finally")
    public boolean insert(Employee employee) {
        boolean result = false;
        if (employee == null) {
            return result;
        }

        // ��ȡConnection
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try {
            String sql = "insert into employee (emp_no, emp_name, emp_tel_num, emp_addr, emp_email) VALUES (?,?,?,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, employee.getEmpNo());
            pstmt.setString(2, employee.getEmpName());
            pstmt.setString(3, employee.getEmpTelNum());
            pstmt.setString(4, employee.getEmpAddr());
            pstmt.setString(5, employee.getEmpEmail());

            pstmt.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // �ر�����
            ConnectionManager.close(null, pstmt, con);
            return result;
        }
    }

    public ArrayList<Employee> findEmployeeAll() {
        ArrayList<Employee> list = new ArrayList<Employee>();
        Employee info = null;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // ��ȡ�����û�����
            pstmt = con.prepareStatement("select * from employee");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                info = new Employee();

                info.setEmpId(rs.getInt("emp_id"));
                info.setEmpName(rs.getString("emp_name"));
                info.setEmpTelNum(rs.getString("emp_tel_num"));
                info.setEmpAddr(rs.getString("emp_addr"));
                info.setEmpEmail(rs.getString("emp_email"));
                info.setEmpNo(rs.getString("emp_no"));

                // �����б�
                list.add(info);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(rs, pstmt, con);
            return list;
        }
    }

    @Override
    public boolean update(Employee employee) {
        boolean result = false;
        if (employee == null) {
            return result;
        }
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try {
            String sql = "update employee set emp_no=?, emp_name=?, emp_tel_num=?,emp_addr=?, emp_email=?  where emp_id-? ;";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, employee.getEmpNo());
            pstmt.setString(2, employee.getEmpName());
            pstmt.setString(3, employee.getEmpTelNum());
            pstmt.setString(4, employee.getEmpAddr());
            pstmt.setString(5, employee.getEmpEmail());
            pstmt.setString(6, String.valueOf(employee.getEmpId()));

            pstmt.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // �ر�����
            ConnectionManager.close(null, pstmt, con);
            return result;
        }
    }

    @Override
    public boolean delete(String employeeNo) {
        boolean result = false;
        if (employeeNo == null || employeeNo.equals("")) {
            return result;
        }
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try {
            // ɾ����ĳ���û�
            String sql = "delete from employee where emp_id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(employeeNo));
            pstmt.executeUpdate();
            ConnectionManager.close(null, pstmt, con);

            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // �ر�����
            ConnectionManager.close(null, pstmt, con);
            return result;
        }

}

    @Override
    public Employee findByEmpId(Employee employee) {

        Employee info = null;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // ��ȡ�����û�����
            pstmt = con.prepareStatement("select * from employee where emp_id=?");
            pstmt.setString(1, String.valueOf(employee.getEmpId()));
            rs = pstmt.executeQuery();
            while (rs.next()) {
                info = new Employee();

                info.setEmpId(rs.getInt("emp_id"));
                info.setEmpName(rs.getString("emp_name"));
                info.setEmpTelNum(rs.getString("emp_tel_num"));
                info.setEmpAddr(rs.getString("emp_addr"));
                info.setEmpEmail(rs.getString("emp_email"));
                info.setEmpNo(rs.getString("emp_no"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(rs, pstmt, con);
            return info;
        }
    }

    @Override
    public List<Employee> findByEmpName(Employee employee) {
        ArrayList<Employee> list = new ArrayList<Employee>();
        Employee info = null;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // ��ȡ�����û�����
            pstmt = con.prepareStatement("select * from employee where emp_name like = ?");
            pstmt.setString(1,employee.getEmpName());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                info = new Employee();

                info.setEmpId(rs.getInt("emp_id"));
                info.setEmpName(rs.getString("emp_name"));
                info.setEmpTelNum(rs.getString("emp_tel_num"));
                info.setEmpAddr(rs.getString("emp_addr"));
                info.setEmpEmail(rs.getString("emp_email"));
                info.setEmpNo(rs.getString("emp_no"));

                list.add(info);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(rs, pstmt, con);
            return list;
        }
    }

    @Override
    public Employee findByEmpId(Integer empId) {
        Employee info = null;

        if (empId == null) {
            return info;
        }

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // ��ȡ�����û�����
            pstmt = con.prepareStatement("select * from employee where emp_no=?");
            pstmt.setString(1, String.valueOf(empId));
            rs = pstmt.executeQuery();
            if(rs.next()) {
                // �����ֵ�Ļ���ʵ����
                info = new Employee();
                info.setEmpId(empId);
                info.setEmpName(rs.getString("emp_name"));
                info.setEmpNo(rs.getString("emp_no"));
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(rs, pstmt, con);
            return info;
        }
    }

    @Override
    public List<Employee> findByEmpName(String empName) {
        if(empName == null || empName.equals(""))
            return null;

        ArrayList<Employee> list = new ArrayList<Employee>();
        Employee info = null;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            // ��ȡ�����û�����:ģ����ѯ
            pstmt = con.prepareStatement("select * from employee where emp_name like ?");
            pstmt.setString(1, "%" + empName + "%");// ƴ��ģ����ѯ��
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                // �����ֵ�Ļ���ʵ����
                info = new Employee();
                info.setEmpId(rs.getInt("emp_id"));
                info.setEmpNo(rs.getString("emp_no"));
                info.setEmpName(rs.getString("emp_name"));
                info.setEmpTelNum(rs.getString("emp_tel_num"));
                info.setEmpAddr(rs.getString("emp_addr"));
                info.setEmpEmail(rs.getString("emp_email"));
                // �����б�
                list.add(info);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            ConnectionManager.close(rs, pstmt, con);
            return list;
        }
    }

    @Override
    public Employee findByEmpNo(String empNo) {
        Employee info = null;
        if(empNo == null) {
            return info;
        }

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // ��ȡ�����û�����
            pstmt = con.prepareStatement("select * from employee where emp_no=?");
            pstmt.setString(1, empNo);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                // �����ֵ�Ļ���ʵ����
                info = new Employee();
                info.setEmpNo(empNo);
                info.setEmpId(rs.getInt("emp_id"));
                info.setEmpName(rs.getString("emp_name"));
                info.setEmpTelNum(rs.getString("emp_tel_num"));
                info.setEmpAddr(rs.getString("emp_addr"));
                info.setEmpEmail(rs.getString("emp_email"));
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(rs, pstmt, con);
            return info;
        }
    }

    @Override
    public List<Employee> findEmployeeByPage(int currentPage, String employeName) {
        currentPage = currentPage;
        List<Employee> list = new ArrayList<Employee>();

        // ��δָ������ĳ�ˣ���Ĭ��ȫ��
        if (null == employeName || employeName.equals("null")) {
            employeName = "";
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // ��ȡ��¼����
            String sql1 = "select count(emp_no) as AllRecord from employee where emp_name like ?";
            conn = ConnectionManager.getInstance().getConnection();
            pstmt = conn.prepareStatement(sql1);
            pstmt.setString(1, "%" + employeName + "%");
            rs = pstmt.executeQuery();
            if(rs.next())
                allCount = rs.getInt("AllRecord");
            rs.close();
            pstmt.close();

            // ������ҳ��
            allPageCount = (allCount + PAGE_SIZE - 1) / PAGE_SIZE;

            // �����ǰҳ��������ҳ������ֵΪ��ҳ��
            if(allPageCount > 0 && currentPage > allPageCount)
                currentPage = allPageCount;

            // ��ȡ��currentPageҳ����
            String sql2 = "select * from employee where emp_name like ? limit ?,?";
            pstmt = conn.prepareStatement(sql2);
            pstmt.setString(1, "%" + employeName + "%");
            pstmt.setInt(2, PAGE_SIZE * (currentPage - 1));
            pstmt.setInt(3, PAGE_SIZE);
            rs = pstmt.executeQuery();
            Employee employee = null;
            while (rs.next()) {
                employee = new Employee();
                employee.setEmpId(rs.getInt("emp_id"));
                employee.setEmpNo(rs.getString("emp_no"));
                employee.setEmpName(rs.getString("emp_name"));
                employee.setEmpTelNum(rs.getString("emp_tel_num"));
                employee.setEmpAddr(rs.getString("emp_addr"));
                employee.setEmpEmail(rs.getString("emp_email"));

                // �����û���Ϣ�����б�
                list.add(employee);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(rs, pstmt, conn);
            return list;
        }
    }
}
