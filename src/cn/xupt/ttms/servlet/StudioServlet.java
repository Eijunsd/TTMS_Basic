package cn.xupt.ttms.servlet;

import cn.xupt.ttms.dao.StudioDAO;
import cn.xupt.ttms.idao.DAOFactory;
import cn.xupt.ttms.model.Studio;
import cn.xupt.ttms.service.StudioSrv;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/studioServlet")
public class StudioServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("loginflag", "ok");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String method = request.getParameter("method"); // ��ȡ��������

        System.out.println(method);

        if (method.equals("studioSelect")) {
            int currentPage = 1;
            String page = (String) request.getAttribute("currentPage");
            if (page != null && !page.equals("")) {
                currentPage = Integer.parseInt(page) < 1 ? 1
                        : Integer.parseInt(page); // ���ַ���ת��������
            }
            String studio_name = request.getParameter("studio_name");
            StudioDAO dao = (StudioDAO) DAOFactory.createStudioDAO();
            // ��UserDAO�л�ȡ�����û���Ϣ
            ArrayList<Studio> list = dao.findStudioByPage(currentPage, studio_name);
            // ��UserDAO�л�ȡ�ܼ�¼��
            int allCount = dao.getAllCount();
            // ��UserDAO�л�ȡ��ҳ��
            int allPageCount = dao.getAllPageCount();

            currentPage = dao.getCurrentPage();

            // ����request��
            request.setAttribute("allStudio", list);
            request.setAttribute("allCount", allCount);
            request.setAttribute("allPage", allPageCount);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("search_studio_name", studio_name);
            try {
                request.getRequestDispatcher("studio.jsp")
                        .forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        if (method.equals("studioDelete")) {
            int studioid = Integer.parseInt(request.getParameter("studioid"));
            System.out.println("ִ��studioDelete...");
            boolean isSuc = false;
//            isSuc = new StudioSrv().delete(studioid);
            System.out.println("ɾ�����Ϊ" + studioid + "���Ƿ�ɹ���" + isSuc);

            List<Studio> studiosList = new StudioSrv().findStudioAll();

            request.getSession().setAttribute("studiosList", studiosList);

            request.getRequestDispatcher("studio.jsp").forward(request, response);

        }

        if (method.equals("add")) {

            System.out.println("����ִ��add");
            Studio studio = new Studio();
            String studioname = request.getParameter("studioname");
            int studiorowcount = Integer.parseInt(request.getParameter("studiorowcount"));
            int studiocolcount = Integer.parseInt(request.getParameter("studiocolcount"));
            String studiointroduct = request.getParameter("studiointroduct");
            int studioflag = Integer.parseInt(request.getParameter("studioflag"));
            System.out.println("����ִ��add studioflag���Ϊ" + studioflag);
            studio.setStudioName(studioname);
            studio.setStudioRowCount(studiorowcount);
            studio.setStudioColCount(studiocolcount);
            studio.setStudioIntroduction(studiointroduct);
            studio.setStudioFlag(studioflag);
            System.out.println(studio);
//            boolean succ = new StudioSrv().insert(studio);

            request.setAttribute("studio", studio);
            List<Studio> list2 = new StudioSrv().findStudioAll();

            request.getSession().setAttribute("studioList", list2);
            // request.getSession().setAttribute("studioList", list3);
            request.getRequestDispatcher("menu.jsp").forward(request, response);
        } else if (method.equals("searchById")) {
            System.out.println("����searchById");
            int studioid = Integer.parseInt(request.getParameter("studioid"));
            if (studioid > 0) {
                System.out.println(studioid);
                Studio studio = new StudioSrv().findStudioById(studioid);
                request.setAttribute("studio", studio);
                try {
                    System.out.println(studioid);
                    request.getRequestDispatcher("studio.jsp").forward(request,
                            response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } else if (method.equals("searchByPage")) {

            int currentPage = 1; // ��ǰҳĬ��Ϊ��һҳ
            String strpage = request.getParameter("currentPage"); // ��ȡǰ̨���뵱ǰҳ
            if (strpage != null && !strpage.equals("")) {
                currentPage = Integer.parseInt(strpage) < 1 ? 1
                        : Integer.parseInt(strpage); // ���ַ���ת��������
            }
            String studio_name = request.getParameter("studio_name");
            StudioDAO dao = (StudioDAO) DAOFactory.createStudioDAO();
            // ��UserDAO�л�ȡ�����û���Ϣ
            ArrayList<Studio> list = dao.findStudioByPage(currentPage);
            // ��UserDAO�л�ȡ�ܼ�¼��
            int allCount = dao.getAllCount();
            // ��UserDAO�л�ȡ��ҳ��
            int allPageCount = dao.getAllPageCount();
            // ��UserDAO�л�ȡ��ǰҳ
            currentPage = dao.getCurrentPage();

            // ����request��
            request.setAttribute("allStudio", list);
            request.setAttribute("allCount", allCount);
            request.setAttribute("allPageCount", allPageCount);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("search_studio_name", studio_name);
            try {
                request.getRequestDispatcher("studio.jsp")
                        .forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if (method.equals("studioupdate")) {
            System.out.print("����ִ��studioupdate��select");

            StudioDAO dao = (StudioDAO) DAOFactory.createStudioDAO();

            List<Studio> studiolist = new StudioSrv().findStudioAll();
//             ��ӡ StudioSrv().findStudioById(studioid)����Ϣ
            request.setAttribute("studiolist", studiolist);
            System.out.println("�����������");
            request.getRequestDispatcher("studio.jsp").forward(request, response);
        }

    }
}
