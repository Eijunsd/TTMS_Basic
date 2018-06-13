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
        // ����jspҳ�����
        response.setContentType("text/html;charset=UTF-8");

        String flag = request.getParameter("flag");
        System.out.println("ScheduleServlet��ִ�еķ���flag:" + flag);

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
//                System.out.println("�ݳ��ƻ�ʱ�䣺" + time);
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
            // ��UserDAO�л�ȡ��ҳ��
            int allPageCount = new ScheduleSrv().getAllPageCount();
            // ��UserDAO�л�ȡ��ǰҳ
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
        // ��UserDAO�л�ȡ��ҳ��
        int allPageCount = new ScheduleSrv().getAllPageCount();
        // ��UserDAO�л�ȡ��ǰҳ
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
        int currentPage = 1; // ��ǰҳĬ��Ϊ��һҳ
        String search_schedId = "";

        String strpage = request.getParameter("currentPage"); // ��ȡǰ̨���뵱ǰҳ
        if (strpage != null && !strpage.equals("")) {
            currentPage = Integer.parseInt(strpage) < 1 ? 1 : Integer.parseInt(strpage); // ���ַ���ת��������
        }

        if (flag != 0) {
            search_schedId = request.getParameter("search_schedId").trim();
        }

        System.out.println("123456"+search_schedId);
        // ��UserDAO�л�ȡ�����û���Ϣ
        List<Schedule> list = new ScheduleSrv().findScheduleByPage(currentPage, search_schedId);
        // ��UserDAO�л�ȡ�ܼ�¼��
        int allCount = new ScheduleSrv().getAllCount();
        // ��UserDAO�л�ȡ��ҳ��
        int allPageCount = new ScheduleSrv().getAllPageCount();
        // ��UserDAO�л�ȡ��ǰҳ
        currentPage = new ScheduleSrv().getCurrentPage();

        // ����request��
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
        String method = request.getParameter("method"); // ��ȡ������
        System.out.println("��ִ��scheduleservlet"+method);
        // jspҳ����в鿴����
        if(method.equals("scheduleSelect"))
        {
            System.out.print("����ִ��scheduleservlet��select");

            int search_schedule_name = Integer.parseInt(request.getParameter("search_schedule_name"));
            System.out.print("���յ��ĺ�" + search_schedule_name);
            Schedule schedule = new ScheduleSrv().findScheduleById(search_schedule_name);

            System.out.print(schedule.getSched_id());
            request.setAttribute("objectschedule", schedule);
            System.out.print("�����������");
            request.getRequestDispatcher("schedule/scheduleSelectResult.jsp").forward(request, response);
        }
        else
            if(method.equals("delete"))
            {
                int scheduleid = Integer.parseInt(request.getParameter("scheduleid"));
                System.out.print("���յ��ĺ�" + scheduleid);
                System.out.print("����ִ��delete");
                boolean succ = new ScheduleSrv().delete(scheduleid);
                request.getRequestDispatcher("menu.jsp").forward(request, response);
                Schedule schedule = new Schedule();
                List<Schedule> list2 = new ScheduleSrv().findScheduleAll();

                request.getSession().setAttribute("scheduleList", list2);
            }

        if(method.equals("modify"))
        {
            System.out.print("����ִ��modify");

            int scheduleid = Integer.parseInt(request.getParameter("scheduleid"));
            System.out.println("update��idΪ" + scheduleid);
            Schedule schedule = new Schedule();
            // ��Scheduleupdate.jspȡ����ֵ��ֵ������


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

                System.out.println("����ִ��add");
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
                System.out.println("����ִ��add scheduleschedtime���λ" + scheduleschedtime.toString());

                schedule.setStudio_id(schedulestudioid);
                schedule.setPlay_id(scheduleplayid);
                schedule.setSched_time(scheduleschedtime);
                schedule.setSched_ticket_price(scheduleticket_price);
                boolean succ = new ScheduleSrv().add(schedule);
                //��ĳ�飬������ݳ���ʱ��Ʊ
                if(succ==true) {
                	//new TicketDAO().addbyscheduleid(scheduleid);

                }



                request.getRequestDispatcher("schedule/schedule.jsp").forward(request, response);
            }
            else
                if(method.equals("searchById"))
                {
                    System.out.println("����searchById");
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

                        int currentPage = 1; // ��ǰҳĬ��Ϊ��һҳ
                        String strpage = request.getParameter("currentPage"); // ��ȡǰ̨���뵱ǰҳ
                        if(strpage != null && !strpage.equals(""))
                        {
                            currentPage = Integer.parseInt(strpage) < 1 ? 1
                                    : Integer.parseInt(strpage); // ���ַ���ת��������
                        }
                        String search_schedule_name = request.getParameter("search_schedule_name");

                        ScheduleDAO dao = (ScheduleDAO) DAOFactory.creatScheduleDAO();
                        // ��UserDAO�л�ȡ�����û���Ϣ
                        ArrayList<Schedule> list = dao.findScheduleByPage(currentPage, search_schedule_name);
                        // ��UserDAO�л�ȡ�ܼ�¼��
                        int allCount = dao.getAllCount();
                        // ��UserDAO�л�ȡ��ҳ��
                        int allPageCount = dao.getAllPageCount();
                        // ��UserDAO�л�ȡ��ǰҳ
                        currentPage = dao.getCurrentPage();

                        // ����request��
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
                            System.out.println("����Seating");
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
