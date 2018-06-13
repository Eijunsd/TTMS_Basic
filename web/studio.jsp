<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="cn.xupt.ttms.model.Studio" %>
<%@ page import="java.util.ArrayList" %><%--suppress ALL --%>
<%--suppress ALL --%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <%--<meta http-equiv="refresh" content="0;url=/studioServlet?method=searchByPage"/>--%>
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
    <title>演出厅</title>
    <style>
        .modal-dialog {
            width: 500px;
        }

        img {
            width: 20px;
            height: 20px;
        }

        input {
            border-radius: 5px;
        }

        /*.yanzheng {*/
        /*margin-right: 10px;*/
        /*}*/
        .modal-header {
            text-align: center;
        }

        .modal-body {
            margin-left: 100px;
        }
    </style>

</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 演出厅管理 <span
        class="c-gray en">&gt;</span> 所有演出厅 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"
                                               href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>

<button class="btn btn-default" id="addBtn" data-toggle="modal" data-target="#addAudio"
        style="color: #8a6d3b;background: #FFFFFF;border-radius: 6px;margin-left: 20px;margin-top: 10px;margin-bottom: 20px">
    添加演出厅
</button>
<button class="layui-btn-primary">
    <form action="/studioServlet?method=searchByPage" method="post">
        <input type="submit" class="btn btn-pri" value="查询所有"/>
    </form>
</button>

<form onsubmit="highlight(this.s.value);return false;" style="float: right">
    <input name="s" id="s" class="sou"/>
    <input type="submit" id="submit" value="搜索"
           style="margin-right: 20px;font-size: 20px;background: #508675;color: #FFFFFF"/>
</form>

<form action="/studioServlet" method="get">
    <div id="page1">
        <table class="layui-table" lay-skin="nob" lay-even="" style="text-align: center;">
            <colgroup>
                <col width="150">
                <col width="200">
                <col width="150">
                <col width="150">
                <col width="200">
            </colgroup>
            <thead>
            <tr style="text-align: center">
                <td>名称</td>
                <td>行数</td>
                <td>列数</td>
                <td>状态</td>
                <td>操作</td>
            </tr>
            </thead>

            <%--//填充数据--%>
            <%
                int currentPage = 1;  //当前页
                int allCount = 0;     //总记录数
                int allPageCount = 0; //总页数
                Studio Studio = null;
                //查看request中是否有currentPage信息，如没有，则说明首次访问该页
                if (request.getAttribute("allStudio") != null) {
                    //获取Action返回的信息
                    currentPage = ((Integer) request.getAttribute("currentPage")).intValue();
                    ArrayList<Studio> studioList = (ArrayList<Studio>) request.getAttribute("allStudio");
                    allCount = ((Integer) request.getAttribute("allCount")).intValue();
                    allPageCount = ((Integer) request.getAttribute("allPageCount")).intValue();


                    if (studioList != null && studioList.size() > 0) {

                        for (int i = 0; i < studioList.size(); i++) {
            %>
            <tr>
                <td hidden style="text-align: center">
                    <%=studioList.get(i).getStudioId()%>
                </td>
                <td style="text-align: center">
                    <%=studioList.get(i).getStudioName()%>
                </td>
                <td style="text-align: center">
                    <%=studioList.get(i).getStudioRowCount()%>
                </td>
                <td style="text-align: center">
                    <%=studioList.get(i).getStudioColCount()%>
                </td>
                <td style="text-align: center">
                    <%=studioList.get(i).getStudioFlag()%>
                </td>
                <td style="text-align: center">
                    <button type="button" class="btn btn-success" data-toggle="modal" data-target="#reviseAudio"
                            onclick="searchAudio(this)" style="border-radius: 5px">修改
                    </button>
                    <button type="button" class="btn btn-danger" data-target="#deleteSource" style="border-radius: 5px">
                        <a href="/studioServlet?method=studioDelete&studioid=<%=studioList.get(i).getStudioId()%>">删除</a>
                    </button>

                </td>
                <%
                    }
                %>
            </tr>
            <%
                }
            %>
            </tbody>
            <%
                }
            %>
        </table>
    </div>
</form>

<!--添加演出厅-->
<form action="/studioServlet" method="get">
    <div class="modal fade bs-example-modal-lg" id="addAudio" tabindex="-1" role="dialog" aria-labelledby="addAudio"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        添加演出厅
                    </h4>
                </div>
                <div class="modal-body">
                    <%--<h4>编号：<input type="text" class="number" placeholder="输入2-10位数字" onblur="checkNum()">--%>
                    <%--<p style="display: inline;"></p></h4>--%>
                    <h4>名称：<input name="studioname" type="text" class="name" placeholder="输入中文" onblur="checkName()">
                        <p style="display: inline;"></p></h4>
                    <h4>行数：<input name="studiorow" type="text" class="hang" placeholder="输入1到20的整数" onblur="checkRow()">
                        <p style="display: inline;"></p></h4>
                    <h4>列数：<input name="studiocol" type="text" class="col" placeholder="输入1到20的整数" onblur="checkCol()">
                        <p style="display: inline;"></p></h4>
                    <h4>状态：<input name="studiostatus" type="text" class="status" placeholder="1为已安排，0为未安排"
                                  onblur="checkStatus()">
                        <p style="display: inline;"></p></h4>
                </div>


                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                    </button>
                    <input type="hidden" name="method" value="add">
                    <button type="submit" class="btn btn-primary" onblur="checkAll()">
                        保存
                    </button>
                </div>
            </div>

        </div>
    </div>
</form>

<!--修改演出厅-->
<form action="/studioServlet" method="get">
    <div class="modal fade bs-example-modal-lg" id="reviseAudio" tabindex="-1" role="dialog" aria-labelledby="addAudio"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="revise">
                        修改演出厅
                    </h4>
                </div>
                <div class="modal-body">
                    <div>
                        <h4>名称：<input type="text" id="name" name="name">
                            <p style="display: inline"></p></h4>
                    </div>
                    <div>
                        <h4>行数：<input type="text" id="row" name="row">
                            <p style="display: inline"></p></h4>
                    </div>
                    <div>
                        <h4>列数：<input type="text" id="col" name="col">
                            <p style="display: inline"></p></h4>
                    </div>
                    <div>
                        <h4>状态：<input type="text" id="status" name="status">
                            <p style="display: inline"></p></h4>
                    </div>
                    <input type="hidden" name="method" value="studioupdate">
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                    </button>
                    <input type="hidden" name="method" value="studioupdate">
                    <button type="submit" class="btn btn-primary" data-dismiss="modal">
                        保存
                    </button>
                </div>
            </div>

        </div>
    </div>

</form>


<!--弹出删除警告窗口-->
<form action="/studioServlet" method="get">
    <div class="modal fade" id="deleteSource" role="dialog" aria-labelledby="gridSystemModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="gridSystemModalLabel">警告</h4>
                </div>
                <div class="modal-body">
                    <div class="container-fluid">
                        确定要删除该资源？删除后不可恢复！
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-xs btn-white" data-dismiss="modal">取 消</button>
                    <button type="submit" class="btn btn-xs btn-danger">确 认</button>

                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
    <!-- /.modal -->
    </div>
</form>

<script src="/jquery/jquery-1.11.1.min.js"></script>
<script>
    var searchAudio = function (btn) {
        var tr = btn.parentNode.parentNode;
        var td1 = tr.cells[0];
        document.getElementById("name").value = tr.cells[1].innerHTML.trim();
        document.getElementById("row").value = tr.cells[2].innerHTML.trim();
        document.getElementById("col").value = tr.cells[3].innerHTML.trim();
        document.getElementById("status").value = tr.cells[4].innerHTML.trim();

    }

    function checkRow() {
        var reg = /^[1-9]{1,2}$/;
        var row = $(".hang");
        if (!reg.test(row.val())) {

            $(".hang+p").html("<img src='../../images/falseCheck.png'>");
            row.focus();
            return false;
        }
        else {
//            alert("yes");
            $(".hang+p").html("<img src='../../images/trueCheck.png'>");
            return true;
        }
    }

    function checkStatus() {
        var status = $('.status');
        if (status.val() == 1 || status.val() == 0 || status.val() == -1) {
            // alert("yes");
            $(".status+p").html("<img src='../../images/trueCheck.png'>");
            return true;
        }
        else {
//
            $(".status+p").html("<img src='../../images/falseCheck.png'>");
            status.focus();
            return false;
        }

    }

    function checkCol() {
        var reg = /^[1-9]{1,2}$/;
        var col = $(".col");
        if (!reg.test(col.val())) {

            $(".col+p").html("<img src='../images/falseCheck.png'>");
            col.focus();
            return false;
        }
        else {
//            alert("yes");
            $(".col+p").html("<img src='../images/trueCheck.png'>");
            return true;
        }
    }

    function checkAll() {
        if (!checkNum() || !checkName() || !checkCol() || !checkRow() || !checkStatus())
            return true;
        else
            return false;
    }


    function checkNum() {
        var reg = /^[0-9]{2,10}$/;
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


    function checkName() {
        var reg = /^[\u0391-\uFFE5]+$/;
        var name = $(".name");
        if (!reg.test(name.val())) {

            $(".name+p").html("<img src='../images/falseCheck.png'>");
            name.focus();
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