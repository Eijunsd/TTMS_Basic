package cn.xupt.ttms.idao;

import cn.xupt.ttms.model.Play;

import java.util.ArrayList;

public interface IPlayDAO {
    // ��
    public boolean insert(Play play);

    // ɾ
    public boolean delete(int playId);

    // ��
    public boolean update(Play play);

    // ������ӰƬ(һ�����ںͽ��潻��)
    public ArrayList<Play> findPlayAll();

    // ����ӰƬ����(һ�����ںͽ��潻��)
    public ArrayList<Play> findPlayByName(String playName);

    // ����ӰƬid��(һ�����������ڲ���������)
    public Play findPlayById(int playId);

    //��ҳ��ѯ
    public ArrayList<Play> getPlayByPage(int cPage, String play_name);

}
