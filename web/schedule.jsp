<%@ page import="cn.xupt.ttms.model.Schedule" %>
<%@ page import="java.util.List" %>
<%@ page pageEncoding="UTF-8" isErrorPage="false" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../../layui-v2.1.4/layui-v2.1.4/layui/css/layui.css" media="all">
    <link rel="stylesheet" type="text/css" href="../../static/h-ui/css/H-ui.min.css"/>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../../static/h-ui.admin/css/H-ui.admin.css"/>
    <link rel="stylesheet" type="text/css" href="../../lib/Hui-iconfont/1.0.8/iconfont.css"/>
    <link rel="stylesheet" type="text/css" href="../../css/bootstrap.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>演出计划</title>
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
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 演出计划 <span
        class="c-gray en">&gt;</span> 演出计划列表 <a class="btn btn-success radius r"
                                                style="line-height:1.6em;margin-top:3px"
                                                href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>

<button class="btn btn-default" id="addBtn" data-toggle="modal" data-target="#addStaff"
        style="color: #8a6d3b;background: #FFFFFF;border-radius: 6px;margin-left: 20px;margin-top: 10px;margin-bottom: 20px">
    添加演出计划
</button>
<form action="/TTMS/scheduleServlet">
    <button name="flag" value="searchAll" class="btn">搜索</button>
</form>
<form action="/TTMS/scheduleServlet?flag=searchByPage" method="post" onsubmit="highlight(this.s.value);return false;"
      style="float: right">
    <input name="flag" id="${search_schedId}" class="sou"/>
    <%--<input id="" class="btn btn-default" >--%>
    <input type="submit" id="submit" value="搜索"
           style="margin-right: 20px;font-size: 20px;background: #508675;color: #FFFFFF"/>
</form>
<form action="/TTMS/scheduleServlet">
    <div id="page1">
        <table class="layui-table" lay-skin="nob" lay-even="" style="text-align: center;">
            <colgroup>
                <col width="150">
                <col width="200">
                <col width="200">
                <col width="200">
                <col width="150">
            </colgroup>
            <thead>
            <tr style="text-align: center">
                <td>演出计划编号</td>
                <td>演出厅编号</td>
                <td>剧目编号</td>
                <td>放映时间</td>
                <td>售价</td>
                <td>操作</td>
            </tr>
            </thead>
            <tbody>
            <%
                int currentPage = 1;  //当前页
                int allCount = 0;     //总记录数
                int allPageCount = 0; //总页数
                //查看request中是否有currentPage信息，如没有，则说明首次访问该页
                if (request.getAttribute("allSchedule") != null) {
                    //获取Action返回的信息
                    currentPage = ((Integer) request.getAttribute("currentPage")).intValue();
                    List<Schedule> list = (List<Schedule>) request.getAttribute("allSchedule");
                    allCount = ((Integer) request.getAttribute("allCount")).intValue();
                    allPageCount = ((Integer) request.getAttribute("allPageCount")).intValue();
                    System.out.println("curr:" + currentPage);
                    for (Schedule schedule : list) {%>
            <tr>
                <td style="text-align: center;"><%=schedule.getSchedId()%>
                </td>
                <td style="text-align: center;"><%=schedule.getStudioId()%>
                </td>
                <td style="text-align: center;"><%=schedule.getPlayId()%>
                </td>
                <td style="text-align: center;"><%=schedule.getSchedTime()%>
                </td>
                <td style="text-align: center;"><%=schedule.getSchedTicketPrice()%>
                </td>
                <td style="text-align: center;">
                    <button type="button" class="btn btn-success" data-toggle="modal" onclick="searchSched(this)"
                            data-target="#reviseStaff" style="border-radius: 5px">修改
                    </button>
                    <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteSched" onclick="deleteSched(this)"
                            data-target="#deleteStaff" style="border-radius: 5px">删除
                    </button>
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
                    <li>
                        <a href="/TTMS/scheduleServlet?flag=searchByPage&currentPage=1&search_schedId=${search_schedId}">首页</a>
                    </li>
                    <li>
                        <a href="/TTMS/scheduleServlet?flag=searchByPage&currentPage=<%=(currentPage-1)<1?1:(currentPage-1)%>&search_schedId=${search_schedId}">上一页</a>
                    </li>
                    <li>
                        <a href="/TTMS/scheduleServlet?flag=searchByPage&currentPage=<%=(currentPage+1)>allPageCount?allPageCount:(currentPage+1)%>&search_schedId=${search_schedId}">下一页</a>
                    </li>
                    <li>
                        <a href="/TTMS/scheduleServlet?flag=searchByPage&currentPage=<%=allPageCount%>&search_schedId=${search_schedId}">末页</a>
                    </li>
                </ul>
            </nav>
        </div>

    </div>

</form>
<!--添加员工-->
<div class="modal fade" id="addStaff" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel" style="text-align: center;font-size: 25px">
                    添加演出计划
                </h4>
            </div>
            <div class="modal-body">
                <form action="/TTMS/scheduleServlet" method="post">
                    <div><h4>演出计划编号：<input type="text" name="SchedId"></h4></div>
                    <div><h4>&#12288演出厅编号：<input type="text" name="StudioId"></h4></div>
                    <div><h4>&#12288&#12288剧目编号：<input type="text" name="PlayId"></h4></div>
                    <div><h4>&#12288&#12288放映时间：<input type="text" name="SchedTime"></h4></div>
                    <div><h4>&#12288&#12288&#12288&#12288售价：<input type="text" name="SchedTicketPrice"></h4></div>
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
<div class="modal fade bs-example-modal-lg" id="reviseStaff" tabindex="-1" role="dialog" aria-labelledby="reviseStaff"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="revise">
                    修改演出计划
                </h4>
            </div>
            <div class="modal-body">
                <form action="/TTMS/scheduleServlet" method="post">
                    <div><h4>演出计划编号：<input type="text" id="SchedId" name="SchedId"></h4></div>
                    <div><h4>&#12288演出厅编号：<input type="text" id="StudioId" name="StudioId"></h4></div>
                    <div><h4>&#12288&#12288剧目编号：<input type="text" id="PlayId" name="PlayId"></h4></div>
                    <div><h4>&#12288&#12288放映时间：<input type="text" id="SchedTime" name="SchedTime"></h4></div>
                    <div><h4>&#12288&#12288&#12288&#12288售价：<input type="text" id="SchedTicketPrice"
                                                                   name="SchedTicketPrice"></h4></div>
                    <div><h4><input type="hidden" name="flag" value="modify"></h4></div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消
                        </button>
                        <%--<input type="submit" class="btn btn-primary" value="保存">--%>
                        <button type="submit" class="btn btn-primary" 保存>
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<!--弹出删除警告窗口-->
<div class="modal fade" id="deleteSched" role="dialog" aria-labelledby="gridSystemModalLabel">
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
                <%--<button type="button" class="btn btn-xs btn-white" data-dismiss="modal">取 消</button>--%>
                <%--<button type="button" class="btn btn-xs btn-danger">保 存</button>--%>
                <form action="/TTMS/scheduleServlet" method="post">
                    <div><h4><input type="hidden" name="flag" value="delete"></h4></div>
                    <div><h4><input type="hidden" name="SchedId" id="SchedIdde"></h4></div>
                    <button type="button" class="btn btn-xs btn-default" data-dismiss="modal">取 消</button>
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
    var searchSched = function (btn) {
        var tr = btn.parentNode.parentNode;
        var td1 = tr.cells[0];
        document.getElementById("SchedId").value = tr.cells[0].innerHTML;
        document.getElementById("StudioId").value = tr.cells[1].innerHTML;
        document.getElementById("PlayId").value = tr.cells[2].innerHTML;
        document.getElementById("SchedTime").value = tr.cells[3].innerHTML;
        document.getElementById("SchedTicketPrice").value = tr.cells[4].innerHTML;
    }
    var deleteSched = function (btn) {
        var tr = btn.parentNode.parentNode;
//        alert(tr.cells[0].innerHTML);
        document.getElementById("SchedIdde").value = tr.cells[0].innerHTML;
    }
</script>

</body>
</html>