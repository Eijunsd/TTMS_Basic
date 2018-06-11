package cn.xupt.ttms.idao;

import cn.xupt.ttms.dao.*;

public class DAOFactory {
    public static IEmployeeDAO createEmployeeDAO() {
        return new EmployeeDAO();
    }

    public static IPlayDAO createPlayDAO() {
        return new PlayDAO();
    }

    public static ISeatDAO createSeatDAO() {
        return new SeatDAO();
    }

    public static IScheduleDAO createScheduleDAO() {
        return new ScheduleDAO();
    }

    public static IUserDAO createUserDAO() {
        return new UserDAO();
    }

    public static ITicketDAO createTicket() {
        return new TicketDAO();
    }

    public static IStudioDAO createStudioDAO() {
        return new StudioDAO();
    }
}
