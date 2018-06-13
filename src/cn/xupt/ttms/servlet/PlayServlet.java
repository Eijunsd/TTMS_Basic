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
            play.setPlayName(request.getParameter(""));
        } else if (flag.equals("modify")) {
            play.setPlayName(request.getParameter("playName"));
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
        }
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
        System.out.println("PlayServlet.java" + method);
        // jsp页面进行查看操作
        //playSelect是一个简单的使用id的查询方法
        if (method.equals("playSelect")) {
            System.out.print("正在执行playservlet的select");

            int playid = Integer.parseInt(request.getParameter("playid"));
            System.out.print("接收到的号" + playid);
            Play play = new PlaySrv().findPlayById(playid);

            System.out.println(play.getPlayId());
            request.setAttribute("objectplay", play);
            System.out.print("传递搜索结果");
            request.getRequestDispatcher("play.jsp").forward(request, response);
        } else if (method.equals("delete")) {
            int playid = Integer.parseInt(request.getParameter("playid"));
            System.out.print("接收到的号" + playid);
            System.out.print("正在执行play 的delete");
            boolean succ = new PlaySrv().delete(playid);
            request.getRequestDispatcher("menu.jsp").forward(request, response);
            Play play = new Play();
            List<Play> list2 = new PlaySrv().getPlayAll();

            request.getSession().setAttribute("playList", list2);
        }

        if (method.equals("modify")) {
            System.out.print("正在执行play的modify");

            int playid = Integer.parseInt(request.getParameter("playid"));
            System.out.println("update的id为" + playid);
            Play play = new Play();
            // 将Playupdate.jsp取来的值赋值给对象

            String playtype = request.getParameter("playtypeid");
            String playlong = request.getParameter("playlongid");
            String playname = request.getParameter("playname");
            String playintroduct = request.getParameter("playintroduct");
            String playimage = "D://1.png";
            //String playimage = request.getParameter("playimage");
            int playlength = Integer.parseInt(request.getParameter("playlength"));
            float playticketprice = Float.parseFloat(request.getParameter("playticketprice"));
            int playstatus = Integer.parseInt(request.getParameter("playstatus"));
            System.out.println("playtype  " + playtype);
            System.out.println("playlong " + playlong);
            System.out.println("playname " + playname);
            System.out.println("playintroduct " + playintroduct);
            System.out.println("playimage " + playimage);
            System.out.println("playlength " + playlength);
            System.out.println("playticketprice " + playticketprice);
            System.out.println("playstatus " + playstatus);


// 			将从页面取到的值塞入对象中
            play.setPlayId(playid);
            play.setPlayType(playtype);
            play.setPlayLang(playlong);
            play.setPlayName(playname);
            play.setPlayIntroduction(playintroduct);
            play.setPlayImage(playimage);
            play.setPlayLength(playlength);
            play.setPlayTicketPrice(playticketprice);
            play.setPlayStatus(playstatus);

            //调用dao层服务
            boolean isSuccessed = false;
//            isSuccessed = new PlaySrv().update(play);

            System.out.println("修改ID为"+playid+"是否成功？" +isSuccessed);
            List<Play> list2 = new PlaySrv().getPlayAll();
            request.getSession().setAttribute("playList", list2);

            request.getRequestDispatcher("play.jsp").forward(request, response);
        } else if (method.equals("add")) {

            System.out.println("正在执行add");
            Play play = new Play();

            String playtype = request.getParameter("playtypeid");
            String playlong = request.getParameter("playlongid");
            String playname = request.getParameter("playname");
            String playintroduct = request.getParameter("playintroduct");
            //String playimage = request.getParameter("playimage");某认D://head.png
            String playimage = "d:\\head.png";
            int playlength = Integer.parseInt(request.getParameter("playlength"));
            float playticketprice = Float.parseFloat(request.getParameter("playticketprice"));
            int playstatus = Integer.parseInt(request.getParameter("playstatus"));

//     			将从页面取到的值塞入对象中
            play.setPlayType(playtype);
            play.setPlayLang(playlong);
            play.setPlayName(playname);
            play.setPlayIntroduction(playintroduct);
            play.setPlayImage(playimage);
            play.setPlayLength(playlength);
            play.setPlayTicketPrice(playticketprice);
            play.setPlayStatus(playstatus);

            boolean isSuccessed = false;
            boolean succ = new PlaySrv().insert(play);

            // request.setAttribute("play", play);
            //List<Play> list2 = new PlaySrv().findPlayAll();

            //request.getSession().setAttribute("playList", list2);
            // request.getSession().setAttribute("studioList", list3);
            request.getRequestDispatcher("play.jsp").forward(request, response);
        } else if (method.equals("searchById")) {
            System.out.println("进入PlayServlet的searchById");
            int playid = Integer.parseInt(request.getParameter("playid"));
            if (playid > 0) {
                System.out.println(playid);
                Play play = new PlaySrv().findPlayById(playid);
                request.setAttribute("play", play);
                request.setAttribute("playstatastype", play.getPlayStatus());
                try {
                    System.out.print("serchbyid" + play.getPlayType());
                    System.out.println("PlayServlet.java" + playid);
                    System.out.println("" + play.getPlayStatus());
                    request.getRequestDispatcher("play.jsp").forward(request,
                            response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (method.equals("searchByPage")) {
            System.out.println("进入searchByPage");
            int currentPage = 1; // 当前页默认为第一页
            String strpage = request.getParameter("currentPage"); // 获取前台传入当前页
            if (strpage != null && !strpage.equals("")) {
                currentPage = Integer.parseInt(strpage) < 1 ? 1
                        : Integer.parseInt(strpage); // 将字符串转换成整型
            }
            String play_name = request.getParameter("play_name");
            PlayDAO dao = (PlayDAO) DAOFactory.createPlayDAO();
            // 从UserDAO中获取所有用户信息
            ArrayList<Play> list = dao.getPlayByPage(currentPage, play_name);
            // 从UserDAO中获取总记录数
            int allCount = dao.getAllCount();
            // 从UserDAO中获取总页数
            int allPageCount = dao.getAllPageCount();
            // 从UserDAO中获取当前页
            currentPage = dao.getCurrentPage();

            // 存入request中
            request.setAttribute("allPlay", list);
            request.setAttribute("allCount", allCount);
            request.setAttribute("allPageCount", allPageCount);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("search_play_name", play_name);
            try {
                request.getRequestDispatcher("TTMS/play/play.jsp")
                        .forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }*/
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
