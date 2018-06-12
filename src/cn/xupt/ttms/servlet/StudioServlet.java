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
        String method = request.getParameter("method"); // 获取操作方法

        System.out.println(method);

        if (method.equals("studioSelect")) {
            int currentPage = 1;
            String page = (String) request.getAttribute("currentPage");
            if (page != null && !page.equals("")) {
                currentPage = Integer.parseInt(page) < 1 ? 1
                        : Integer.parseInt(page); // 将字符串转换成整型
            }
            String studio_name = request.getParameter("studio_name");
            StudioDAO dao = (StudioDAO) DAOFactory.createStudioDAO();
            // 从UserDAO中获取所有用户信息
            ArrayList<Studio> list = dao.findStudioByPage(currentPage, studio_name);
            // 从UserDAO中获取总记录数
            int allCount = dao.getAllCount();
            // 从UserDAO中获取总页数
            int allPageCount = dao.getAllPageCount();

            currentPage = dao.getCurrentPage();

            // 存入request中
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
            System.out.println("执行studioDelete...");
            boolean isSuc = false;
//            isSuc = new StudioSrv().delete(studioid);
            System.out.println("删除编号为" + studioid + "，是否成功？" + isSuc);

            List<Studio> studiosList = new StudioSrv().findStudioAll();

            request.getSession().setAttribute("studiosList", studiosList);

            request.getRequestDispatcher("studio.jsp").forward(request, response);

        }

        if (method.equals("add")) {

            System.out.println("正在执行add");
            Studio studio = new Studio();
            String studioname = request.getParameter("studioname");
            int studiorowcount = Integer.parseInt(request.getParameter("studiorowcount"));
            int studiocolcount = Integer.parseInt(request.getParameter("studiocolcount"));
            String studiointroduct = request.getParameter("studiointroduct");
            int studioflag = Integer.parseInt(request.getParameter("studioflag"));
            System.out.println("正在执行add studioflag输出为" + studioflag);
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
            System.out.println("进入searchById");
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

            int currentPage = 1; // 当前页默认为第一页
            String strpage = request.getParameter("currentPage"); // 获取前台传入当前页
            if (strpage != null && !strpage.equals("")) {
                currentPage = Integer.parseInt(strpage) < 1 ? 1
                        : Integer.parseInt(strpage); // 将字符串转换成整型
            }
            String studio_name = request.getParameter("studio_name");
            StudioDAO dao = (StudioDAO) DAOFactory.createStudioDAO();
            // 从UserDAO中获取所有用户信息
            ArrayList<Studio> list = dao.findStudioByPage(currentPage);
            // 从UserDAO中获取总记录数
            int allCount = dao.getAllCount();
            // 从UserDAO中获取总页数
            int allPageCount = dao.getAllPageCount();
            // 从UserDAO中获取当前页
            currentPage = dao.getCurrentPage();

            // 存入request中
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
            System.out.print("正在执行studioupdate的select");

            StudioDAO dao = (StudioDAO) DAOFactory.createStudioDAO();

            List<Studio> studiolist = new StudioSrv().findStudioAll();
//             打印 StudioSrv().findStudioById(studioid)的信息
            request.setAttribute("studiolist", studiolist);
            System.out.println("传递搜索结果");
            request.getRequestDispatcher("studio.jsp").forward(request, response);
        }

    }
}
