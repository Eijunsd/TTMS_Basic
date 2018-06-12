<%--
  Created by IntelliJ IDEA.
  User: ChenZi
  Date: 2018/6/12
  Time: 00:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="layui-v2.1.4/layui-v2.1.4/layui/css/layui.css" media="all">
    <!--<link rel="stylesheet" type="text/css" href="../static/h-ui/css/H-ui.min.css" />-->
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../../static/h-ui.admin/css/H-ui.admin.css"/>
    <link rel="stylesheet" type="text/css" href="../../lib/Hui-iconfont/1.0.8/iconfont.css"/>
    <link rel="stylesheet" type="text/css" href="../../css/bootstrap.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Play</title>
    <style>
        /*.tab-content {*/
        /*display: inline-block;*/
        /*-webkit-transform: translateZ(20px);*/
        /*-moz-transform: translateZ(20px);*/
        /*-ms-transform: translateZ(20px);*/
        /*transform: translateZ(20px);*/
        /*margin-bottom: 20px;*/
        /*}*/
        img {

        }

        .li_img {

            float: left;
            list-style: none;
        }

        .ul_img {
            width: 6000px;
            padding: 0px;
            margin: 0px;
            transition: all 2s;
        }

        .main_div {
            width: 100%;
            overflow: hidden;
            position: relative;
            /*top: 100px;*/
            /*left: 350px;*/
        }

        .arrows {
            z-index: 9999;
            position: absolute;
            padding-top: 230px;
            width: 800px;
        }

        .arrows span {
            font-size: 3em;
            color: seashell;
        }

        .arrows span:hover {
            /*变小手*/
            cursor: pointer;
            background-color: rgba(192, 192, 192, 0.29);
        }

        .div_btn {
            float: left;
            border-radius: 100px;
            background-color: aquamarine;
            width: 60px;
            height: 10px;
            margin-left: 10px;
            margin-top: 30px;
        }

        .div_btn:hover {
            background-color: aqua;

        }
    </style>


    <script>
        //跑动的次数
        var count = 0;
        //动画的执行方向
        var isgo = false;
        //定义计时器对象
        var timer;

        window.onload = function () {
            /*获取ul元素*/
            var ul_img = document.getElementsByClassName("ul_img")[0];
            //获取所有的li图片元素
            var li_img = document.getElementsByClassName("li_img");
            //获取控制方向的箭头元素
            var arrow = document.getElementsByClassName("arrow");
            //获取所有按钮元素
            var div_btn = document.getElementsByClassName("div_btn");
            div_btn[0].style.backgroundColor = "aqua";


            /*定义计时器，控制图片移动*/
            showtime();

            function showtime() {
                timer = setInterval(function () {
                    if (isgo == false) {
                        count++;
                        ul_img.style.transform = "translate(" + -800 * count + "px)";
                        if (count >= li_img.length - 1) {
                            count = li_img.length - 1;
                            isgo = true;
                        }
                    }
                    else {
                        count--;
                        ul_img.style.transform = "translate(" + -800 * count + "px)";
                        if (count <= 0) {
                            count = 0;
                            isgo = false;
                        }
                    }

                    for (var i = 0; i < div_btn.length; i++) {
                        div_btn[i].style.backgroundColor = "aquamarine";
                    }

                    div_btn[count].style.backgroundColor = "aqua";

                }, 4000)
            }

            /*鼠标进入左右方向键操作*/
            for (var i = 0; i < arrow.length; i++) {
                //鼠标悬停时
                arrow[i].onmouseover = function () {
                    //停止计时器
                    clearInterval(timer);
                }
                //鼠标离开时
                arrow[i].onmouseout = function () {
                    //添加计时器
                    showtime();
                }
                arrow[i].onclick = function () {
                    //区分左右
                    if (this.title == 0) {
                        count++;
                        if (count > 3) {
                            count = 0;
                        }
                    }
                    else {
                        count--;
                        if (count < 0) {
                            count = 3;
                        }
                    }

                    ul_img.style.transform = "translate(" + -800 * count + "px)";

                    for (var i = 0; i < div_btn.length; i++) {
                        div_btn[i].style.backgroundColor = "aquamarine";
                    }
                    div_btn[count].style.backgroundColor = "aqua";
                }
            }

            //鼠标悬停在底部按钮的操作
            for (var b = 0; b < div_btn.length; b++) {
                div_btn[b].index = b;
                div_btn[b].onmouseover = function () {

                    clearInterval(timer);

                    for (var a = 0; a < div_btn.length; a++) {
                        div_btn[a].style.backgroundColor = "aquamarine";
                    }
                    div_btn[this.index].style.backgroundColor = "aqua";
                    //让count值对应
                    //为了控制方向
                    if (this.index == 3) {
                        isgo = true;
                    }
                    if (this.index == 0) {
                        isgo = false;
                    }
                    count = this.index;
                    ul_img.style.transform = "translate(" + -800 * this.index + "px)";
                }
                div_btn[b].onmouseout = function () {
                    //添加计时器
                    showtime();
                }
            }
        }
    </script>
</head>
<body>
<!--实现图片轮播-->
<div>
    <div class="main_div" style="width: 100%;height: 100px">
        <div class="arrows">
            <span title="1" class="arrow"><</span>
            <span title="0" class="arrow" style="float: right">></span>
        </div>

        <ul class="ul_img">
            <li class="li_img"><img src="../../image/zhuluoji.jpg"></li>
            <li class="li_img"><img src="../../image/chaoshikong.jpg"></li>
            <li class="li_img"><img src="../../image/come.jpg"></li>
            <li class="li_img"><img src="../../image/hero.jpg"></li>
        </ul>
    </div>

    <div style="margin-left: 500px">
        <div class="div_btn"></div>
        <div class="div_btn"></div>
        <div class="div_btn"></div>
        <div class="div_btn"></div>
    </div>
</div>

<style type="text/css">
    .tab-control-item {
        font-size: medium;
        margin-left: 50px;
    }

    .tab-content {
        width: 1050px;
        height: 282px;
    }

    .movie-card-wrap {
        display: inline-block;
        width: 160px;
        height: 282px;
        margin-left: 40px;
        position: inherit;
    }

    .movie-card-info {
        display: none;
        position: absolute;
        top: 0;
        left: 0;
        width: 125px;
        padding-top: 30px;
        padding-left: 25px;

    }

    .movie-card-buy {
        background: red;
        height: 30px;
        width: 160px;
        display: block;
        text-align: center;
    }

    card-info .movie-card-mask {
        width: 160px;
        height: 225px;
        background-color: #000;
    }

    .movie-card-poster {
        width: 160px;
        height: 224px;
    }

    .movie-card-name {
        display: none;
        position: absolute;
        height: 56px;
        width: 100%;
        bottom: 0;
    }
</style>
<%--存取数据--%>
<script>
    function savedate() {
        sessionStorage.setItem()
    }
</script>
<div class="center-wrap" style="margin-top: 80px">
    <div class="tab-control tab-movie-tit">
        <a class="tab-control-item" href="#">正在热映(33)</a>
    </div>
</div>
<div class="tab-content">
    <div class="tab-movie-list" style="width: 1050px">
        <div class="movie-card-wrap">
            <a href="sale.jsp" class="movie-card">
                <div class="movie-card-tag"><i class="t-"></i></div>
                <div class="movie-card-poster">
                    <img width="160" height="224" src="../../image/shenhai.jpg">
                </div>
                <div class="movie-card-info">
                    <div class="movie-card-mask"></div>
                    <div class="movie-card-list">
                        <span>导演：帕夏&middot;帕特里基</span>
                        <span>主演：尚格&middot;云顿,杜夫&middot;龙格尔,杰思敏&middot;沃兹,帕特里克&middot;基尔帕特里克,阿尔&middot;萨皮恩扎,克里斯&middot;云顿</span>
                        <span>类型：动作,冒险</span>
                        <span>地区：美国</span>
                        <span>语言：英语</span>
                        <span>片长：104分钟</span></div>
                </div>
            </a>
            <a href="sale.jsp" class="movie-card-buy">选座购票</a>
        </div>
    </div>


</div>
</div>
</body>
</html>