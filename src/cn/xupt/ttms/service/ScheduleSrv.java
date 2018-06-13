package cn.xupt.ttms.service;

import cn.xupt.ttms.idao.DAOFactory;
import cn.xupt.ttms.idao.IScheduleDAO;
import cn.xupt.ttms.model.Schedule;

import java.util.ArrayList;

public class ScheduleSrv {
    private IScheduleDAO scheduleDAO = DAOFactory.createScheduleDAO();

    // ��
    public boolean insert(Schedule schedule){
        return scheduleDAO.insert(schedule);
    }

    // ɾ
    public boolean delete(int scheduleId){
        return scheduleDAO.delete(scheduleId);
    }

    // ��
    public boolean update(Schedule schedule){
        return scheduleDAO.update(schedule);
    }

    // �������û�(һ�����ںͽ��潻��)
    public ArrayList<Schedule> findScheduleAll(){
        return scheduleDAO.findScheduleAll();
    }

    // �����û�����(һ�����ںͽ��潻��)
    public ArrayList<Schedule> findScheduleByName(String scheduleName){
        return scheduleDAO.findScheduleByName(scheduleName);
    }

    // �����û�id��(һ�����������ڲ���������)
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
