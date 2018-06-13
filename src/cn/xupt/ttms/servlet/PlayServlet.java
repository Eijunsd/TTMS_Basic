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
        // ����jspҳ�����
        response.setContentType("text/html;charset=UTF-8");

        String flag = request.getParameter("flag");
        System.out.println("ScheduleServlet��ִ�еķ���flag:" + flag);

        if (flag.equals("searchByPage")) {
            System.out.println("ScheduleServlet��ִ�е�searchByPage...");
            searchByPage(request, response, 1);
            return;
        } else if (flag.equals("add")) {
            System.out.println("ScheduleServlet��ִ�е�add...");
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
        // ��UserDAO�л�ȡ��ҳ��
        int allPageCount = new ScheduleSrv().getAllPageCount();
        // ��UserDAO�л�ȡ��ǰҳ
        int currentPage = new ScheduleSrv().getCurrentPage();

        //�����ĸ�������Ҫ���е�
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
        int currentPage = 1; // ��ǰҳĬ��Ϊ��һҳ
        String playId = "";

        String strpage = request.getParameter("currentPage"); // ��ȡǰ̨���뵱ǰҳ
        if (strpage != null && !strpage.equals("")) {
            currentPage = Integer.parseInt(strpage) < 1 ? 1 : Integer.parseInt(strpage); // ���ַ���ת��������
        }

        if (flag != 0) {
            playId = request.getParameter("search_playId").trim();
        }

//        System.out.println("123456"+search_schedId);
        // ��UserDAO�л�ȡ�����û���Ϣ
        List<Play> list = new PlaySrv().findPlayByPage(currentPage, playId);

        // ��UserDAO�л�ȡ�ܼ�¼��
        int allCount = new ScheduleSrv().getAllCount();
        // ��UserDAO�л�ȡ��ҳ��
        int allPageCount = new ScheduleSrv().getAllPageCount();
        // ��UserDAO�л�ȡ��ǰҳ
        currentPage = new ScheduleSrv().getCurrentPage();

        // ����request��
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
