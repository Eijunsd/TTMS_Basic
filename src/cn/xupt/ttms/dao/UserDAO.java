package cn.xupt.ttms.dao;

import cn.xupt.ttms.idao.IUserDAO;
import cn.xupt.ttms.model.User;
import cn.xupt.ttms.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserDAO implements IUserDAO {
    /**
     * 存储用户信息
     * @return 成功与否boolean
     */
    @Override
    @SuppressWarnings("finally")
    public boolean insert(User user)
    {
        boolean result = false;
        if(user == null)
            return result;

        // 获取Connection
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
            // 关闭连接
            ConnectionManager.close(null, pstmt, con);
            return result;
        }
    }


    /**
     * 修改用户信息
     * @return 成功与否boolean
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
            // 关闭连接
            ConnectionManager.close(null, pstmt, con);
            return result;
        }
    }



    /**
     * 获取所有用户信息(一般用于和界面交互)
     * @return User列表
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
            // 获取所有用户数据
            pstmt = con.prepareStatement("select * from user");
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                info = new User();

                info.setEmpNo(rs.getString("emp_no"));
                info.setEmpPass(rs.getString("emp_pass"));
                info.setType(rs.getInt("type"));
                info.setHeadPath(rs.getString("head_path"));
                // 加入列表
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
     * 根据user_no获取用户信息(一般用于数据内部关联操作)
     * @return 用户
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
            // 获取所有用户数据
            pstmt = con.prepareStatement("select * from user where emp_no=?");
            pstmt.setString(1, employeeNo);
            rs = pstmt.executeQuery();
            if(rs.next())
            {
                // 如果有值的话再实例化
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
}
