package cn.xupt.ttms.dao;

import cn.xupt.ttms.idao.IUserDAO;
import cn.xupt.ttms.model.User;
import cn.xupt.ttms.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO {

    public static final int PAGE_SIZE = 10; // ÿҳ��ʾ����
    private static int allCount; // ���ݿ�������
    private static int allPageCount; // ��ҳ��
    private static int currentPage; // ��ǰҳ

    public int getAllCount() {
        return allCount;
    }

    public int getAllPageCount() {
        return allPageCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }



    /**
     * �洢�û���Ϣ
     * @return �ɹ����boolean
     */
    @Override
    @SuppressWarnings("finally")
    public boolean insert(User user)
    {
        boolean result = false;
        if(user == null)
            return result;

        // ��ȡConnection
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try
        {
            String sql = "insert into user(emp_no,emp_pass,type,head_path) values(?,?,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, user.getEmpNo());
            pstmt.setString(2, user.getEmpPass());
            pstmt.setInt(3, user.getType());
            pstmt.setString(4, user.getHeadPath());
            pstmt.executeUpdate();
            result = true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            // �ر�����
            ConnectionManager.close(null, pstmt, con);
            return result;
        }
    }

    @Override
    public boolean delete(int User) {
        return false;
    }


    /**
     * �޸��û���Ϣ
     * @return �ɹ����boolean
     */
    @Override
    @SuppressWarnings("finally")
    public boolean update(User user)
    {
        boolean result = false;
        if(user == null)
            return result;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try
        {
            String sql = "update user set emp_pass=?,type=?,head_path=? where emp_no=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, user.getEmpPass());
            pstmt.setInt(2, user.getType());
            pstmt.setString(3, user.getHeadPath());
            pstmt.setString(4, user.getEmpNo());

            pstmt.executeUpdate();
            result = true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            // �ر�����
            ConnectionManager.close(null, pstmt, con);
            return result;
        }
    }



    /**
     * ��ȡ�����û���Ϣ(һ�����ںͽ��潻��)
     * @return User�б�
     */
    @Override
    @SuppressWarnings("finally")
    public ArrayList<User> findUserAll()
    {
        ArrayList<User> list = new ArrayList<User>();
        User info = null;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            // ��ȡ�����û�����
            pstmt = con.prepareStatement("select * from user");
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                info = new User();

                info.setEmpNo(rs.getString("emp_no"));
                info.setEmpPass(rs.getString("emp_pass"));
                info.setType(rs.getInt("type"));
                info.setHeadPath(rs.getString("head_path"));
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


    /**
     * ����user_no��ȡ�û���Ϣ(һ�����������ڲ���������)
     * @return �û�
     */
    @Override
    @SuppressWarnings("finally")
    public User findUserByNo(String employeeNo)
    {
        User info = null;
        if(employeeNo == "")
            return info;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            // ��ȡ�����û�����
            pstmt = con.prepareStatement("select * from user where emp_no=?");
            pstmt.setString(1, employeeNo);
            rs = pstmt.executeQuery();
            if(rs.next())
            {
                // �����ֵ�Ļ���ʵ����
                info = new User();
                info.setEmpNo(employeeNo);
                info.setEmpPass(rs.getString("emp_pass"));
                info.setType(rs.getInt("type"));
                info.setHeadPath(rs.getString("head_path"));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            ConnectionManager.close(rs, pstmt, con);
            return info;
        }
    }

    @Override
    public List<User> findUserByPage(int currentPage, String employeeNo) {
        currentPage = currentPage;
        ArrayList<User> list = new ArrayList<User>();

        // ��δָ������ĳ�ˣ���Ĭ��ȫ��
        if (null == employeeNo || employeeNo.equals("null")) {
            employeeNo = "";
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // ��ȡ��¼����
            String sql1 = "select count(emp_no) as AllRecord from user where emp_no like ?";
            conn = ConnectionManager.getInstance().getConnection();
            pstmt = conn.prepareStatement(sql1);
            pstmt.setString(1, "%" + employeeNo + "%");
            System.out.println("-->" + pstmt);
            rs = pstmt.executeQuery();
            if (rs.next())
                allCount = rs.getInt("AllRecord");
            rs.close();
            pstmt.close();

            System.out.println(allCount);
            // ������ҳ��
            allPageCount = (allCount + PAGE_SIZE - 1) / PAGE_SIZE;

            // �����ǰҳ��������ҳ������ֵΪ��ҳ��
            if (allPageCount > 0 && currentPage > allPageCount)
                currentPage = allPageCount;

            // ��ȡ��currentPageҳ����
            String sql2 = "select * from user where emp_no like ? limit ?,?";
            pstmt = conn.prepareStatement(sql2);
            pstmt.setString(1, "%" + employeeNo + "%");
            pstmt.setInt(2, PAGE_SIZE * (currentPage - 1));
            pstmt.setInt(3, PAGE_SIZE);
            System.out.println(pstmt);
            rs = pstmt.executeQuery();
            User user = null;
            while (rs.next()) {
                user = new User();
                user.setEmpNo(rs.getString("emp_no"));
                user.setEmpPass(rs.getString("emp_pass"));
                user.setType(rs.getInt("type"));
                user.setHeadPath(rs.getString("head_path"));

                // �����û���Ϣ�����б�
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(rs, pstmt, conn);
            return list;
        }
    }

}
