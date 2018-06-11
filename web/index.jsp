<%--
  Created by IntelliJ IDEA.
  User: ChenZi
  Date: 2018/6/11
  Time: 10:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>登录</title>

  <link href="css/bootstrap.min.css" rel="stylesheet">
  <link href="css/signin.css" rel="stylesheet">
  <%--<link href="css/style.css" rel="stylesheet" type="text/css" />--%>
  <script src="js/jquery-1.7.2.min.js" type="text/javascript"></script>

  <script type="text/javascript">
      var req;
      /*通过异步传输XMLHTTP发送参数到ajaxServlet，返回符合条件的XML文档*/
      function check() {
          var user = document.getElementById("username").value;
          var pass = document.getElementById("password").value;
          var url = "Login?username=" + user + "&password=" + pass;

          if (window.XMLHttpRequest) {
              req = new XMLHttpRequest();
          } else if (window.ActiveXObject) {
              req = new ActiveXObject("Microsoft.XMLHTTP");
          }
          if (req) {
              req.open("get", url, true);
              //get方式可不加如下语句
              //req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
              req.onreadystatechange = complete;
              req.send(null);
              document.getElementById("flag").innerText = "请稍后，正在检查用户信息...";
          }
      }
      /*分析返回的XML文档*/
      function complete() {
          var infoStr;
          if (req.readyState == 4) {
              if (req.status == 200) {
                  var result = req.responseText;
                  if (result == "") {
                      infoStr = '<div style="color:red">请输入用户名!</div>';
                  } else if (result == "yes") {
                      infoStr = '<div style="color:forestgreen">该用户存在，可以登录!</div>';
                  } else {
                      infoStr = '<div style="color:red">该用户不存在，请检查后重新输入!</div>';
                  }
                  document.getElementById("flag").innerHTML = infoStr;
              }
          }
      }
  </script>
</head>
<body>
<div class="signin">
  <div class="signin-head"><img src="images/head.png" alt="" class="img-circle" width="110px" height="110px"></div>
  <form id="sendData" class="form-signin" name="form" role="form" action="Login" method="post">
    <div id="login_tip" style="display: inline;height: 30px;margin-top: 10px"></div>
    <%--<label class="control-label"><div id="flag" style="color:red"></div></label>--%>
    <label class="control-label" id="flag"></label>
    <input type="text" class="form-control" id="username" name="username" placeholder="用户名" required autofocus onblur="check()" />
    <input type="password" class="form-control" id="password" name="password" placeholder="密码" required autofocus/>
    <label class="checkbox">
      <input type="checkbox" value="remember-me"> 记住我&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;&nbsp;
      <a href="TTMS/forgetpasswd/forgetPwd1.html" target="_blank">忘记登录密码？</a>
    </label>

    <input type="submit" id="submit" class="btn btn-lg btn-warning btn-block" name="Submit" value="登录" onclick="return check()"/>
  </form>
</div>
<div style="text-align:center;"></div>
</body>
</html>
