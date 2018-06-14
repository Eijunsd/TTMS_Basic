package cn.xupt.ttms.servlet;

import cn.xupt.ttms.model.Schedule;
import cn.xupt.ttms.service.ScheduleSrv;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "scheduleServlet", urlPatterns = "/TTMS/scheduleServlet")
public class ScheduleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Schedule schedule = new Schedule();
        boolean res = false;

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        // ����jspҳ�����
        response.setContentType("text/html;charset=UTF-8");

        String flag = request.getParameter("flag");
        System.out.println("ScheduleServlet��ִ�еķ���flag:" + flag);

        if (flag.equals("searchByPage")) {
            System.out.println("��ScheduleServlet��ִ��searchByPage...." + new Date());
            searchByPage(request, response, 1);
            return;
        } else if (flag.equals("add")) {
            System.out.println("��ScheduleServlet��ִ��add...." + new Date());
            try {
                schedule.setPlayId(Integer.parseInt(request.getParameter("PlayId").trim()));
                schedule.setStudioId(Integer.parseInt(request.getParameter("StudioId").trim()));
                schedule.setSchedTime(request.getParameter("SchedTime").trim());
                schedule.setSchedTicketPrice(Integer.parseInt(request.getParameter("SchedTicketPrice").trim()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            res = new ScheduleSrv().insert(schedule);

            System.out.println("��ScheduleServlet��ִ��add...�Ƿ�ɹ���" + res);
        } else if (flag.equals("delete")) {
            System.out.println("��ScheduleServlet��ִ��delete...." + new Date());
            int ID = Integer.parseInt(request.getParameter("SchedId"));
            res = new ScheduleSrv().delete(ID);
            System.out.println("��ScheduleServlet��ִ��delete...�Ƿ�ɹ���" + res);
        } else if (flag.equals("modify")) {
            System.out.println("��ScheduleServlet��ִ��modify...." + new Date());

            schedule.setSchedId(Integer.parseInt(request.getParameter("SchedId").trim()));
            schedule.setPlayId(Integer.parseInt(request.getParameter("PlayId").trim()));
            schedule.setStudioId(Integer.parseInt(request.getParameter("StudioId").trim()));
            schedule.setSchedTime(request.getParameter("SchedTime").trim());
            schedule.setSchedTicketPrice(Integer.parseInt(request.getParameter("SchedTicketPrice").trim()));

            res = new ScheduleSrv().update(schedule);

            System.out.println("��ScheduleServlet��ִ��modify...�Ƿ�ɹ���" + res);
        }


        //�����ĸ���������Ҫ����Ĳ���
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
        if (res) {
            request.setAttribute("desc", "ok");
            searchByPage(request, response, 0);
        }else{
            request.setAttribute("desc", "no");
            request.getRequestDispatcher("/TTMS/schedule/schedule.jsp").forward(request, response);
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

//        System.out.println("123456"+search_schedId);
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
            request.getRequestDispatcher("/TTMS/schedule/schedule.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
