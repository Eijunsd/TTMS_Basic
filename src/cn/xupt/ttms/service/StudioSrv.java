package cn.xupt.ttms.service;

import cn.xupt.ttms.idao.DAOFactory;
import cn.xupt.ttms.idao.IStudioDAO;
import cn.xupt.ttms.model.Studio;

import java.util.ArrayList;

public class StudioSrv {
    private IStudioDAO studioDAO = DAOFactory.createStudioDAO();

    // ��
    public boolean insert(Studio studio){
        return studioDAO.insert(studio);
    }

    // ɾ
    public boolean delete(int studioId){
        return studioDAO.delete(studioId);
    }

    // ��
    public boolean update(Studio studio){
        return studioDAO.update(studio);
    }

    // �������û�(һ�����ںͽ��潻��)
    public ArrayList<Studio> findStudioAll(){
        return studioDAO.findStudioAll();
    }

    // �����û�����(һ�����ںͽ��潻��)
    public ArrayList<Studio> findStudioByName(String studioName){
        return studioDAO.findStudioByName(studioName);
    }

    // �����û�id��(һ�����������ڲ���������)
    public Studio findStudioById(int studioId){
        return studioDAO.findStudioById(studioId);
    }


}
