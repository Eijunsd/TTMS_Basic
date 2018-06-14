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
        // 设置jsp页面编码
        response.setContentType("text/html;charset=UTF-8");

        String flag = request.getParameter("flag");
        System.out.println("ScheduleServlet中执行的方法flag:" + flag);

        if (flag.equals("searchByPage")) {
            System.out.println("在ScheduleServlet中执行searchByPage...." + new Date());
            searchByPage(request, response, 1);
            return;
        } else if (flag.equals("add")) {
            System.out.println("在ScheduleServlet中执行add...." + new Date());
            try {
                schedule.setPlayId(Integer.parseInt(request.getParameter("PlayId").trim()));
                schedule.setStudioId(Integer.parseInt(request.getParameter("StudioId").trim()));
                schedule.setSchedTime(request.getParameter("SchedTime").trim());
                schedule.setSchedTicketPrice(Integer.parseInt(request.getParameter("SchedTicketPrice").trim()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            res = new ScheduleSrv().insert(schedule);

            System.out.println("在ScheduleServlet中执行add...是否成功？" + res);
        } else if (flag.equals("delete")) {
            System.out.println("在ScheduleServlet中执行delete...." + new Date());
            int ID = Integer.parseInt(request.getParameter("SchedId"));
            res = new ScheduleSrv().delete(ID);
            System.out.println("在ScheduleServlet中执行delete...是否成功？" + res);
        } else if (flag.equals("modify")) {
            System.out.println("在ScheduleServlet中执行modify...." + new Date());

            schedule.setSchedId(Integer.parseInt(request.getParameter("SchedId").trim()));
            schedule.setPlayId(Integer.parseInt(request.getParameter("PlayId").trim()));
            schedule.setStudioId(Integer.parseInt(request.getParameter("StudioId").trim()));
            schedule.setSchedTime(request.getParameter("SchedTime").trim());
            schedule.setSchedTicketPrice(Integer.parseInt(request.getParameter("SchedTicketPrice").trim()));

            res = new ScheduleSrv().update(schedule);

            System.out.println("在ScheduleServlet中执行modify...是否成功？" + res);
        }


        //无论哪个方法都需要进入的部分
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
        if (res) {
            request.setAttribute("desc", "ok");
            searchByPage(request, response, 0);
        }else{
            request.setAttribute("desc", "no");
            request.getRequestDispatcher("/TTMS/schedule/schedule.jsp").forward(request, response);
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

//        System.out.println("123456"+search_schedId);
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
            request.getRequestDispatcher("/TTMS/schedule/schedule.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
