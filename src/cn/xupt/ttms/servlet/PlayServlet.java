package cn.xupt.ttms.servlet;

import cn.xupt.ttms.dao.PlayDAO;
import cn.xupt.ttms.idao.DAOFactory;
import cn.xupt.ttms.model.Play;
import cn.xupt.ttms.service.PlaySrv;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "playServlet",urlPatterns = "/playServlet")
public class PlayServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("loginflag", "ok");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String method = request.getParameter("method"); // ��ȡ������
        System.out.println("PlayServlet.java" + method);
        // jspҳ����в鿴����
        //playSelect��һ���򵥵�ʹ��id�Ĳ�ѯ����
        if (method.equals("playSelect")) {
            System.out.print("����ִ��playservlet��select");

            int playid = Integer.parseInt(request.getParameter("playid"));
            System.out.print("���յ��ĺ�" + playid);
            Play play = new PlaySrv().findPlayById(playid);

            System.out.println(play.getPlayId());
            request.setAttribute("objectplay", play);
            System.out.print("�����������");
            request.getRequestDispatcher("play.jsp").forward(request, response);
        } else if (method.equals("delete")) {
            int playid = Integer.parseInt(request.getParameter("playid"));
            System.out.print("���յ��ĺ�" + playid);
            System.out.print("����ִ��play ��delete");
            boolean succ = new PlaySrv().delete(playid);
            request.getRequestDispatcher("menu.jsp").forward(request, response);
            Play play = new Play();
            List<Play> list2 = new PlaySrv().getPlayAll();

            request.getSession().setAttribute("playList", list2);
        }

        if (method.equals("modify")) {
            System.out.print("����ִ��play��modify");

            int playid = Integer.parseInt(request.getParameter("playid"));
            System.out.println("update��idΪ" + playid);
            Play play = new Play();
            // ��Playupdate.jspȡ����ֵ��ֵ������

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


// 			����ҳ��ȡ����ֵ���������
            play.setPlayId(playid);
            play.setPlayType(playtype);
            play.setPlayLang(playlong);
            play.setPlayName(playname);
            play.setPlayIntroduction(playintroduct);
            play.setPlayImage(playimage);
            play.setPlayLength(playlength);
            play.setPlayTicketPrice(playticketprice);
            play.setPlayStatus(playstatus);

            //����dao�����
            boolean isSuccessed = false;
//            isSuccessed = new PlaySrv().update(play);

            System.out.println("�޸�IDΪ"+playid+"�Ƿ�ɹ���" +isSuccessed);
            List<Play> list2 = new PlaySrv().getPlayAll();
            request.getSession().setAttribute("playList", list2);

            request.getRequestDispatcher("play.jsp").forward(request, response);
        } else if (method.equals("add")) {

            System.out.println("����ִ��add");
            Play play = new Play();

            String playtype = request.getParameter("playtypeid");
            String playlong = request.getParameter("playlongid");
            String playname = request.getParameter("playname");
            String playintroduct = request.getParameter("playintroduct");
            //String playimage = request.getParameter("playimage");ĳ��D://head.png
            String playimage = "d:\\head.png";
            int playlength = Integer.parseInt(request.getParameter("playlength"));
            float playticketprice = Float.parseFloat(request.getParameter("playticketprice"));
            int playstatus = Integer.parseInt(request.getParameter("playstatus"));

//     			����ҳ��ȡ����ֵ���������
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
            System.out.println("����PlayServlet��searchById");
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
            System.out.println("����searchByPage");
            int currentPage = 1; // ��ǰҳĬ��Ϊ��һҳ
            String strpage = request.getParameter("currentPage"); // ��ȡǰ̨���뵱ǰҳ
            if (strpage != null && !strpage.equals("")) {
                currentPage = Integer.parseInt(strpage) < 1 ? 1
                        : Integer.parseInt(strpage); // ���ַ���ת��������
            }
            String play_name = request.getParameter("play_name");
            PlayDAO dao = (PlayDAO) DAOFactory.createPlayDAO();
            // ��UserDAO�л�ȡ�����û���Ϣ
            ArrayList<Play> list = dao.getPlayByPage(currentPage, play_name);
            // ��UserDAO�л�ȡ�ܼ�¼��
            int allCount = dao.getAllCount();
            // ��UserDAO�л�ȡ��ҳ��
            int allPageCount = dao.getAllPageCount();
            // ��UserDAO�л�ȡ��ǰҳ
            currentPage = dao.getCurrentPage();

            // ����request��
            request.setAttribute("allPlay", list);
            request.setAttribute("allCount", allCount);
            request.setAttribute("allPageCount", allPageCount);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("search_play_name", play_name);
            try {
                request.getRequestDispatcher("play.jsp")
                        .forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
