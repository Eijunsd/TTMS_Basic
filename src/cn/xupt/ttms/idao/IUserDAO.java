package cn.xupt.ttms.idao;

import cn.xupt.ttms.model.User;

import java.util.ArrayList;
import java.util.List;

public interface IUserDAO {
    public boolean insert(User user);

    // ɾ
     public boolean delete(int User_);

    // ��
    public boolean update(User user);

    // �������û�(һ�����ںͽ��潻��)
    public ArrayList<User> findUserAll();

    public User findUserByNo(String employeeNo);

    public List<User> searchByPage(int userId);
}
