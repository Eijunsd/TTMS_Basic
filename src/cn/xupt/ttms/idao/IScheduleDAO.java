package cn.xupt.ttms.idao;

import cn.xupt.ttms.model.Schedule;

import java.util.ArrayList;

public interface IScheduleDAO {
    // ��
    public boolean insert(Schedule schedule);

    // ɾ
    public boolean delete(int scheduleId);

    // ��
    public boolean update(Schedule schedule);

    // �������û�(һ�����ںͽ��潻��)
    public ArrayList<Schedule> findScheduleAll();

    // �����û�����(һ�����ںͽ��潻��)
    public ArrayList<Schedule> findScheduleByName(String scheduleName);

    // �����û�id��(һ�����������ڲ���������)
    public Schedule findScheduleById(int scheduleId);
}
