package cn.xupt.ttms.idao;

import cn.xupt.ttms.model.Play;

import java.util.ArrayList;

public interface IPlayDAO {
    // 增
    public boolean insert(Play play);

    // 删
    public boolean delete(int playId);

    // 改
    public boolean update(Play play);

    // 查所有影片(一般用于和界面交互)
    public ArrayList<Play> findPlayAll();

    // 根据影片名查(一般用于和界面交互)
    public ArrayList<Play> findPlayByName(String playName);

    // 根据影片id查(一般用于数据内部关联操作)
    public Play findPlayById(int playId);

    //分页查询
    public ArrayList<Play> getPlayByPage(int cPage, String play_name);

}
