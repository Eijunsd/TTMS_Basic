<%@ page pageEncoding="UTF-8" errorPage="../../error.jsp"%>
<%@ page import="cn.xupt.ttms.model.Employee" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.xupt.ttms.model.Employee" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../../layui-v2.1.4/layui-v2.1.4/layui/css/layui.css" media="all">
    <link rel="stylesheet" type="text/css" href="../../static/h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../../static/h-ui.admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="../../lib/Hui-iconfont/1.0.8/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="../../css/bootstrap.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>员工管理</title>
    <style>
        .modal-dialog
        {
            width: 500px;text-align: center;
        }
        input
        {
            border-radius: 5px;
        }
    </style>
    <script type="text/javascript">
        function check_blank() {
            var blankName = document.getElementById("init");
            if (blankName.checked) {
                blankName.value = "yes";
            }
            return true;
        }
    </script>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 员工管理 <span class="c-gray en">&gt;</span> 员工列表</nav>

<button  class="btn btn-default" id="addBtn" data-toggle="modal" data-target="#addStaff" style="color: #8a6d3b;background: #FFFFFF;border-radius: 6px;margin-left: 20px;margin-top: 10px;margin-bottom: 20px">添加员工</button>
<form action="/TTMS/employee/EmployeeServlet?flag=searchByPage" method="post" onsubmit="highlight(this.s.value);return false;" style="float: right">
    <input name="empNo" id="s" value="${search_emp_no}" class="sou" />
    <input type="submit" id="submit" value="查询" style="margin-right: 20px;font-size: 20px;background: #508675;color: #FFFFFF"/>
</form>
<div id="page1"><table class="layui-table" lay-skin="nob" lay-even=""  style="text-align: center;">
    <colgroup>
        <col width="150">
        <col width="200">
        <col width="150">
        <col width="150">
        <col width="200">
    </colgroup>
    <thead>
    <tr style="text-align: center">
        <td>工号</td>
        <td>姓名</td>
        <td>电话</td>
        <td>地址</td>
        <td>邮箱</td>
        <td>操作</td>
    </tr>
    </thead>
    <tbody>
    <%
        int currentPage = 1;  //当前页
        int allCount = 0;     //总记录数
        int allPageCount = 0; //总页数
        //查看request中是否有currentPage信息，如没有，则说明首次访问该页
        if (request.getAttribute("allEmployee") != null) {
            //获取Action返回的信息
            currentPage = ((Integer)request.getAttribute("currentPage")).intValue();
            List<Employee> list = (List<Employee>)request.getAttribute("allEmployee");
            allCount = ((Integer)request.getAttribute("allCount")).intValue();
            allPageCount = ((Integer)request.getAttribute("allPageCount")).intValue();
            System.out.println("curr:" + currentPage);
            for (Employee emp: list) {%>
    <tr>
        <td><%=emp.getEmpNo()%></td>
        <td><%=emp.getEmpName()%></td>
        <td><%=emp.getEmpTelNum()%></td>
        <td><%=emp.getEmpAddr()%></td>
        <td><%=emp.getEmpEmail()%></td>
        <td>
            <button type="button"  class="btn btn-success" data-toggle="modal" onclick="modifyStaff(this)" data-target="#reviseStaff" style="border-radius: 5px">修改</button>
            <button type="button"  class="btn btn-danger" data-toggle="modal" onclick="deleteStaff(this)" data-target="#deleteStaff" style="border-radius: 5px">删除</button>
        </td>
    </tr>
    <%
            }
        }
    %>
    </tbody>
</table>
    <div class="text-center">
        <nav>
            <ul class="pagination">
                <li><a href="/TTMS/employee/EmployeeServlet?flag=searchByPage&currentPage=1&emp_name=${search_emp_no}">首页</a></li>
                <li><a href="/TTMS/employee/EmployeeServlet?flag=searchByPage&currentPage=<%=(currentPage-1)<1?1:(currentPage-1)%>&emp_name=${search_emp_no}">上一页</a></li>
                <li><a href="/TTMS/employee/EmployeeServlet?flag=searchByPage&currentPage=<%=(currentPage+1)>allPageCount?allPageCount:(currentPage+1)%>&emp_name=${search_emp_no}">下一页</a></li>
                <li><a href="/TTMS/employee/EmployeeServlet?flag=searchByPage&currentPage=<%=allPageCount%>&emp_name=${search_emp_no}">末页</a></li>
            </ul>
        </nav>
    </div>
</div>


<!--添加员工-->
<div class="modal fade" id="addStaff" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel" style="text-align: center;font-size: 25px">
                    添加员工
                </h4>
            </div>
            <div class="modal-body">
                <form action="/TTMS/employee/EmployeeServlet" method="post">
                    <div><h4>工号：<input type="text" name="empNo"></h4></div>
                    <div><h4>姓名：<input type="text" name="empName"></h4></div>
                    <div><h4>电话：<input type="text" name="empTel"></h4></div>
                    <div><h4>地址：<input type="text" name="empAddr"></h4></div>
                    <div><h4>邮箱：<input type="text" name="empEmail"></h4></div>
                    <div><h4><input type="checkbox" name="empInit" id="init" value="no">初始化帐号密码</h4></div>
                    <div><h4><input type="hidden" name="flag" value="add"></h4></div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消
                        </button>
                        <input type="submit" class="btn btn-primary" onclick="return check_blank()" value="保存">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<!--修改演出厅-->
<div class="modal fade bs-example-modal-lg" id="reviseStaff" tabindex="-1" role="dialog" aria-labelledby="reviseStaff" aria-hidden="true">
    <div class="modal-dialog" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="revise">
                    修改员工信息
                </h4>
            </div>
            <div class="modal-body">
                <form action="/TTMS/employee/EmployeeServlet" method="post">
                    <div><h4>工号：<input type="text" id="num" name="empNo"></h4></div>
                    <div><h4>姓名：<input type="text" id="worker" name="empName"></h4></div>
                    <div><h4>电话：<input type="text" id="name" name="empTel"></h4></div>
                    <div><h4>地址：<input type="text" id="tel" name="empAddr"></h4></div>
                    <div><h4>邮箱：<input type="text" id="address" name="empEmail"></h4></div>
                    <div><h4><input type="hidden" name="flag" value="modify"></h4></div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消
                        </button>
                        <input type="submit" class="btn btn-primary" value="保存">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!--弹出删除警告窗口-->
<div class="modal fade" id="deleteStaff" role="dialog" aria-labelledby="gridSystemModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="gridSystemModalLabel">提示</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    确定要删除该资源？删除后不可恢复！
                </div>
            </div>
            <div class="modal-footer">
                <form action="/TTMS/employee/EmployeeServlet" method="post">
                    <div><h4><input type="hidden" name="flag" value="delete"></h4></div>
                    <div><h4><input type="hidden" id="empNo" name="empNo"></h4></div>
                    <button type="button" class="btn btn-xs btn-default" data-dismiss="modal">取 消</button>
                    <input type="submit" class="btn btn-xs btn-danger" value="确认">
                </form>
            </div>
        </div>
    </div>
</div>
</div>

<script>
    var modifyStaff = function(btn){
        var tr = btn.parentNode.parentNode;
        var td1 = tr.cells[0];
        document.getElementById("num").value = tr.cells[0].innerHTML;
        document.getElementById("worker").value = tr.cells[1].innerHTML;
        document.getElementById("name").value = tr.cells[2].innerHTML;
        document.getElementById("tel").value = tr.cells[3].innerHTML;
        document.getElementById("address").value = tr.cells[4].innerHTML;
    }
    var deleteStaff = function (btn) {
        var tr = btn.parentNode.parentNode;
        document.getElementById("empNo").value = tr.cells[0].innerHTML;
    }
</script>

</body>
</html>