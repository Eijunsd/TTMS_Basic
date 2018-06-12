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
import java.util.List;

@WebServlet(name = "EmployeeServlet", urlPatterns = "/TTMS/employee/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Employee employee = new Employee();
        String init = request.getParameter("empInit");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        // ����jspҳ�����
        response.setContentType("text/html;charset=UTF-8");


        String flag = request.getParameter("flag");
        System.out.println(flag);

        String empNo = null;
        String empName = null;
        String empTel = null;
        String empAddr = null;
        String empEmail = null;

        try {
            empNo = request.getParameter("empNo");
            empName = request.getParameter("empName");
            empTel = request.getParameter("empTel");
            empAddr = request.getParameter("empAddr");
            empEmail = request.getParameter("empEmail");
        } catch (Exception e) {
            e.printStackTrace();
        }

        employee.setEmpNo(empNo);
        employee.setEmpName(empName);
        employee.setEmpTelNum(empTel);
        employee.setEmpAddr(empAddr);
        employee.setEmpEmail(empEmail);

        boolean ret = false;
        User user = null;
        if (flag.equals("add")) {
            System.out.println("adding...");
            if (init.equals("yes")) {
                ret = new EmployeeSrv().insert(employee);
                user = new User();
                user.setEmpNo(empNo);
                user.setEmpPass("123456");
                user.setHeadPath("../../images/head/default.jpg");
                ret = new UserSrv().insert(user);
            } else {
                ret = new EmployeeSrv().insert(employee);
            }
        } else if (flag.equals("modify")) {
            System.out.println("modifying...");
            ret = new EmployeeSrv().update(employee);
        } else if (flag.equals("delete")) {
            System.out.println("deleting...");
            ret = new EmployeeSrv().delete(empNo);
        } else if (flag.equals("searchByPage")) {
            System.out.println("searching...");
            searchByPage(request, response, 1);
            return;
        }

        if (ret) {
            request.setAttribute("desc", "ok");
            searchByPage(request, response, 0);
        } else {
            request.setAttribute("desc", "no");
            request.getRequestDispatcher("/TTMS/staff/EmpMGT.jsp").forward(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void searchByPage(HttpServletRequest request, HttpServletResponse response, int flag) {
        int currentPage = 1; // ��ǰҳĬ��Ϊ��һҳ
        String empNo = "";

        String strpage = request.getParameter("currentPage"); // ��ȡǰ̨���뵱ǰҳ
        if (strpage != null && !strpage.equals("")) {
            currentPage = Integer.parseInt(strpage) < 1 ? 1 : Integer.parseInt(strpage); // ���ַ���ת��������
        }

        if (flag != 0) {
            empNo = request.getParameter("empNo");
        }

        System.out.println(empNo);
        // ��UserDAO�л�ȡ�����û���Ϣ
        List<Employee> list = new EmployeeSrv().findEmployeeByPage(currentPage, empNo);
        // ��UserDAO�л�ȡ�ܼ�¼��
        int allCount = new EmployeeSrv().getAllCount();
        // ��UserDAO�л�ȡ��ҳ��
        int allPageCount = new EmployeeSrv().getAllPageCount();
        // ��UserDAO�л�ȡ��ǰҳ
        currentPage = new EmployeeSrv().getCurrentPage();

        // ����request��
        request.setAttribute("allEmployee", list);
        request.setAttribute("allCount", allCount);
        request.setAttribute("allPageCount", allPageCount);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("search_emp_no", empNo);

        request.setAttribute("desc", "ok");
        try {
            request.getRequestDispatcher("/TTMS/staff/EmpMGT.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
