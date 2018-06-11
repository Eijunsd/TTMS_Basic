package cn.xupt.ttms.service;

import cn.xupt.ttms.idao.DAOFactory;
import cn.xupt.ttms.idao.IStudioDAO;
import cn.xupt.ttms.model.Studio;

import java.util.ArrayList;

public class StudioSrv {
    private IStudioDAO studioDAO = DAOFactory.createStudioDAO();

    // 增
    public boolean insert(Studio studio){
        return studioDAO.insert(studio);
    }

    // 删
    public boolean delete(int studioId){
        return studioDAO.delete(studioId);
    }

    // 改
    public boolean update(Studio studio){
        return studioDAO.update(studio);
    }

    // 查所有用户(一般用于和界面交互)
    public ArrayList<Studio> findStudioAll(){
        return studioDAO.findStudioAll();
    }

    // 根据用户名查(一般用于和界面交互)
    public ArrayList<Studio> findStudioByName(String studioName){
        return studioDAO.findStudioByName(studioName);
    }

    // 根据用户id查(一般用于数据内部关联操作)
    public Studio findStudioById(int studioId){
        return studioDAO.findStudioById(studioId);
    }


}
