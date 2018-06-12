<%@ page import="cn.xupt.ttms.model.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <%--<meta http-equiv="refresh" content="0;url="/>--%>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../layui-v2.1.4/layui-v2.1.4/layui/css/layui.css" media="all">
    <link rel="stylesheet" type="text/css" href="../static/h-ui/css/H-ui.min.css"/>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../static/h-ui.admin/css/H-ui.admin.css"/>
    <link rel="stylesheet" type="text/css" href="../lib/Hui-iconfont/1.0.8/iconfont.css"/>
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>用户管理</title>
    <style>
        .modal-dialog {
            width: 500px;
            text-align: center;
        }

        input {
            border-radius: 5px;
        }
    </style>

</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 用户管理 <span
        class="c-gray en">&gt;</span> 用户列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"
                                              title="刷新"><i class="Hui-iconfont">&#xe68f;</i></a></nav>

<button class="btn btn-default" id="addBtn" data-toggle="modal" data-target="#addStaff"
        style="color: #8a6d3b;background: #FFFFFF;border-radius: 6px;margin-left: 20px;margin-top: 10px;margin-bottom: 20px">
    添加用户
</button>
<%--<form action="/userServlet?" method="post">--%>
<%--<button class="btn btn-primary" type="submit" name="flag" value="findAll">查询</button>--%>
<%--</form>--%>
<form onsubmit="highlight(this.s.value);return false;" action="/userServlet?" style="float: right">
    <input name="s" id="s" class="sou"/>
    <input type="submit" name="flag" id="flag" value="搜索"
           style="margin-right: 20px;font-size: 20px;background: #508675;color: #FFFFFF"/>
</form>
<div id="page1">
    <table class="layui-table" id="myTable" lay-skin="nob" lay-even="" style="text-align: center;">
        <colgroup>
            <col width="150">
            <col width="200">
            <col width="150">
            <col width="150">
            <col width="200">
        </colgroup>
        <thead>
        <tr style="text-align: center">
            <td>账号</td>
            <td>密码</td>
            <td>类型</td>
            <td>操作</td>
        </tr>
        </thead>
        <tbody>
        <%
            int count = 0;
            String info = null;
            List<User> list = (List<User>) session.getAttribute("userlist");
            if (list != null) {
                for (User u : list) {
                    count++;
                    info = "<tr>\n" +
                            "        <td>" + u.getEmpNo() + "</td>\n" +
                            "        <td>" + u.getEmpPass() + "</td>\n" +
                            "        <td>" + u.getType() + "</td>\n" +
                            "        <td>\n" +
                            "            <button type=\"button\"  class=\"btn btn-success\" data-toggle=\"modal\" onclick=\"searchStaff(this,count)\" data-target=\"#reviseStaff\" style=\"border-radius: 5px\">修改</button>\n" +
                            "            <button type=\"button\"  class=\"btn btn-danger\" data-toggle=\"modal\" data-target=\"#deleteStaff\" style=\"border-radius: 5px\">删除</button>\n" +
                            "        </td>\n" +
                            "    </tr>";
                    out.println(info);
                }
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
                    添加用户
                </h4>
            </div>
            <div class="modal-body">
                <form action="/userServlet" method="post">
                    <div><h4>账号：<input type="text" name="empNo"></h4></div>
                    <div><h4>密码：<input type="text" name="empPass"></h4></div>
                    <div><h4>类型：<input type="text" name="empType"></h4></div>
                    <div><h4><input type="hidden" name="flag" value="add"></h4></div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消
                        </button>
                        <input type="submit" class="btn btn-primary" value="确定">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<div class="modal fade bs-example-modal-lg" id="reviseStaff" tabindex="-1" role="dialog" aria-labelledby="reviseStaff"
     aria-hidden="true">
    <div class="modal-dialog">
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
                    <div><h4>账号：<input type="text" id="empNo" name="empNo"></h4></div>
                    <div><h4>密码：<input type="text" id="empPass" name="empPass"></h4></div>
                    <div><h4>类型：<input type="text" id="empType" name="empType"></h4></div>
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


<!--弹出警告窗口-->
<div class="modal fade" id="deleteStaff" role="dialog" aria-labelledby="gridSystemModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
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
                    <div><h4><input type="hidden" name="empNo" value=""></h4></div>
                    <button type="button" class="btn btn-xs btn-white" data-dismiss="modal">取 消</button>
                    <input type="submit" class="btn btn-xs btn-danger" value="确认">
                </form>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
</div>

<script>
    var searchStaff = function (btn,count) {

        alert(count);
        var tr = btn.parentNode;
        alert(document.getElementById("myTable").rows[1].cells[1].innerHTML);
//        var td1 = tr.cells[0];
        document.getElementById("empNo").value = tr.cells[0].innerHTML;
        document.getElementById("empPass").value = tr.cells[1].innerHTML;
        document.getElementById("empType").value = tr.cells[2].innerHTML;

//        document.getElementById("empNo").value = document.getElementById("myTable").rows[0].cells[1].innerHTML;
//        document.getElementById("empPass").value = document.getElementById("myTable").rows[1].cells[1].innerHTML;
//        document.getElementById("empType").value = document.getElementById("myTable").rows[2].cells[1].innerHTML;
    }

    function checkNum() {
        var reg = /^[0-9]{2,10}/;
        var num = $(".name");
        if (!reg.test(name.val())) {

            $(".name+p").html("<img src='../images/falseCheck.png'>");
            num.focus();
            return false;
        }
        else {
//            alert("yes");
            $(".name+p").html("<img src='../images/trueCheck.png'>");
            return true;
        }
    }
</script>

</body>
</html>