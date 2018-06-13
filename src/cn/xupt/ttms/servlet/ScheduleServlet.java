package cn.xupt.ttms.servlet;

import cn.xupt.ttms.model.Schedule;
import cn.xupt.ttms.service.ScheduleSrv;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(name = "scheduleServlet", urlPatterns = "/TTMS/scheduleServlet")
public class ScheduleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Schedule schedule = new Schedule();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        // 设置jsp页面编码
        response.setContentType("text/html;charset=UTF-8");

        String flag = request.getParameter("flag");
        System.out.println("ScheduleServlet中执行的方法flag:" + flag);

        int schedId = 0;
        int playId = 0;
        int studioId = 0;
        String schedTime = null;
        int schedTicketPrice = 0;

        String studioName = null;
        String playName = null;

        SimpleDateFormat str2date = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        boolean res = false;
        if (flag.equals("add")) {
            System.out.println("add....");
            try {
                playId = Integer.parseInt(request.getParameter("PlayId").trim());
                studioId = Integer.parseInt(request.getParameter("StudioId").trim());
                schedId = Integer.parseInt(request.getParameter("SchedId").trim());
                String time = request.getParameter("SchedTime");
//                System.out.println("演出计划时间：" + time);
                schedTime = request.getParameter("SchedTime").trim();
                schedTicketPrice = Integer.parseInt(request.getParameter("SchedTicketPrice").trim());
//                System.out.println(schedTicketPrice);
            } catch (Exception e) {
                e.printStackTrace();
            }

            schedule.setPlayId(playId);
            schedule.setStudioId(studioId);
            schedule.setSchedTime(schedTime);
            schedule.setSchedTicketPrice(schedTicketPrice);
            System.out.println(schedule);
            res = new ScheduleSrv().insert(schedule);
        } else if (flag.equals("modify")) {
            System.out.println("modify....");
            try {
                playId = Integer.parseInt(request.getParameter("PlayId").trim());
                studioId = Integer.parseInt(request.getParameter("StudioId").trim());
                schedId = Integer.parseInt(request.getParameter("SchedId").trim());
                schedTime = request.getParameter("SchedTime").trim();
                schedTicketPrice = Integer.parseInt(request.getParameter("schedTicketPrice").trim());
            } catch (Exception e) {
                e.printStackTrace();
            }
            schedule.setSchedId(schedId);
            schedule.setPlayId(playId);
            schedule.setStudioId(studioId);
            schedule.setSchedTime(schedTime);
            schedule.setSchedTicketPrice(schedTicketPrice);
            System.out.println(schedule);
            res = new ScheduleSrv().update(schedule);
        } else if (flag.equals("delete")) {
            System.out.println("delete...");
            try {
                schedId = Integer.parseInt(request.getParameter("SchedId").trim());
            } catch (Exception e) {
                e.printStackTrace();
            }
            schedule.setSchedId(schedId);
            System.out.println(schedule);
//            res = new ScheduleSrv().delete(schedId);
        } else if (flag.equals("searchByPage")) {
            System.out.println("searchByPage...");
            searchByPage(request, response, 1);
            return;
        } else if (flag.equals("searchAll")) {
            System.out.println("SearchAll...");

            List<Schedule> list = new ScheduleSrv().findScheduleAll();
            System.out.println(list);
            int allCount = new ScheduleSrv().getAllCount();
            // 从UserDAO中获取总页数
            int allPageCount = new ScheduleSrv().getAllPageCount();
            // 从UserDAO中获取当前页
            int currentPage = new ScheduleSrv().getCurrentPage();
            request.setAttribute("allSchedule", list);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("allCount", allCount);
            request.setAttribute("allPageCount", allPageCount);

//            request.setAttribute("desc", "ok");
//            try {
//                request.getRequestDispatcher("/schedule.jsp").forward(request, response);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        }
        List<Schedule> list = new ScheduleSrv().findScheduleAll();
        System.out.println(list);
        int allCount = new ScheduleSrv().getAllCount();
        // 从UserDAO中获取总页数
        int allPageCount = new ScheduleSrv().getAllPageCount();
        // 从UserDAO中获取当前页
        int currentPage = new ScheduleSrv().getCurrentPage();
        request.setAttribute("allSchedule", list);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("allCount", allCount);
        request.setAttribute("allPageCount", allPageCount);

        request.setAttribute("desc", "ok");
        try {
            request.getRequestDispatcher("/schedule.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void searchByPage(HttpServletRequest request, HttpServletResponse response, int flag) {
        int currentPage = 1; // 当前页默认为第一页
        String search_schedId = "";

        String strpage = request.getParameter("currentPage"); // 获取前台传入当前页
        if (strpage != null && !strpage.equals("")) {
            currentPage = Integer.parseInt(strpage) < 1 ? 1 : Integer.parseInt(strpage); // 将字符串转换成整型
        }

        if (flag != 0) {
            search_schedId = request.getParameter("search_schedId").trim();
        }

        System.out.println("123456"+search_schedId);
        // 从UserDAO中获取所有用户信息
        List<Schedule> list = new ScheduleSrv().findScheduleByPage(currentPage, search_schedId);
        // 从UserDAO中获取总记录数
        int allCount = new ScheduleSrv().getAllCount();
        // 从UserDAO中获取总页数
        int allPageCount = new ScheduleSrv().getAllPageCount();
        // 从UserDAO中获取当前页
        currentPage = new ScheduleSrv().getCurrentPage();

        // 存入request中
        request.setAttribute("allSchedule", list);
        request.setAttribute("allCount", allCount);
        request.setAttribute("allPageCount", allPageCount);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("search_schedId", search_schedId);

        request.setAttribute("desc", "ok");
        try {
            request.getRequestDispatcher("/schedule.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }


        /*

        request.getSession().setAttribute("loginflag", "ok");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String method = request.getParameter("method"); // 获取操作数
        System.out.println("在执行scheduleservlet"+method);
        // jsp页面进行查看操作
        if(method.equals("scheduleSelect"))
        {
            System.out.print("正在执行scheduleservlet的select");

            int search_schedule_name = Integer.parseInt(request.getParameter("search_schedule_name"));
            System.out.print("接收到的号" + search_schedule_name);
            Schedule schedule = new ScheduleSrv().findScheduleById(search_schedule_name);

            System.out.print(schedule.getSched_id());
            request.setAttribute("objectschedule", schedule);
            System.out.print("传递搜索结果");
            request.getRequestDispatcher("schedule/scheduleSelectResult.jsp").forward(request, response);
        }
        else
            if(method.equals("delete"))
            {
                int scheduleid = Integer.parseInt(request.getParameter("scheduleid"));
                System.out.print("接收到的号" + scheduleid);
                System.out.print("正在执行delete");
                boolean succ = new ScheduleSrv().delete(scheduleid);
                request.getRequestDispatcher("menu.jsp").forward(request, response);
                Schedule schedule = new Schedule();
                List<Schedule> list2 = new ScheduleSrv().findScheduleAll();

                request.getSession().setAttribute("scheduleList", list2);
            }

        if(method.equals("modify"))
        {
            System.out.print("正在执行modify");

            int scheduleid = Integer.parseInt(request.getParameter("scheduleid"));
            System.out.println("update的id为" + scheduleid);
            Schedule schedule = new Schedule();
            // 将Scheduleupdate.jsp取来的值赋值给对象


            SimpleDateFormat str2date =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );

            int schedulestudioid = Integer.parseInt(request.getParameter("schedulestudioid"));
            int scheduleplayid = Integer.parseInt(request.getParameter("scheduleplayid"));
            Date scheduleschedtime = null;
			try {
				scheduleschedtime = str2date.parse(request.getParameter("scheduleschedtime"));
			} catch (ParseException e) {

				e.printStackTrace();
			}
            double scheduleticket_price = Double.parseDouble(request.getParameter("scheduleticket_price"));

            schedule.setSched_id(scheduleid);
            schedule.setStudio_id(schedulestudioid);
            schedule.setPlay_id(scheduleplayid);
            schedule.setSched_time(scheduleschedtime);
            schedule.setSched_ticket_price(scheduleticket_price);

            boolean succ = new ScheduleSrv().modify(schedule);

            List<Schedule> list2 = new ScheduleSrv().findScheduleAll();
            request.getSession().setAttribute("scheduleList", list2);
            request.getRequestDispatcher("menu.jsp").forward(request, response);
        }
        else
            if(method.equals("add"))
            {

                System.out.println("正在执行add");
                Schedule schedule = new Schedule();
                SimpleDateFormat str2date =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );

                int schedulestudioid = Integer.parseInt(request.getParameter("schedulestudioid"));
                int scheduleplayid = Integer.parseInt(request.getParameter("scheduleplayid"));
                Date scheduleschedtime = null;
				try {
					String scheduleschedtimestr = request.getParameter("scheduleschedtime");
					System.out.println(scheduleschedtimestr);
					scheduleschedtime = str2date.parse(scheduleschedtimestr);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                int scheduleticket_price = Integer.parseInt(request.getParameter("scheduleticket_price"));
                System.out.println("正在执行add scheduleschedtime输出位" + scheduleschedtime.toString());

                schedule.setStudio_id(schedulestudioid);
                schedule.setPlay_id(scheduleplayid);
                schedule.setSched_time(scheduleschedtime);
                schedule.setSched_ticket_price(scheduleticket_price);
                boolean succ = new ScheduleSrv().add(schedule);
                //新某块，在添加演出厅时添票
                if(succ==true) {
                	//new TicketDAO().addbyscheduleid(scheduleid);

                }



                request.getRequestDispatcher("schedule/schedule.jsp").forward(request, response);
            }
            else
                if(method.equals("searchById"))
                {
                    System.out.println("进入searchById");
                    int scheduleid = Integer.parseInt(request.getParameter("scheduleid"));
                    if(scheduleid > 0)
                    {
                        System.out.println(scheduleid);
                        Schedule schedule = new ScheduleSrv().findScheduleById(scheduleid);
                        request.setAttribute("schedule", schedule);
                        try
                        {
                            System.out.println(scheduleid);
                            request.getRequestDispatcher("schedule/scheduleupdate.jsp").forward(request,
                                                                                        response);
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                    }

                }
                else
                    if(method.equals("searchByPage"))
                    {

                        int currentPage = 1; // 当前页默认为第一页
                        String strpage = request.getParameter("currentPage"); // 获取前台传入当前页
                        if(strpage != null && !strpage.equals(""))
                        {
                            currentPage = Integer.parseInt(strpage) < 1 ? 1
                                    : Integer.parseInt(strpage); // 将字符串转换成整型
                        }
                        String search_schedule_name = request.getParameter("search_schedule_name");

                        ScheduleDAO dao = (ScheduleDAO) DAOFactory.creatScheduleDAO();
                        // 从UserDAO中获取所有用户信息
                        ArrayList<Schedule> list = dao.findScheduleByPage(currentPage, search_schedule_name);
                        // 从UserDAO中获取总记录数
                        int allCount = dao.getAllCount();
                        // 从UserDAO中获取总页数
                        int allPageCount = dao.getAllPageCount();
                        // 从UserDAO中获取当前页
                        currentPage = dao.getCurrentPage();

                        // 存入request中
                        request.setAttribute("allSchedule", list);
                        request.setAttribute("allCount", allCount);
                        request.setAttribute("allPageCount", allPageCount);
                        request.setAttribute("currentPage", currentPage);
                        request.setAttribute("search_Schedule_name",search_schedule_name);
                        System.out.println(allPageCount);
                        try
                        {
                            request.getRequestDispatcher("schedule/schedulelist.jsp")
                                   .forward(request, response);
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }

                    }

                    else
                        if(method.equals("Seating"))
                        {
                            System.out.println("进入Seating");
                            int scheduleid = Integer.parseInt(request.getParameter("scheduleid"));
                            if(scheduleid > 0)
                            {
                                System.out.println(scheduleid);
                                new TicketDAO().addbyscheduleid(scheduleid);
                                try
                                {
                                    System.out.println(scheduleid);
                                    request.getRequestDispatcher("schedule/schedule.jsp").forward(request,
                                                                                                response);
                                }
                                catch(Exception e)
                                {
                                    e.printStackTrace();
                                }
                            }
                        }
         */
    }
}
