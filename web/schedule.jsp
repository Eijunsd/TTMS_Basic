<%@ page import="cn.xupt.ttms.model.Employee" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.xupt.ttms.model.Employee" %>
<%@ page pageEncoding="UTF-8" isErrorPage="false" errorPage="error.jsp"%>
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
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 员工管理 <span class="c-gray en">&gt;</span> 员工列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>

<button  class="btn btn-default" id="addBtn" data-toggle="modal" data-target="#addStaff" style="color: #8a6d3b;background: #FFFFFF;border-radius: 6px;margin-left: 20px;margin-top: 10px;margin-bottom: 20px">添加员工</button>
<form onsubmit="highlight(this.s.value);return false;" style="float: right">
    <input name="s" id="s" class="sou" />
    <input type="submit" id="submit" value="搜索" style="margin-right: 20px;font-size: 20px;background: #508675;color: #FFFFFF"/>
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
        String info;
        for (Employee emp: (List<Employee>)session.getAttribute("emplist")) {
            info = "<tr>\n" +
                    "        <td>"+ emp.getEmpNo() +"</td>\n" +
                    "        <td>" + emp.() + "</td>\n" +
                    "        <td>" + emp.getEmp_tel_num() + "</td>\n" +
                    "        <td>" + emp.getEmp_addr() +"</td>\n" +
                    "        <td>" + emp.getEmp_email() + "</td>\n" +
                    "        <td>\n" +
                    "            <button type=\"button\"  class=\"btn btn-success\" data-toggle=\"modal\" onclick=\"searchStaff(this)\" data-target=\"#reviseStaff\" style=\"border-radius: 5px\">修改</button>\n" +
                    "            <button type=\"button\"  class=\"btn btn-danger\" data-toggle=\"modal\" data-target=\"#deleteStaff\" style=\"border-radius: 5px\">删除</button>\n" +
                    "        </td>\n" +
                    "    </tr>";
            out.println(info);
        }
    %>
    </tbody>
</table>
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
                <form action="Employee" method="post">
                    <div><h4>工号：<input type="text" name="empNo"></h4></div>
                    <div><h4>姓名：<input type="text" name="empName"></h4></div>
                    <div><h4>电话：<input type="text" name="empTel"></h4></div>
                    <div><h4>地址：<input type="text" name="empAddr"></h4></div>
                    <div><h4>邮箱：<input type="text" name="empEmail"></h4></div>
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
                <form action="Employee" method="post">
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
                <button type="button" class="btn btn-xs btn-white" data-dismiss="modal">取 消</button>
                <button type="button" class="btn btn-xs btn-danger">保 存</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
</div>

<script>
    var searchStaff = function(btn){
//            alert(123);
        var tr = btn.parentNode.parentNode;
        var td1 = tr.cells[0];
        document.getElementById("num").value = tr.cells[0].innerHTML;
        document.getElementById("worker").value = tr.cells[1].innerHTML;
        document.getElementById("name").value = tr.cells[2].innerHTML;
        document.getElementById("tel").value = tr.cells[3].innerHTML;
        document.getElementById("address").value = tr.cells[4].innerHTML;
    }
</script>

</body>
</h