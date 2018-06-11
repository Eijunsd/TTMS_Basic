package cn.xupt.ttms.idao;

import cn.xupt.ttms.model.Studio;

import java.util.ArrayList;

public interface IStudioDAO {
    // ��
    public boolean insert(Studio studio);

    // ɾ
    public boolean delete(int studioId);

    // ��
    public boolean update(Studio studio);

    // �������û�(һ�����ںͽ��潻��)
    public ArrayList<Studio> findStudioAll();

    // �����û�����(һ�����ںͽ��潻��)
    public ArrayList<Studio> findStudioByName(String studioName);

    // �����û�id��(һ�����������ڲ���������)
    public Studio findStudioById(int studioId);

    public ArrayList<Studio> findStudioByPage(int cPage, String seat_row);
}
