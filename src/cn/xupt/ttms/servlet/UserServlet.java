package cn.xupt.ttms.servlet;

import cn.xupt.ttms.model.User;
import cn.xupt.ttms.service.UserSrv;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "userServlet", urlPatterns = "/userServlet")
public class UserServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = new User();

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        // 设置jsp页面编码
        response.setContentType("text/html;charset=UTF-8");

        String flag = request.getParameter("flag");
        System.out.println(flag);

        String empNo = null;
        String empPass = null;
        String empType = null;

        try {
            empNo = request.getParameter("empNo");
            empPass = request.getParameter("empPass");
            empType = request.getParameter("empType");
        } catch (Exception e) {
            e.printStackTrace();
        }

        user.setEmpNo(empNo);
        user.setEmpPass(empPass);
        if (empType != null) {
            user.setType(Integer.parseInt(empType));
        }

        boolean ret = false;

        if (flag.equals("add")) {
            System.out.println("adding...");
            ret = new UserSrv().insert(user);
        } else if (flag.equals("modify")) {
            System.out.println("modifying...");
            System.out.println(empNo);
            ret = new UserSrv().update(user);
        } else if (flag.equals("delete")) {
            System.out.println("deleting...");
            ret = new UserSrv().delete(Integer.parseInt(empNo));
        } else if (flag.equals("searchByPage")) {
            System.out.println("searching...");
            searchByPage(request, response, 1);
            return;
        }

//        List<User> list = new UserSrv().findUserAll();
//        request.getSession().setAttribute("userlist", list);

        if (ret) {
            request.setAttribute("desc", "ok");
            searchByPage(request, response, 0);
        } else {
            request.setAttribute("desc", "no");
            request.getRequestDispatcher("/TTMS/staff/UserMGT.jsp").forward(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void searchByPage(HttpServletRequest request, HttpServletResponse response, int flag) {
        int currentPage = 1; // 当前页默认为第一页
        String empNo = "";

        String strpage = request.getParameter("currentPage"); // 获取前台传入当前页
        if (strpage != null && !strpage.equals("")) {
            currentPage = Integer.parseInt(strpage) < 1 ? 1 : Integer.parseInt(strpage); // 将字符串转换成整型
        }

        if (flag != 0) {
            empNo = request.getParameter("empNo");
        }

        System.out.println(empNo);
        // 从UserDAO中获取所有用户信息
        List<User> list = new UserSrv().findUserByPage(currentPage, empNo);
        // 从UserDAO中获取总记录数
        int allCount = new UserSrv().getAllCount();
        // 从UserDAO中获取总页数
        int allPageCount = new UserSrv().getAllPageCount();
        // 从UserDAO中获取当前页
        currentPage = new UserSrv().getCurrentPage();

        // 存入request中
        request.setAttribute("allUser", list);
        request.setAttribute("allCount", allCount);
        request.setAttribute("allPageCount", allPageCount);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("search_emp_no", empNo);

        request.setAttribute("desc", "ok");
        try {
            request.getRequestDispatcher("/TTMS/staff/UserMGT.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
