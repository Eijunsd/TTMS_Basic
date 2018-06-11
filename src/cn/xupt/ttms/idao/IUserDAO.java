package cn.xupt.ttms.idao;

import cn.xupt.ttms.model.User;

import java.util.ArrayList;

public interface IUserDAO {
    public boolean insert(User user);

    // 删
    // public boolean delete(int User_);

    // 改
    public boolean update(User user);

    // 查所有用户(一般用于和界面交互)
    public ArrayList<User> findUserAll();

    public User findUserByNo(String employeeNo);
}
