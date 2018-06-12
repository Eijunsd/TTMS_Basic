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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        // …Ë÷√jsp“≥√Ê±‡¬Î
        response.setContentType("text/html;charset=UTF-8");

        String flag = request.getParameter("flag");
        System.out.println(flag);

        String empNo = null;
        String empPass = null;
//        String empHead = null;
        int empType = 0;

        try {
            empNo = request.getParameter("empNo");
            empPass = request.getParameter("empPass");
//            empHead = request.getParameter("empHead");
            System.out.println(request.getParameter("empType"));
            empType = Integer.parseInt(request.getParameter("empType"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        user.setEmpNo(empNo);
        user.setType(empType);
        user.setEmpPass(empPass);
//        user.setHeadPath(empHead);

        boolean ret = false;

        if (flag.equals("add")) {
            System.out.println("adding...");
            ret = new UserSrv().insert(user);
        } else if (flag.equals("modify")) {
            System.out.println("modifying...");
            ret = new UserSrv().update(user);
        } else if (flag.equals("delete")) {
//            System.out.println("deleting...");
//            ret = new UserSrv().delete(empNo);
        } else if (flag.equals("refresh")) {
            System.out.println("refreshing...");
        } else {

            List<User> list = new UserSrv().findUserAll();
            request.getSession().setAttribute("userlist", list);
        }
        List<User> list = new UserSrv().findUserAll();
        request.getSession().setAttribute("userlist", list);

        if (ret) {
            request.setAttribute("desc", "ok");
        } else {
            request.setAttribute("desc", "no");
        }
        System.out.println(ret);
        request.getRequestDispatcher("/staff/UserManager.jsp").forward(request, response);
    }
}
