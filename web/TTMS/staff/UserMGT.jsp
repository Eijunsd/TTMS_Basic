<%@ page import="cn.xupt.ttms.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.xupt.ttms.model.User" %>
<%@ page pageEncoding="UTF-8" isErrorPage="false" errorPage="../../error.jsp"%>
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
    <title>用户管理</title>
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

</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 用户管理 <span class="c-gray en">&gt;</span> 用户列表 </nav>

<button  class="btn btn-default" id="addBtn" data-toggle="modal" data-target="#addStaff" style="color: #8a6d3b;background: #FFFFFF;border-radius: 6px;margin-left: 20px;margin-top: 10px;margin-bottom: 20px">添加用户</button>
<form action="/userServlet?flag=searchByPage" method="post" onsubmit="highlight(this.s.value);return false;" style="float: right">
    <input name="empNo" id="s" value="${search_emp_no}" class="sou" />
    <input type="submit" id="submit" name="" value="查询" style="margin-right: 20px;font-size: 20px;background: #508675;color: #FFFFFF"/>
</form>
<div id="page1">
    <table class="layui-table" lay-skin="nob" lay-even=""  style="text-align: center;">
        <colgroup>
            <col width="150">
            <col width="200">
            <col width="150">
            <col width="150">
            <col width="200">
        </colgroup>
        <thead>
        <tr style="text-align: center">
            <td>头像</td>
            <td>工号</td>
            <td>密码</td>
            <td>职位</td>
            <td>操作</td>
        </tr>
        </thead>
        <tbody>
        <%
            int currentPage = 1;  //当前页
            int allCount = 0;     //总记录数
            int allPageCount = 0; //总页数
            //查看request中是否有currentPage信息，如没有，则说明首次访问该页
            if (request.getAttribute("allUser") != null) {
                //获取Action返回的信息
                currentPage = ((Integer)request.getAttribute("currentPage")).intValue();
                List<User> list = (List<User>)request.getAttribute("allUser");
                allCount = ((Integer)request.getAttribute("allCount")).intValue();
                allPageCount = ((Integer)request.getAttribute("allPageCount")).intValue();
                for (User u: list) {%>
        <tr>
            <td><img src="<%=u.getHeadPath()%>" width="36px" height="36px" overflow="hidden"></td>
            <td><%=u.getEmpNo()%></td>
            <td><%=u.getEmpPass()%></td>
            <td><%=u.getType()==0?"普通用户":"管理员"%></td>
            <td>
                <button type="button"  class="btn btn-success" data-toggle="modal" onclick="modifyUser(this)" data-target="#reviseStaff" style="border-radius: 5px">修改</button>
                <button type="button"  class="btn btn-danger" data-toggle="modal" onclick="deleteUser(this)" data-target="#deleteStaff" style="border-radius: 5px">删除</button>
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
                <li><a href="/userServlet?flag=searchByPage&currentPage=1&emp_name=${search_emp_no}">首页</a></li>
                <li><a href="/userServlet?flag=searchByPage&currentPage=<%=(currentPage-1)<1?1:(currentPage-1)%>&emp_name=${search_emp_no}">上一页</a></li>
                <li><a href="/userServlet?flag=searchByPage&currentPage=<%=(currentPage+1)>allPageCount?allPageCount:(currentPage+1)%>&emp_name=${search_emp_no}">下一页</a></li>
                <li><a href="/userServlet?flag=searchByPage&currentPage=<%=allPageCount%>&emp_name=${search_emp_no}">末页</a></li>
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
                    添加用户
                </h4>
            </div>
            <div class="modal-body">
                <form action="/userServlet" method="post">
                    <div><h4>工号：<input type="text" name="empNo"></h4></div>
                    <div><h4>密码：<input type="text" name="empPass"></h4></div>
                    <div><h4>职位：<input type="text" name="empType"></h4></div>
                    <div><h4><input type="hidden" name="flag" value="add"></h4></div>
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


<div class="modal fade bs-example-modal-lg" id="reviseStaff" tabindex="-1" role="dialog" aria-labelledby="reviseStaff" aria-hidden="true">
    <div class="modal-dialog" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="revise">
                    修改用户信息
                </h4>
            </div>
            <div class="modal-body">
                <form action="/userServlet" method="post">
                    <div><h4>工号：<input type="text" name="empNo" id="num"></h4></div>
                    <div><h4>密码：<input type="text" name="empPass" id="pass"></h4></div>
                    <div><h4>类型：<input type="text" name="empType" id="type"></h4></div>
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
                <form action="/userServlet" method="post">
                    <div><h4><input type="hidden" name="flag" value="delete"></h4></div>
                    <div><h4><input type="hidden" name="empNo" id="empNo"></h4></div>
                    <button type="button" class="btn btn-xs btn-default" data-dismiss="modal">取 消</button>
                    <input type="submit" class="btn btn-xs btn-danger" value="确认">
                </form>
            </div>
        </div>
    </div>
</div>
</div>

<script>
    var modifyUser = function(btn){
        var tr = btn.parentNode.parentNode;
        document.getElementById("num").value = tr.cells[1].innerHTML.trim();
        document.getElementById("pass").value = tr.cells[2].innerHTML.trim();
        document.getElementById("type").value = tr.cells[3].innerHTML.trim();
    }
    var deleteUser = function (btn) {
        var tr = btn.parentNode.parentNode;
        document.getElementById("empNo").value = tr.cells[1].innerHTML.trim();
    }
</script>

</body>
</html>