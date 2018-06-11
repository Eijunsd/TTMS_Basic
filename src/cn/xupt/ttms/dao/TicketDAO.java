package cn.xupt.ttms.dao;

import cn.xupt.ttms.idao.DAOFactory;
import cn.xupt.ttms.idao.ITicketDAO;
import cn.xupt.ttms.model.Schedule;
import cn.xupt.ttms.model.Seat;
import cn.xupt.ttms.model.Ticket;
import cn.xupt.ttms.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class TicketDAO implements ITicketDAO {
    @Override
    public ArrayList<Ticket> findSeatByPage(int cPage, String studio_id) {
        return null;
    }

    @Override
    public int update(Ticket ticket) {
        return 0;
    }


    public boolean insert(Ticket ticket) {
        // TODO Auto-generated method stub
        boolean result = false;
        if(ticket == null)
            return result;
        // 获取Connection
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try
        {
            String sql = "insert into ticket(ticket_id, seat_id, sched_id, ticket_price, ticket_status, ticket_locked_time) values(?,?,?,?,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, ticket.getTicketId());
            pstmt.setInt(2, ticket.getSeatId());
            pstmt.setInt(3, ticket.getSchedId());
            pstmt.setFloat(4, ticket.getTicketPrice());
            pstmt.setInt(5, ticket.getTicketStatus());
            //  pstmt.setTimestamp(6, new java.sql.Timestamp(ticket.getLocked_time().getTime()));
            pstmt.setDate(6,null);
            pstmt.executeUpdate();
            result = true;

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            // 关闭连接
            ConnectionManager.close(null, pstmt, con);
            return result;
        }
    }


    @Override
    public void searchByscheduleId(int scheduleid) {

    }

    /**
     *
     * @param scheduleid
     */
    @Override
    public void addByScheduleId(int scheduleid) {
        // TODO Auto-generated method stub
        Schedule schedule = new ScheduleDAO().findScheduleById(scheduleid);
        System.out.println("TicketDao.java" + schedule.getSchedId());
        float schedule_price = (float) schedule.getSchedTicketPrice();
        int studioid = schedule.getStudioId();
        System.out.println("TicketDao.java" + studioid);

        int studiorow = new DAOFactory().createStudioDAO().findStudioById(studioid).getStudioRowCount();
        int studiocol = new DAOFactory().createStudioDAO().findStudioById(studioid).getStudioColCount();

        for (int i = 1; i <= studiorow; i++) {
            for (int j = 1; j <= studiocol; j++) {
                Ticket ticket = new Ticket();
                ticket.setSchedId(scheduleid);
                //遍历所有演出厅座位的位置，得出seat_id
                Seat seat = new DAOFactory().createSeatDAO().selectByMany(studioid, i, j);
                int seat_id = seat.getSeatId();
                int seat_status = seat.getSeatStatus();
                ticket.setSchedId(scheduleid);
                ticket.setSeatId(seat_id);
                ticket.setTicketPrice(schedule_price);
                System.out.println("TicketDAO.java 105行");
                if (seat_status == 1)//如果座位可以买
                    ticket.setTicketStatus(0);//该票可以销售

                else if (seat_status == 0)//这个座位损坏了
                    ticket.setTicketStatus(1);//1表示改票不可以销
                insert(ticket);
            }
        }

    }
}
