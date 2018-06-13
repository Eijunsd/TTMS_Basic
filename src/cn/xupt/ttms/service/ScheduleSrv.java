package cn.xupt.ttms.service;

import cn.xupt.ttms.idao.DAOFactory;
import cn.xupt.ttms.idao.IScheduleDAO;
import cn.xupt.ttms.model.Schedule;

import java.util.ArrayList;

public class ScheduleSrv {
    private IScheduleDAO scheduleDAO = DAOFactory.createScheduleDAO();

    // 增
    public boolean insert(Schedule schedule){
        return scheduleDAO.insert(schedule);
    }

    // 删
    public boolean delete(int scheduleId){
        return scheduleDAO.delete(scheduleId);
    }

    // 改
    public boolean update(Schedule schedule){
        return scheduleDAO.update(schedule);
    }

    // 查所有用户(一般用于和界面交互)
    public ArrayList<Schedule> findScheduleAll(){
        return scheduleDAO.findScheduleAll();
    }

    // 根据用户名查(一般用于和界面交互)
    public ArrayList<Schedule> findScheduleByName(String scheduleName){
        return scheduleDAO.findScheduleByName(scheduleName);
    }

    // 根据用户id查(一般用于数据内部关联操作)
    public Schedule findScheduleById(int scheduleId){
        return scheduleDAO.findScheduleById(scheduleId);
    }

    public ArrayList<Schedule> findScheduleByPage(int cPage, String studio_id) {
        return scheduleDAO.findScheduleByPage(cPage, studio_id);
    }

    public int getAllCount() {
        return scheduleDAO.getAllCount();
    }

    public int getAllPageCount() {
        return scheduleDAO.getAllPageCount();
    }

    public int getCurrentPage() {
        return scheduleDAO.getCurrentPage();
    }
}
