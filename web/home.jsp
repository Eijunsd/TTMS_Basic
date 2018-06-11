<%--
  Created by IntelliJ IDEA.
  User: ChenZi
  Date: 2018/6/11
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="Bookmark" href="/favicon.ico">
    <link rel="Shortcut Icon" href="/favicon.ico"/>
    <!--[if lt IE 9]>
    <script type="text/javascript" src="../lib/html5shiv.js"></script>
    <script type="text/javascript" src="../lib/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="../static/h-ui/css/H-ui.min.css"/>
    <link rel="stylesheet" type="text/css" href="../static/h-ui.admin/css/H-ui.admin.css"/>
    <link rel="stylesheet" type="text/css" href="../lib/Hui-iconfont/1.0.8/iconfont.css"/>
    <link rel="stylesheet" type="text/css" href="../static/h-ui.admin/skin/green/skin.css" id="skin"/>
    <link rel="stylesheet" type="text/css" href="../static/h-ui.admin/css/style.css"/>
    <!--[if IE 6]>
    <script type="text/javascript" src="../lib/DD_belatedPNG_0.0.8a-min.js"></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>Manager</title>
    <style>
        .modal-dialog {
            width: 500px;
            text-align: center;
        }
    </style>
</head>
<body>

<%--<%--%>
    <%--String path = request.getContextPath();--%>
    <%--String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";--%>


    <%--/**--%>
     <%--* 再这里可能会有错误--%>
     <%--*/--%>
    <%--String flag = (String) session.getAttribute("loginflag");--%>
    <%--if (flag == null || !flag.equals("ok")) {--%>
        <%--request.getSession().setAttribute("desc", "请从入口登陆。");--%>
        <%--request.getRequestDispatcher("Login.jsp").forward(request, response);--%>
    <%--}--%>

<%--%>--%>
<header class="navbar-wrapper">
    <div class="navbar navbar-fixed-top">
        <div class="container-fluid cl"><a class="logo navbar-logo f-l mr-10 hidden-xs">管理员界面</a> <a
                class="logo navbar-logo-m f-l mr-10 visible-xs" href="/aboutHui.shtml">H-ui</a>

            <a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:;">&#xe667;</a>
            <nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
                <ul class="cl">
                    <li>超级管理员</li>
                    <li class="dropDown dropDown_hover">
                        <a href="#" class="dropDown_A">admin <i class="Hui-iconfont">&#xe6d5;</i></a>
                        <ul class="dropDown-menu menu radius box-shadow">
                            <li><a href="javascript:;" data-toggle="modal" data-target="#user">个人信息</a></li>
                            <li><a href="#javascript:;" data-href="../登录/index.">切换账户</a></li>
                            <li><a href="#javascript:;" onclick="">退出</a></li>
                        </ul>
                    </li>
                    <li id="Hui-msg"><a href="#" title="消息"><span class="badge badge-danger">9</span><i
                            class="Hui-iconfont" style="font-size:18px">&#xe68a;</i></a></li>
                    <li id="Hui-skin" class="dropDown right dropDown_hover"><a href="javascript:;" class="dropDown_A"
                                                                               title="换肤"><i class="Hui-iconfont"
                                                                                             style="font-size:18px">&#xe62a;</i></a>
                        <ul class="dropDown-menu menu radius box-shadow">
                            <li><a href="javascript:;" data-val="default" title="默认（黑色）">默认（黑色）</a></li>
                            <li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
                            <li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
                            <li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
                            <li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
                            <li><a href="javascript:;" data-val="orange" title="橙色">橙色</a></li>
                        </ul>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</header>
<aside class="Hui-aside">
    <div class="menu_dropdown bk_2">
        <dl id="menu-article">
            <dt><i class="Hui-iconfont">&#xe616;</i> 演出厅管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="studio.jsp" data-title="演出厅" onclick="/studioServlet?method=searchByPage">全部演出厅</a></li>
                </ul>
            </dd>
        </dl>
         <dl id="menu-play">
         <dt><i class="Hui-iconfont">&#xe616;</i> 剧目管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
         <dd>
         <ul>
         <li><a data-href="Playmanager.jsp" data-title="剧目" >全部剧目</a></li>
         </ul>
         </dd>
         </dl>
        <dl id="menu-sale">
            <dt><i class="Hui-iconfont">&#xe616;</i> 售票<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="Sale.jsp" data-title="售票">售票</a></li>
                </ul>
            </dd>
        </dl>
        <!--<dl id="menu-refund">-->
        <!--<dt><i class="Hui-iconfont">&#xe616;</i> 退票<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>-->
        <!--<dd>-->
        <!--<ul>-->
        <!--<li><a data-href="#" data-title="退票" >退票</a></li>-->
        <!--</ul>-->
        <!--</dd>-->
        <!--</dl>-->
        <dl id="menu-schedule">
            <dt><i class="Hui-iconfont">&#xe616;</i> 演出计划管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="Schedule.jsp" data-title="演出计划">全部演出计划</a></li>
                </ul>
            </dd>
        </dl>
        <%--<dl id="menu-analyse">--%>
            <%--<dt><i class="Hui-iconfont">&#xe620;</i> 销售数据分析<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>--%>
            <%--<dd>--%>
                <%--<ul>--%>
                    <%--<li><a data-href="#" data-title="日销售数据" href="javascript:void(0)">日销售数据分析</a></li>--%>
                    <%--<li><a data-href="#" data-title="周销售数据" href="javascript:void(0)">周销售数据分析</a></li>--%>
                    <%--<li><a data-href="#" data-title="月销售数据" href="javascript:void(0)">月销售数据分析</a></li>--%>
                    <%--<li><a data-href="#" data-title="季度销售数据" href="javascript:void(0)">季度销售数据分析</a></li>--%>
                    <%--<li><a data-href="#" data-title="年销售数据" href="javascript:void(0)">年销售数据分析</a></li>--%>
                <%--</ul>--%>
            <%--</dd>--%>
        <%--</dl>--%>
        <dl id="menu-picture">
            <dt><i class="Hui-iconfont">&#xe613;</i> 座位管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="Seat.jsp" data-title="1号厅" href="javascript:void(0)">1号厅</a></li>
                    <%--<li><a data-href="seat/seatManager2.html" data-title="2号厅" href="javascript:void(0)">2号厅</a></li>--%>
                    <%--<li><a data-href="seat/seatManager3.html" data-title="3号厅" href="javascript:void(0)">3号厅</a></li>--%>
                </ul>
            </dd>

        </dl>
        <dl id="menu-product">
            <dt><i class="Hui-iconfont">&#xe620;</i> 人员管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="staff/StaffManager.jsp" data-title="员工管理" href="javascript:void(0)">员工管理</a>
                    </li>
                    <li><a data-href="staff/UserManager.jsp" data-title="用户管理" href="javascript:void(0)">用户管理</a>
                    </li>

                </ul>
            </dd>
        </dl>
    </div>
</aside>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a>
</div>
<section class="Hui-article-box">
    <div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
        <div class="Hui-tabNav-wp">
            <ul id="min_title_list" class="acrossTab cl">
                <li class="active">
                    <span title="我的桌面" data-href="">我的桌面</span>
                    <em></em></li>
            </ul>
        </div>
        <div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S"
                                                  href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a
                id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a>
        </div>
    </div>
    <div id="iframe_box" class="Hui-article">
        <div class="show_iframe">
            <div style="display:none" class="loading"></div>
            <iframe scrolling="yes" frameborder="0" src="./jquerybanner/index.html"></iframe>
        </div>
    </div>
</section>

<div class="contextMenu" id="Huiadminmenu">
    <ul>
        <li id="closethis">关闭当前</li>
        <li id="closeall">关闭全部</li>
    </ul>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="../lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="../lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="../static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="../static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->


<div class="modal fade bs-example-modal-lg" id="user" tabindex="-1" role="dialog" aria-labelledby="user"
     aria-hidden="true">
    <div class="modal-dialog" style="border-radius: 5px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h3 class="modal-title" id="geren">
                    个人信息
                </h3>
            </div>
            <div class="modal-body">

                <div><h4>编号：3000</h4></div>
                <div><h4>职称：管理员</h4></div>
                <div><h4>名称：张三</h4></div>
                <div><h4>电话：11111</h4></div>
                <div><h4>地址：西安邮电大学</h4></div>
            </div>


        </div>

    </div>
</div>


<div>管理员信息</div>


</body>
</html>
