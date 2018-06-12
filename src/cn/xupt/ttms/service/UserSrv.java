package cn.xupt.ttms.service;

import cn.xupt.ttms.idao.DAOFactory;
import cn.xupt.ttms.idao.IUserDAO;
import cn.xupt.ttms.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserSrv {
    private IUserDAO userDAO = DAOFactory.createUserDAO();

    public boolean insert(User user){
        return userDAO.insert(user);
    }

    // 删
    public boolean delete(int UserId) {
        return userDAO.delete(UserId);
    }

    // 改
    public boolean update(User user){
        return userDAO.update(user);
    }

    // 查所有用户(一般用于和界面交互)
    public ArrayList<User> findUserAll(){
        return userDAO.findUserAll();
    }

    public User findUserByNo(String employeeNo){
        return userDAO.findUserByNo(employeeNo);
    }

    public int getAllCount() {
        return userDAO.getAllCount();
    }

    public int getAllPageCount() {
        return userDAO.getAllPageCount();
    }

    public int getCurrentPage() {
        return userDAO.getCurrentPage();
    }

    public List<User> findUserByPage(int currentPage, String employeeNo) {
        return userDAO.findUserByPage(currentPage, employeeNo);
    }
}
