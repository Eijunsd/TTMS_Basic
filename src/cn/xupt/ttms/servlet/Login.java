package cn.xupt.ttms.servlet;

import cn.xupt.ttms.model.Employee;
import cn.xupt.ttms.model.User;
import cn.xupt.ttms.service.EmployeeSrv;
import cn.xupt.ttms.service.UserSrv;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/Login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        List<User> userList = new UserSrv().findUserAll();
        List<Employee> employeeList = new EmployeeSrv().getEmployeeAll();

        //���ñ���
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String user = null;
        String pass = null;
        String emp_type = null;
        String head_path = null;
        String result = "no";
        int type = 0;
        String IsLogin = "yes";

        Employee emp = null;

        if (null == username || null == password) {
            result = "";
        } else if (username != null) {
            for (User u : userList) {
                if (u.getEmpNo().equals(username)) {
                    System.out.println("�����û�");
                    user = u.getEmpNo();
                    pass = u.getEmpPass();
                    type = u.getType();
                    if (type == 1) {
                        emp_type = "����Ա";
                    } else {
                        emp_type = "��ͨ�û�";
                    }
                    head_path = u.getHeadPath();
                    emp = new EmployeeSrv().findByEmpNo(user);
                    result = "yes";
                }
            }
        }

        if (password == null || password == "") {
            out.write(String.valueOf(IsLogin));
            out.close();
            out.flush();
        }

        if ((username != null) && username.equals(user) && (password != null) && password.equals(pass)) {
            //Я��ת������
            request.getSession().setAttribute("loginflag", IsLogin);
            request.getSession().setAttribute("employeeList", employeeList);
            request.getSession().setAttribute("userList", userList);
            request.setAttribute("empNo", username);
            request.setAttribute("password", password);
            request.setAttribute("empType", emp_type);
            request.setAttribute("headPath", head_path);
            request.setAttribute("emp", emp);
            if (type == 1) {
                request.getSession().setAttribute("auth", "ok");
            } else {
                request.getSession().setAttribute("auth", "no");
            }
            request.getRequestDispatcher("home.jsp").forward(request, response);
        } else {
            request.setAttribute("desc", "�û������������");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
