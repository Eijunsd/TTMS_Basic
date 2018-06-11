package cn.xupt.ttms.idao;

import cn.xupt.ttms.model.Schedule;

import java.util.ArrayList;

public interface IScheduleDAO {
    // 增
    public boolean insert(Schedule schedule);

    // 删
    public boolean delete(int scheduleId);

    // 改
    public boolean update(Schedule schedule);

    // 查所有用户(一般用于和界面交互)
    public ArrayList<Schedule> findScheduleAll();

    // 根据用户名查(一般用于和界面交互)
    public ArrayList<Schedule> findScheduleByName(String scheduleName);

    // 根据用户id查(一般用于数据内部关联操作)
    public Schedule findScheduleById(int scheduleId);
}
