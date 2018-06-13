package cn.xupt.ttms.servlet;

import cn.xupt.ttms.model.Studio;
import cn.xupt.ttms.service.ScheduleSrv;
import cn.xupt.ttms.service.StudioSrv;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "studioServlet", urlPatterns = "/TTMS/studioServlet")
public class StudioServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Studio studio = new Studio();
        boolean result = false;

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        // ����jspҳ�����
        response.setContentType("text/html;charset=UTF-8");

        String flag = request.getParameter("flag");
        System.out.println("ScheduleServlet��ִ�еķ���flag:" + flag);
        if (flag.equals("searchByPage")) {
            System.out.println("ScheduleServlet��ִ�е�searchByPage...");
            searchByPage(request, response, 1);
            return;
        }
        if (flag.equals("add")) {
            System.out.println("ScheduleServlet��ִ�е�add...");

            studio.setStudioName(request.getParameter("studioName"));
            studio.setStudioRowCount(Integer.parseInt(request.getParameter("studioRow")));
            studio.setStudioColCount(Integer.parseInt(request.getParameter("studioCol")));
            studio.setStudioFlag(Integer.parseInt(request.getParameter("studioStatus")));

            System.out.println(studio);

            result = new StudioSrv().insert(studio);
        }
        if (flag.equals("modify")) {
            System.out.println("ScheduleServlet��ִ�е�modify...");
            studio.setStudioName(request.getParameter("name"));
            studio.setStudioRowCount(Integer.parseInt(request.getParameter("row")));
            studio.setStudioColCount(Integer.parseInt(request.getParameter("col")));
            studio.setStudioFlag(Integer.parseInt(request.getParameter("status")));

            System.out.println(studio);

            result = new StudioSrv().update(studio);
        }
        if (flag.equals("delete")) {

            System.out.println("ScheduleServlet��ִ�е�delete...");
            int studioId = Integer.parseInt(request.getParameter("studioId"));
            result = new StudioSrv().delete(studioId);
        }

        if (result) {
            request.setAttribute("desc", "ok");
            searchByPage(request, response, 0);
        } else {
            request.setAttribute("desc", "no");
            request.getRequestDispatcher("/TTMS/studio/studio.jsp").forward(request, response);
        }
    }


    private void searchByPage(HttpServletRequest request, HttpServletResponse response, int flag) {
        int currentPage = 1; // ��ǰҳĬ��Ϊ��һҳ
        String studioId = "";

        String strpage = request.getParameter("currentPage"); // ��ȡǰ̨���뵱ǰҳ
        System.out.println("StudioServlet...searchByPage�е�currentPage" + strpage);
        if (strpage != null && !strpage.equals("")) {
            currentPage = Integer.parseInt(strpage) < 1 ? 1 : Integer.parseInt(strpage); // ���ַ���ת��������
        }

        if (flag != 0) {
            studioId = request.getParameter("search_studioId").trim();
        }

        ArrayList<Studio> list = new StudioSrv().findStudioByPage(currentPage, studioId);

//        System.out.println(list);
        // ��UserDAO�л�ȡ�ܼ�¼��
        int allCount = new StudioSrv().getAllCount();
        // ��UserDAO�л�ȡ��ҳ��
        int allPageCount = new StudioSrv().getAllPageCount();
        System.out.println("StudioServlet�е�allPageCount"+allPageCount);
        // ��UserDAO�л�ȡ��ǰҳ
        currentPage = new StudioSrv().getCurrentPage();

        // ����request��
        request.setAttribute("allStudio", list);
        request.setAttribute("allCount", allCount);
        request.setAttribute("allPageCount", allPageCount);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("studioId", studioId);

        request.setAttribute("desc", "ok");
        try {
            request.getRequestDispatcher("/TTMS/studio/studio.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
