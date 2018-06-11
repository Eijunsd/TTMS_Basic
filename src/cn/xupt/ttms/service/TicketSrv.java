package cn.xupt.ttms.service;

import cn.xupt.ttms.idao.DAOFactory;
import cn.xupt.ttms.idao.ITicketDAO;

public class TicketSrv {
    private ITicketDAO ticketDAO = DAOFactory.createTicket();
}
