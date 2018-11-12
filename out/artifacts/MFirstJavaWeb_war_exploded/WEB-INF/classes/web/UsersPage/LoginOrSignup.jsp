<%--
  Created by IntelliJ IDEA.
  User: pepper
  Date: 2018/10/15
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登陆</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <style type="text/css">
        .form-con{
            margin-top:60px;
            padding-top:60px;
            padding-bottom:30px;
            padding-left:60px;
            padding-right:60px;
        }
        .form-horizontal{
            padding:30px;
        }
    </style>
    <!-- Custom styles for this template -->
    <link href="<%=request.getContextPath()%>/res/signin.css" rel="stylesheet">

</head>
<body class="text-center">
    <form class="form-signin" method="post" action="/Login">

        <h1 class="h3 mb-3 font-weight-normal">请登录</h1>
        <label for="inputEmail" class="sr-only">用户名</label>
        <input  id="inputEmail" name="username" class="form-control" placeholder="输入用户名" required autofocus>
        <label for="inputPassword" class="sr-only">密码</label>
        <input type="password" id="inputPassword" name="password" class="form-control" placeholder="输入密码" required>

        <label>
            <h8 class="text-danger" > <%=request.getSession().getAttribute("failedLogin")==null?"":request.getSession().getAttribute("failedLogin")%></h8>
            <%
                request.getSession().setAttribute("failedLogin"," ");
            %>
        </label>
        <div class="checkbox mb-3">
            <label>
                <input type="checkbox" value="remember-me"> 记住我
            </label>
        </div>
        <button class="btn btn-lg btn-success btn-block" type="submit">登陆</button>
        <a  href="<%=request.getContextPath()%>/UsersPage/Signup.jsp">注册</a>
        <a  href="<%=request.getContextPath()%>/RestaurantPage.jsp">跳转到商家页</a>
        <p class="mt-5 mb-3 text-muted">&copy; 2017-2018</p>
    </form>
</body>

</html>
