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

    // 删
    public boolean delete(int playId){
        return playDAO.delete(playId);
    }

    // 改
    public boolean update(Play play){
        return playDAO.update(play);
    }

    public ArrayList<Play> getPlayAll(){
        return playDAO.findPlayAll();
    }

    // 根据影片名查(一般用于和界面交互)
    public ArrayList<Play> findPlayByName(String playName){
        return playDAO.findPlayByName(playName);
    }

    // 根据影片id查(一般用于数据内部关联操作)
    public Play findPlayById(int playId){
        return playDAO.findPlayById(playId);
    }

    //分页查询
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
