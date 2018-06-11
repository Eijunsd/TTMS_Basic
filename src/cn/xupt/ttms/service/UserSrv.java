package cn.xupt.ttms.service;

import cn.xupt.ttms.idao.DAOFactory;
import cn.xupt.ttms.idao.IUserDAO;
import cn.xupt.ttms.model.User;

import java.util.ArrayList;

public class UserSrv {
    private IUserDAO userDAO = DAOFactory.createUserDAO();

    public boolean insert(User user){
        return userDAO.insert(user);
    }

    // ɾ
    // public boolean delete(int User_);

    // ��
    public boolean update(User user){
        return userDAO.update(user);
    }

    // �������û�(һ�����ںͽ��潻��)
    public ArrayList<User> findUserAll(){
        return userDAO.findUserAll();
    }

    public User findUserByNo(String employeeNo){
        return userDAO.findUserByNo(employeeNo);
    }
}
