package cn.xupt.ttms.service;

import cn.xupt.ttms.idao.DAOFactory;
import cn.xupt.ttms.idao.IPlayDAO;
import cn.xupt.ttms.model.Play;

import java.util.ArrayList;

public class PlaySrv {
    private IPlayDAO playDAO = DAOFactory.createPlayDAO();

    public boolean insert(Play play){
        return playDAO.insert(play);
    }

    // ɾ
    public boolean delete(int playId){
        return playDAO.delete(playId);
    }

    // ��
    public boolean update(Play play){
        return playDAO.update(play);
    }

    public ArrayList<Play> getPlayAll(){
        return playDAO.findPlayAll();
    }

    // ����ӰƬ����(һ�����ںͽ��潻��)
    public ArrayList<Play> findPlayByName(String playName){
        return playDAO.findPlayByName(playName);
    }

    // ����ӰƬid��(һ�����������ڲ���������)
    public Play findPlayById(int playId){
        return playDAO.findPlayById(playId);
    }

    //��ҳ��ѯ
    public ArrayList<Play> findPlayByPage(int cPage, String play_id){
        return playDAO.getPlayByPage(cPage, play_id);
    }

    public int getAllCount(){
        return playDAO.getAllCount();
    }

    public int getAllPageCount(){
        return playDAO.getAllPageCount();
    }

    public int getCurrentPage(){
        return playDAO.getCurrentPage();
    }
}
