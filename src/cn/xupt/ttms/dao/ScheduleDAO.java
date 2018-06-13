package cn.xupt.ttms.dao;

import cn.xupt.ttms.idao.IScheduleDAO;
import cn.xupt.ttms.model.Schedule;
import cn.xupt.ttms.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ScheduleDAO implements IScheduleDAO {

    public static final int PAGE_SIZE = 5; // 每页显示条数
    private int allCount; // 数据库中条数
    private int allPageCount; // 总页数
    private int currentPage; // 当前页

    public int getAllCount() {
        return allCount;
    }

    public int getAllPageCount() {
        return allPageCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }


    //more默认全部查询
    @SuppressWarnings("finally")
    public ArrayList<Schedule> findScheduleByPage(int cPage, String studio_id) {
        currentPage = cPage;
        ArrayList<Schedule> list = new ArrayList<Schedule>();

        // 若未指定查找某人，则默认全查
        if (null == studio_id || studio_id.equals("null")) {
            studio_id = "";
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // 获取记录总数 as AllRecord重命名

            String sql1 = "select count(sched_id) as AllRecord from schedule where studio_id like ?";
            conn = ConnectionManager.getInstance().getConnection();
            pstmt = conn.prepareStatement(sql1);
            pstmt.setString(1, "%" + studio_id + "%");
            rs = pstmt.executeQuery();
            // allCount; 数据库中条数
            if (rs.next())
                allCount = rs.getInt("AllRecord");
            rs.close();
            pstmt.close();

            // 记算总页数 PAGE_SIZE = 5; // 每页显示条数
            allPageCount = (allCount + PAGE_SIZE - 1) / PAGE_SIZE;

            // 如果当前页数大于总页数，则赋值为总页数
            if (allPageCount > 0 && currentPage > allPageCount)
                currentPage = allPageCount;

            // 获取第currentPage页数据
            String sql2 = "select * from schedule where studio_id like ? limit ?,?";
            // select * from tablename limit 开始位置,每页行数
            pstmt = conn.prepareStatement(sql2);
            pstmt.setString(1, "%" + studio_id + "%");
            pstmt.setInt(2, PAGE_SIZE * (currentPage - 1));
            pstmt.setInt(3, PAGE_SIZE);
            rs = pstmt.executeQuery();
            Schedule schedule = null;
            while (rs.next()) {
                schedule = new Schedule();
                schedule.setSchedId(rs.getInt("sched_id"));
                schedule.setStudioId(rs.getInt("studio_id"));
                schedule.setPlayId(rs.getInt("play_id"));
                schedule.setSchedTicketPrice(rs.getInt("sched_ticket_price"));
                schedule.setSchedTime(rs.getString("sched_time"));

                // 将该用户信息插入列表
                list.add(schedule);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(rs, pstmt, conn);
            return list;
        }
    }

    @Override
    public boolean insert(Schedule schedule) {
        boolean result = false;
        if (schedule == null)
            return result;

        // 获取Connection
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try {
            String sql = "insert into schedule(studio_id, play_id, sched_time, sched_ticket_price) values(?,?,?,?)";
            pstmt = con.prepareStatement(sql);
//            pstmt.setInt(1, schedule.getSchedId());
            pstmt.setInt(1, schedule.getStudioId());
            pstmt.setInt(2, schedule.getPlayId());
            pstmt.setString(3, schedule.getSchedTime());
            pstmt.setFloat(4, schedule.getSchedTicketPrice());


            pstmt.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接
            ConnectionManager.close(null, pstmt, con);
            return result;
        }

    }

    @Override
    public boolean delete(int scheduleId) {
        boolean result = false;
        if (scheduleId == 0)
            return result;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try {
            // 删除某个用户
            String sql = "delete from schedule where sched_id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, scheduleId);
            pstmt.executeUpdate();
            ConnectionManager.close(null, pstmt, con);

            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接
            ConnectionManager.close(null, pstmt, con);
            return result;
        }

    }

    @Override
    public boolean update(Schedule schedule) {
        boolean result = false;
        if (schedule == null) {
            return result;
        }
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try {
            String sql = "update schedule set studio_id=?,play_id=?,sched_time=?,sched_ticket_price=? where sched_id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, schedule.getStudioId());
            pstmt.setInt(2, schedule.getPlayId());
            pstmt.setString(3, schedule.getSchedTime());
            pstmt.setFloat(4, schedule.getSchedTicketPrice());
            pstmt.setInt(5, schedule.getSchedId());

            pstmt.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接
            ConnectionManager.close(null, pstmt, con);
            return result;
        }
    }

    @Override
    public ArrayList<Schedule> findScheduleAll() {
        ArrayList<Schedule> list = new ArrayList<Schedule>();
        Schedule info = null;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // 获取所有用户数据
            pstmt = con.prepareStatement("select * from schedule");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                //如果存在，再进行初始化
                info = new Schedule();
                info.setSchedId(rs.getInt("sched_id"));
                info.setStudioId(rs.getInt("studio_id"));
                info.setPlayId(rs.getInt("play_id"));
                info.setSchedTime(rs.getString("sched_time"));
                info.setSchedTicketPrice(rs.getInt("sched_ticket_price"));

                // 加入列表
                list.add(info);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(rs, pstmt, con);
            return list;
        }
    }

    @Override
    public ArrayList<Schedule> findScheduleByName(String scheduleName) {
        if (scheduleName == null || scheduleName.equals(""))
            return null;

        ArrayList<Schedule> list = new ArrayList<Schedule>();
        Schedule info = null;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // 获取所有用户数据:模糊查询
            pstmt = con.prepareStatement("select * from schedule where studio_id like ?");
            pstmt.setString(1, "%" + scheduleName + "%");// 拼接模糊查询串
            rs = pstmt.executeQuery();
            while (rs.next()) {
                // 如果有值的话再实例化
                info = new Schedule();

                info.setSchedId(rs.getInt("sched_id"));
                info.setStudioId(rs.getInt("studio_id"));
                info.setPlayId(rs.getInt("play_id"));
                info.setSchedTime(rs.getString("sched_time"));
                info.setSchedTicketPrice(rs.getInt("sched_ticket_price"));
                // 加入列表
                list.add(info);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(rs, pstmt, con);
            return list;
        }
    }

    @Override
    public Schedule findScheduleById(int scheduleId) {
        Schedule info = null;
        if (scheduleId == 0)
            return info;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // 获取所有用户数据
            pstmt = con.prepareStatement("select * from schedule where sched_id=?");
            pstmt.setInt(1, scheduleId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                // 如果有值的话再实例化
                info = new Schedule();
                info.setSchedId(scheduleId);
                info.setStudioId(rs.getInt("studio_id"));
                info.setPlayId(rs.getInt("play_id"));
                info.setSchedTime(rs.getString("sched_time"));
                info.setSchedTicketPrice(rs.getInt("sched_ticket_price"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(rs, pstmt, con);
            return info;
        }
    }
}
