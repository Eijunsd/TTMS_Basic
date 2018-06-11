package cn.xupt.ttms.idao;

import cn.xupt.ttms.model.Ticket;

import java.util.ArrayList;

public interface ITicketDAO {
    public ArrayList<Ticket> findSeatByPage(int cPage, String studio_id);
    public int update(Ticket ticket);
    public boolean insert(Ticket ticket);
    public void addByScheduleId(int scheduleid);
    public void searchByscheduleId(int scheduleid);
}
