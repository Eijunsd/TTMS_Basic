package cn.xupt.ttms.servlet;

import cn.xupt.ttms.model.Play;
import cn.xupt.ttms.service.PlaySrv;
import cn.xupt.ttms.service.ScheduleSrv;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "playServlet", urlPatterns = "/TTMS/playServlet")
public class PlayServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Play play = new Play();
        boolean result = false;

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        // 设置jsp页面编码
        response.setContentType("text/html;charset=UTF-8");

        String flag = request.getParameter("flag");
        System.out.println("ScheduleServlet中执行的方法flag:" + flag);

        if (flag.equals("searchByPage")) {
            System.out.println("ScheduleServlet中执行的searchByPage...");
            searchByPage(request, response, 1);
            return;
        } else if (flag.equals("add")) {
            System.out.println("ScheduleServlet中执行的add...");
            play.setPlayName(request.getParameter("playName"));
            play.setPlayType(request.getParameter("playType"));
            play.setPlayLang(request.getParameter("playLang"));
            play.setPlayLength(Integer.parseInt(request.getParameter("playLength")));
            play.setPlayTicketPrice(Float.parseFloat(request.getParameter("playPrice")));
            play.setPlayStatus(Integer.parseInt(request.getParameter("playStatus")));

            System.out.println(play);

            result = new PlaySrv().insert(play);
        } else if (flag.equals("modify")) {
            play.setPlayId(Integer.parseInt(request.getParameter("playId")));
            play.setPlayName(request.getParameter("playName"));
            play.setPlayType(request.getParameter("playType"));
            play.setPlayLang(request.getParameter("playLang"));
            play.setPlayLength(Integer.parseInt(request.getParameter("playLength")));
            play.setPlayTicketPrice(Float.parseFloat(request.getParameter("playPrice")));
            play.setPlayStatus(Integer.parseInt(request.getParameter("playStatus")));

            System.out.println(play);

            result = new PlaySrv().update(play);
        } else if (flag.equals("delete")) {
            int playId = Integer.parseInt(request.getParameter("playNo"));

            result = new PlaySrv().delete(playId);
        }

        int allCount = new ScheduleSrv().getAllCount();
        // 从UserDAO中获取总页数
        int allPageCount = new ScheduleSrv().getAllPageCount();
        // 从UserDAO中获取当前页
        int currentPage = new ScheduleSrv().getCurrentPage();

        //无论哪个操作都要进行的
        List<Play> list = new PlaySrv().getPlayAll();
        System.out.println(list);

        request.setAttribute("allPlay", list);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("allCount", allCount);
        request.setAttribute("allPageCount", allPageCount);
        if (result) {
            request.setAttribute("desc", "ok");
            searchByPage(request, response, 0);
        } else {
            request.setAttribute("desc", "no");
            request.getRequestDispatcher("/TTMS/play/play.jsp").forward(request, response);
        }

    }

    private void searchByPage(HttpServletRequest request, HttpServletResponse response, int flag) {
        int currentPage = 1; // 当前页默认为第一页
        String playId = "";

        String strpage = request.getParameter("currentPage"); // 获取前台传入当前页
        if (strpage != null && !strpage.equals("")) {
            currentPage = Integer.parseInt(strpage) < 1 ? 1 : Integer.parseInt(strpage); // 将字符串转换成整型
        }

        if (flag != 0) {
            playId = request.getParameter("search_playId").trim();
        }

//        System.out.println("123456"+search_schedId);
        // 从UserDAO中获取所有用户信息
        List<Play> list = new PlaySrv().findPlayByPage(currentPage, playId);

        // 从UserDAO中获取总记录数
        int allCount = new ScheduleSrv().getAllCount();
        // 从UserDAO中获取总页数
        int allPageCount = new ScheduleSrv().getAllPageCount();
        // 从UserDAO中获取当前页
        currentPage = new ScheduleSrv().getCurrentPage();

        // 存入request中
        request.setAttribute("allPlay", list);
        request.setAttribute("allCount", allCount);
        request.setAttribute("allPageCount", allPageCount);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("playId", playId);

        request.setAttribute("desc", "ok");
        try {
            request.getRequestDispatcher("/TTMS/play/play.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
